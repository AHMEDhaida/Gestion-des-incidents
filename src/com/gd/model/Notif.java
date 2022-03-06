package com.gd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notif {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_incident")
	private int id_incident;
	
	@Column(name = "id_rapporteur")
	private Long id_rapporteur;
	
	@Column(name = "statut")
	private String statut;
	
	@Column(name = "notification")
	private String notification;
	
	@Column(name = "id_developpeur")
	private Long id_developpeur;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "etatC")
	private String etatC;
	
	@Column(name = "etatR")
	private String etatR;
	
	@Column(name = "etatD")
	private String etatD;
	
	public Notif() {}
	
	
	public Notif(Long rapporteur, Long id_developpeur, int id_incident, String statut, String notification, String date, String etatC, String etatR,String etatD) {
		this.id_rapporteur = rapporteur;
		this.id_incident = id_incident;
		this.statut = statut;
		this.notification=notification;
		this.id_developpeur=id_developpeur;
		this.date=date;
		this.etatC=etatC;
		this.etatR=etatR;
		this.etatD=etatD;
	}

	public Notif(int id, Long id_rapporteur, Long id_developpeur, int id_incident,  String statut, String notification, String date,String etatC, String etatR,String etatD) {
		this(id_rapporteur,id_developpeur,id_incident,statut,notification,date,etatC,etatR,etatD);
		this.id = id;
	}
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_incident() {
		return id_incident;
	}

	public void setId_incident(int id_incident) {
		this.id_incident = id_incident;
	}

	public Long getId_rapporteur() {
		return id_rapporteur;
	}

	public void setId_rapporteur(Long id_rapporteur) {
		this.id_rapporteur = id_rapporteur;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Long getId_developpeur() {
		return id_developpeur;
	}

	public void setId_developpeur(Long id_developpeur) {
		this.id_developpeur = id_developpeur;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEtatC() {
		return etatC;
	}

	public void setEtatC(String etatC) {
		this.etatC = etatC;
	}

	public String getEtatR() {
		return etatR;
	}

	public void setEtatR(String etatR) {
		this.etatR = etatR;
	}

	public String getEtatD() {
		return etatD;
	}

	public void setEtatD(String etatD) {
		this.etatD = etatD;
	}
	
	
	
}

