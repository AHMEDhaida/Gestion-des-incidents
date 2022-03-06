package com.gd.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gd.db.HibernateConnection;
import com.gd.db.UMSDBException;
import com.gd.model.Developpeur;
import com.gd.model.Incident;


public class IncidentDaoImpl implements IDaoImpl<Incident> {

	@Override
	public void create(Incident obj) throws UMSDBException {
		// TODO Auto-generated method stub
		
		try {
			Session session = HibernateConnection.getInstance().getSession();
			// Creating Transaction Object
			Transaction transaction = session.beginTransaction();
			session.save(obj);
			// Transaction Is Committed To Database
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public List<Incident> readBy(Long id) throws UMSDBException {
		// TODO Auto-generated method stub
		List<Incident> incidents = new ArrayList<>();

		try {
			   Session session = HibernateConnection.getInstance().getSession();
			   Transaction transaction = session.beginTransaction();
			
			   org.hibernate.query.Query q =  session.createQuery("From incident Where developpeur_id = id");
			   q.setLong("id", id);
			   incidents = q.getResultList();
			   transaction.commit();
			
			
		
			
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}

		return incidents;
	}

	@Override
	public List<Incident> readByDev(int idRapporteur, String statu) throws UMSDBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Incident obj) throws UMSDBException {
		// TODO Auto-generated method stub
		
		try {
			Session session = HibernateConnection.getInstance().getSession();
			// Creating Transaction Object
			Transaction transaction = session.beginTransaction();
			session.update(obj);
			// Transaction Is Committed To Database
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}

	}

	@Override
	public void delete(Integer id) throws UMSDBException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Incident> list() throws UMSDBException {
		List<Incident> incidents = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();
			Query query = session.createQuery("From Incident");
			incidents = query.getResultList();
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
		return incidents;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Incident> readByDev(Developpeur dev) throws UMSDBException {
		// TODO Auto-generated method stub
		List<Incident> incidents = new ArrayList<>();

		try {
			   Session session = HibernateConnection.getInstance().getSession();
			   Transaction transaction = session.beginTransaction();
			
			   org.hibernate.query.Query q =  session.createQuery("From Incident Where developpeur = "+dev+"");
			   
			   incidents = q.getResultList();
			   transaction.commit();
			
			
		
			
		} catch (Exception e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}

		return incidents;
	}

}
