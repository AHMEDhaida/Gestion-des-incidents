package com.gd.db.dao;

import java.util.List;

import com.gd.db.UMSDBException;
import com.gd.model.Developpeur;
import com.gd.model.Incident;



public interface IDaoImpl<T> {
	
	public void create(T obj) throws UMSDBException;
	
	public List<T> readBy(Long id) throws UMSDBException;
	
	public List<T> readByDev(int idRapporteur, String statu) throws UMSDBException;
	
	public void update(T obj) throws UMSDBException;
	
	//public void update(String obj) throws UMSDBException;
	
	public void delete(Integer id) throws UMSDBException;
	
	public List<T> list() throws UMSDBException;

	List<Incident> readByDev(Developpeur dev) throws UMSDBException;

}
