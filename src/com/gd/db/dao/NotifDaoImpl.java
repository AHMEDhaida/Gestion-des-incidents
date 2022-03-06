package com.gd.db.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gd.db.HibernateConnection;
import com.gd.db.UMSDBException;
import com.gd.model.Developpeur;
import com.gd.model.Incident;
import com.gd.model.Notif;


public class NotifDaoImpl implements IDaoImpl<Notif> {

	@Override
	public void create(Notif obj) throws UMSDBException {
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

	@Override
	public List<Notif> readBy(Long id) throws UMSDBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notif> readByDev(int idRapporteur, String statu) throws UMSDBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Notif obj) throws UMSDBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws UMSDBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Notif> list() throws UMSDBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> readByDev(Developpeur dev) throws UMSDBException {
		// TODO Auto-generated method stub
		return null;
	}
	
}