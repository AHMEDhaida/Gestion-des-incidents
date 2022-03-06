package com.gd.model;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Developpeur")
public class Developpeur extends Utilisateur {

	
	
	

	public Developpeur(Long id, String login, String password, String nom, String prenom, String email) {
		super(id, login, password, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Developpeur() {
		// TODO Auto-generated constructor stub
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany( targetEntity=Incident.class)
    private List<Incident> incidents = new ArrayList<>();
	
	

}
