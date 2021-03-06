package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {

	/*
	 * This method will be responsible for determining what resource the client is
	 * requesting
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. URI rewriting
		// http://localhost:8080/employee-servlet-app/login -- we want to capture login
		// http://localhost:8080/employee-servlet-app/employees -- if they go here it
		// returns all employees in the DB
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		
		System.out.println("Content Example: " + getServletContext().getInitParameter("contextExample"));
		System.out.println("Config Example: " + getServletContext().getInitParameter("configExample"));

		// set up a switch case statement in which we call the appropriate functionality
		// based on the URI returned
		switch (URI) {

		case "login":

			// invoke some function from the RequestHelper
			RequestHelper.processLogin(request, response);
			break;
		case "employees":

			RequestHelper.processEmployees(request, response);
			break;

		case "register":

			RequestHelper.processRegistration(request, response);
			break;

		default:
			// custom error page
			break;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
