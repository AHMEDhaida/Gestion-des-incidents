package com.gd.db;
import java.sql.SQLException;

public class UMSDBException extends SQLException {
	private static final long serialVersionUID = 1L;

	public UMSDBException(String message) {
		super(message);
	}
}