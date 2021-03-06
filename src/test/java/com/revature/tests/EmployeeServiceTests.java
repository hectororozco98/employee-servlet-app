package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	
	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	
	
	@Before
	public void setup() {
		
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);	
	}
	
	@After
	public void teardown() {
		
		mockdao = null;
		eserv = null;
	}
	
	@Test
	public void testConfirmLogin_success() {
		
		// 1. create a fake list of employees
		// dummy data we feed to mockito
		Employee e1 = new Employee(20, "Bruce", "Banner", "hulk", "green");
		Employee e2 = new Employee(20, "Clint", "Barton", "hawkeye", "arrows");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		// 2. setup the daos behavior
		// findAll() methods behavior to provide fake data
		when(mockdao.findAll()).thenReturn(emps);
		
		// 3. capture the actual output of the method 
		Employee actual = eserv.confirmLogin("hulk", "green");
		
		// capture the expected output of the method
		Employee expected = e1;
		
		// 4 assert that they are equal 
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConfirmLogin_fail() {
		
		Employee e1 = new Employee(20, "Bruce", "Banner", "hulk", "green");
		Employee e2 = new Employee(20, "Clint", "Barton", "hawkeye", "arrows");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		// 2. setup the daos behavior
		// findAll() methods behavior to provide fake data
		when(mockdao.findAll()).thenReturn(emps);
		
		// 3. capture the actual output of the method 
		Employee actual = eserv.confirmLogin("ironman", "lasers");
		
		// capture the expected output of the method
		Employee expected = new Employee();
		
		// 4 assert that they are equal 
		assertEquals(expected, actual);	
	}

}
