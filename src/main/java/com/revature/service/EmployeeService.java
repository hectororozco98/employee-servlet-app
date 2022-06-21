package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDao edao;
	
	/*
	 * Dependency Injection via Constructor Injection
	 * 
	 * Constructor Injection is a sophisticated way of ensuring 
	 * that the employeeService object always has an employeedao object
	 */
	
	
	public EmployeeService(EmployeeDao edao) {
		
		this.edao = edao;
	}
	
	/*
	 * Our servlet will pass the username and password
	 */
	
	public Employee confirmLogin(String username, String password) {
		
		// strea mthroug hall the employeees that are returned
		Optional<Employee> possibleEmp = edao.findAll().stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		
		// IF the employee is present, return it, otherwise return an empty Employee object with Id of 0
		return (possibleEmp.isPresent() ? possibleEmp.get() : new Employee());
		// ideally you should optimize thius and setup a custom exception
	
	}
	
	public List<Employee> getAll() {
		
		return edao.findAll();
	}
	
	
}
