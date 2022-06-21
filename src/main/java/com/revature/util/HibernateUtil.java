package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * this is the hibernate helper class
 * which is to handle startupp and access hibernates session factory
 * to obtain a session object (connection to the db)
 */

public class HibernateUtil {
	
	private static Session ses; //this is kind of like the connection interface from the JDBC API
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() { // similar to getConnection
		
		// call on our sessionFactory to open a connection if there isnt one
		if (ses == null) {
			
			ses = sf.openSession();
		}
		return ses;
	}
	
	public static void closeSes() {
		
		// ideally when we close a session it frees up the connection
		// to the db and returns it to the connection ppool so that it can be used
		// by another thread or operation
		ses.close();
	}
}
