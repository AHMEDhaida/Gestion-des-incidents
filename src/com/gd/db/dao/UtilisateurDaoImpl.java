package com.gd.db.dao;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gd.db.HibernateConnection;
import com.gd.db.UMSDBException;

import com.gd.model.Utilisateur;



public class UtilisateurDaoImpl implements IDao {

	@Override
	public void create(Utilisateur user) throws UMSDBException {
		try {
			Session session = HibernateConnection.getInstance().getSession();
			// Creating Transaction Object
			Transaction transaction = session.beginTransaction();
			session.save(user);
			// Transaction Is Committed To Database
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	

	@Override
	public void update(Utilisateur user) throws UMSDBException {
		try {
			Session session = HibernateConnection.getInstance().getSession();
			// Creating Transaction Object
			Transaction transaction = session.beginTransaction();
			session.update(user);
			// Transaction Is Committed To Database
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void delete(long id) throws UMSDBException {
		try {

			Utilisateur user = read(id);

			if (user != null) {

				Session session = HibernateConnection.getInstance().getSession();

				// Creating Transaction Object
				Transaction transaction = session.beginTransaction();

				session.delete(user);

				// Transaction Is Committed To Database
				transaction.commit();
			}
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> list() throws UMSDBException {
		List<Utilisateur> users = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();
			Query query = session.createQuery("From Utilisateur");
			users = query.getResultList();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
		return users;
	}

	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public Utilisateur Login(String login) throws UMSDBException {
		
		Utilisateur user = null;

		try {
			   Session session = HibernateConnection.getInstance().getSession();
			   Transaction transaction = session.beginTransaction();
			
			   org.hibernate.query.Query q =  session.createQuery("From Utilisateur Where login =:login");
			   q.setString("login", login);
			   user = (Utilisateur)  q.uniqueResult();
			   transaction.commit();
			
			
		
			
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}

		return user;

	}

	@Override
	public Utilisateur read(long id) throws UMSDBException {
		Utilisateur user = null;
		
		try {
			Session session = HibernateConnection.getInstance().getSession();
			user = session.find(Utilisateur.class, id);
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage()+ e.getLocalizedMessage());
		}
		return user;
	}
	
	



	
	@Override
	public String type(long id) throws UMSDBException {
		Utilisateur user = null;
		String type = null;

		try {
			Session session = HibernateConnection.getInstance().getSession();
			user = session.find(Utilisateur.class, id);
			type = user.getRole();
			
			
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage() + e.getLocalizedMessage());
		}
		return type;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<String> listDev() throws UMSDBException {
		List<String> users = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();
			Query query = session.createQuery("Select u.login From Utilisateur u Where role ='Developpeur'");
			users = query.getResultList();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
		return users;
	}

	

	
}
