package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

// servlet -> calls service -> calls dao
public class EmployeeDao {
	
	// CRUD methods
	
	// Create (SERVICE layer will use to regiser)
	public int insert(Employee e) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// caputre the pk returned when the session method save is called
		int pk = (int) ses.save(e);
		
		tx.commit();
		
		// return the pk
		return pk;
	}
	
	// Read
	public List<Employee> findAll() {
		
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL statement -- Hibernate Query langauge. Odd mix of OOP & Native SQL
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		// return the list of employees
		return emps;
	}
	
	public boolean delete() {
		
		return false;
	}
	
	public boolean update(Employee e) {
		
		return false;
	}

}
