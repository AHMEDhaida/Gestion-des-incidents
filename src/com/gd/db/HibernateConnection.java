package com.gd.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConnection {
	private static HibernateConnection instance = new HibernateConnection();
	private Session session = null;
	private SessionFactory factory = null;

	private HibernateConnection() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		
        // Creating Hibernate Session Factory Instance
		factory = meta.getSessionFactoryBuilder().build();
		
        // Creating The Hibernate's Session Object
		session = factory.openSession();
	}

	public Session getSession() {
		if (session == null)
			new HibernateConnection();
		return session;
	}

	public void closeSession() {
		if (session != null) {
			session.close();
			session = null;
		}
	}

	public void closeSessionFactory() {
		if (factory != null) {
			closeSession();
			factory.close();
			factory = null;
		}
	}

	public static HibernateConnection getInstance() {
		return instance;
	}
}
