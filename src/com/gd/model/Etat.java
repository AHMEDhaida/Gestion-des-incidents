package com.gd.model;

public enum Etat {

	Nouveau("Nouveau"),
	En_cours("En_cours"),
	En_attente("En_attente"),
	Résolu("Résolu"),
	Fermé("Fermé");
	private String value;
	private Etat(String value) {
		this.value = value;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
