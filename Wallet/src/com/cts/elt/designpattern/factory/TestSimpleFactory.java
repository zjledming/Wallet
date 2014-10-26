package com.cts.elt.designpattern.factory;

import com.cts.elt.designpattern.factory.dao.DB2Connection;

public class TestSimpleFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleFactory sf = new SimpleFactory();
		OracleConnection oraConn = (OracleConnection) sf.getDBConnection(1);
		DB2Connection db2Conn = (DB2Connection) sf.getDBConnection(2);
		MySQLConnection mysqlConn = (MySQLConnection) sf.getDBConnection(3);
		oraConn.getConnection();
		db2Conn.getConnection();
		mysqlConn.getConnection();

	}

}
