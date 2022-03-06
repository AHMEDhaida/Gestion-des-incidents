package com.gd.db.dao;
import java.util.List;


import com.gd.db.UMSDBException;
import com.gd.model.Utilisateur;

public interface IDao {
	/**
	 * @param user
	 * @throws UMSDBException
	 */
	public void create(Utilisateur user) throws UMSDBException;

	/**
	 * @param id
	 * @return
	 * @throws UMSDBException
	 */
	public Utilisateur read(long id) throws UMSDBException;

	/**
	 * @param user
	 * @throws UMSDBException
	 */
	public void update(Utilisateur user) throws UMSDBException;

	/**
	 * @param integerProperty
	 * @throws UMSDBException
	 */
	public void delete(long id) throws UMSDBException;

	/**
	 * @return
	 * @throws UMSDBException
	 */
	public List<Utilisateur> list() throws UMSDBException;
	
	/**
	 * @return
	 * @throws UMSDBException
	 */
	public List<String> listDev() throws UMSDBException;
	
	
	/**
	 * @return
	 * @throws UMSDBException
	 */
	public Utilisateur Login(String login) throws UMSDBException;

	public String type(long id)throws UMSDBException;

	


	
}