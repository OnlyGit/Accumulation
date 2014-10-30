package com.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class Util {

	private static Configuration configure = new Configuration().configure();
	private static SessionFactory factory = null;
	
	static {
		factory = configure.buildSessionFactory();
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static void closeSession(Session session) {
		if(session != null) {
			session.close();
			session = null;
		}
	}
}
