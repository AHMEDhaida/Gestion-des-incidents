package com.gd.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administrateur")
public class Administrateur extends Utilisateur {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrateur(Long id, String login, String password, String nom, String prenom, String email) {
		super(id, login, password, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Administrateur() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
