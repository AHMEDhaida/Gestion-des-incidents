package com.gd.service;

import java.util.List;



import com.gd.db.UMSDBException;
import com.gd.db.dao.IDao;
import com.gd.db.dao.IDaoImpl;
import com.gd.db.dao.IncidentDaoImpl;
import com.gd.db.dao.NotifDaoImpl;
import com.gd.db.dao.UtilisateurDaoImpl;
import com.gd.model.Incident;
import com.gd.model.Notif;
import com.gd.model.Utilisateur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {

	private ObservableList<Utilisateur> users = FXCollections.observableArrayList();

	private ObservableList<Incident> incidents = FXCollections.observableArrayList();
	private ObservableList<String> dev = FXCollections.observableArrayList();

	private IDao dao;
	private IDaoImpl<Incident> daoImpl;
	private IDaoImpl<Notif> daoImplNofit;

	public DataSource() throws UMSDBException {
		dao = new UtilisateurDaoImpl();
		daoImpl = new IncidentDaoImpl();
		daoImplNofit = new NotifDaoImpl();
		listUsers();
		listDev();
		
		listIncident();
		
	}
	///////////////////////////////////////////////// Date source user //////////////////////////////////////////////////
     
	public ObservableList<Utilisateur> getUsers() {
		return users;
	}
	
	public ObservableList<String> getDev() {
		return dev;
	}
	
	
	

	public void AddUser(Utilisateur user) throws UMSDBException {
		
		dao.create(user);
		this.users.add(user);
		
		
		
	}
	
	public void DeleteUser(int id) throws UMSDBException {
		Utilisateur user = this.users.get(id);
		this.users.remove(user);
		dao.delete(user.getId());
		
		
	}
	
	public void UpdateUser(Utilisateur user,int selecteUserIndex ) throws UMSDBException {
		users.remove(selecteUserIndex);
		users.add(selecteUserIndex, user);
		dao.update(user);
		
	}
	
	public Utilisateur ReadUser (long l) throws UMSDBException {
		
	    return dao.read(l);
		
	} 
	
	public void listUsers() throws UMSDBException {
		List<Utilisateur> users = dao.list();
		this.users.addAll(users);
	}
	
	public void listDev() throws UMSDBException {
		List<String> usersDev = dao.listDev();
		this.dev.addAll(usersDev);
		
		
	}
	
	public Utilisateur Login(String login) throws UMSDBException {
		Utilisateur user = dao.Login(login);
		return user;
	}
	
	public String type(long id) throws UMSDBException {
		String type = dao.type(id);
		return type;
	}
	
	
     ///////////////////////////////////////////////// Date source incident //////////////////////////////////////////////////
	
   public void AddIncident(Incident incident) throws UMSDBException {
		
	    daoImpl.create(incident);
		this.incidents.add(incident);
		
	}
   
   public ObservableList<Incident> getincIdents() {
		return incidents;
	}
   
   public void listIncident() throws UMSDBException {
		List<Incident> incidents = daoImpl.list();
		this.incidents.addAll(incidents);
	}
   
   public void UpdateIncident(Incident incident,int selecteUserIndex ) throws UMSDBException {
	   incidents.remove(selecteUserIndex);
		incidents.add(selecteUserIndex, incident);
		daoImpl.update(incident);
		
	}
   
   
   public List<Incident> readBy(Long long1) throws UMSDBException{
	   
	   List<Incident> incidents = daoImpl.readBy(long1);
	   return incidents;
		
	   
   }
   
  ///////////////////////////////////////////////// Date source Notif //////////////////////////////////////////////////
   public void AddNotif(Notif notif) throws UMSDBException {
		
	   daoImplNofit.create(notif);
		
		
	}
  

  
}
