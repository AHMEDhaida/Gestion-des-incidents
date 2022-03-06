package com.gd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Incident")
public class Incident  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "description")
	private String description;
	
	@Column(name = "opendate")
	private String opendate;
	
	@Column(name = "etat")
	private String etat;
	
	@Column(name = "application")
	private String application;
	
	@Column(name = "gravite")
	private String gravite;
	
	 @CreationTimestamp
	 private Date date;
	
	@ManyToOne 
    private Rapporteur rapporteur;
	
	@ManyToOne 
	private Developpeur developpeur;
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JoinColumn(name = "incident_id")
	 private List<Note> notes = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public Rapporteur getRapporteur() {
		return rapporteur;
	}
	public void setRapporteur(Rapporteur rapporteur) {
		this.rapporteur = rapporteur;
	}
	public Developpeur getDeveloppeur() {
		return developpeur;
	}
	public void setDeveloppeur(Developpeur developpeur) {
		this.developpeur = developpeur;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getGravite() {
		return gravite;
	}
	public void setGravite(String gravite) {
		this.gravite = gravite;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	

}
