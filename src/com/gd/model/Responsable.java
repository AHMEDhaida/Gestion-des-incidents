package com.gd.model;



import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Responsable")
public class Responsable extends Utilisateur {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Responsable(Long id, String login, String password, String nom, String prenom, String email) {
		super(id, login, password, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Responsable() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	

}
