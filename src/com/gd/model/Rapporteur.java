package com.gd.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Rapporteur")
public class Rapporteur extends Utilisateur {
	
	@OneToMany( targetEntity=Incident.class)
    private List<Incident> incidents = new ArrayList<>();

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Rapporteur(Long id, String login, String password, String nom, String prenom, String email) {
		super(id, login, password, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}


	public Rapporteur() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
		

}
