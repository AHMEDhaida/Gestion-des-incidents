package com.gd.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "Note")
public class Note {
	
	@Id
    @GeneratedValue
    private int id;
	
	@Column(name = "message")
	private String massage;
	@Column(name = "date")
	private Date date;
	
	public Note(String massage,Date date) {
		this.massage = massage;
		this.date = date;
	}
	public Note() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
