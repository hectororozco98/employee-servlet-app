package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	// employee service
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	
	// object mapper for frontend
	private static ObjectMapper om = new ObjectMapper();
	
	/*
	 * What does this methood do?
	 * It extracts the parameters from a request (username and password) from the UI
	 * 
	 * It will call the confirmLogin() method from the EmployeeService and 
	 * see if a user with that username and password exist
	 * 
	 * Who will provide the method with the HTTPRequest? the UI
	 * We need to build an html doc with a form that will send these parameters to the method
	 */
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1. Extract the parameters from the request (username & password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2. call the confirm login(0) method from the employeeservice and see what it returns
		Employee e = eserv.confirmLogin(username, password);
		
		// 3. if the user exists,print their info to the screen
		if (e.getId() > 0) {
			
			// grab the session 
			HttpSession ses = request.getSession();
			
			
			// add the user to the session
			ses.setAttribute("the-user", e);
			
			// alternatively, you can redirect to another resource instead of printing our dynamically
			
			// print our the users data with the PrintWriter
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in!</h3>");
			
			// this is to print the object as a JSON string
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
		} else {
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1>No user found, sorry</h1>");
			//response.setStatus(204); // 204 means successful connection to the server, but no content found
			
		}	
		
	}
	
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. extract all values from the parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		
		// 2. construct a new employee object
		Employee e = new Employee(firstname, lastname, username, password);
		
		// 3. call the register method from the service layer
		int pk = eserv.register(e);
		
		// 4. check its id, if its greater than 0, it's successful
		if (pk > 0) {
			
			e.setId(pk);
			//add the user to the session
			HttpSession ses = request.getSession();
			ses.setAttribute("the-user", e);
			
			// using the request dispatcher, forward the request and the response to a new resource
			// send the user to a new page, which is welcome.html
			request.getRequestDispatcher("welcome.html").forward(request, response);
		} else {
			
			// if it's -1, the register method failed (there is a duplicate)
			// use the PrintWriter to print it out
			
			// TODO: provide better logic in the Service layer to check for PSQL exceptions
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Registration failed. User already exists</h1>");
			out.println("<a href =\"index.html\">Back</a>");	
		}	
	}
	
	// this method will call the employee services findAll() method
	// use an object mapper to transform that list to a json string
			// user PrintWriter to print out the json string to the screen
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. set content type to be application.json
		//response.setContentType("application/json");
		response.setContentType("text/html");
		
		// 2. call the findall method
		List<Employee> emps = eserv.getAll();
		
		// 3. transform the list to a string
		String jsonString = om.writeValueAsString(emps);
		
		// 4. get print writer, then write it out
		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body
		
	}

}
