package com.cts.elt.designpattern.factory;

import com.cts.elt.designpattern.factory.dao.DB2Connection;

public class SimpleFactory {

	private OracleConnection getOracleConnection() {
		return new OracleConnection();
	}

	private MySQLConnection getMySQLConnection() {
		return new MySQLConnection();
	}

	private DB2Connection getDB2Connection() {
		return new DB2Connection();
	}

	public Object getDBConnection(int dbType) {
		switch (dbType) {
		case 1:
			return getOracleConnection();
		case 2:
			return getDB2Connection();
		case 3:
			return getMySQLConnection();
		default:
			return null;
		}
	}
}
