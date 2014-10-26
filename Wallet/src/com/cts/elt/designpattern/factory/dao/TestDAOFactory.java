package com.cts.elt.designpattern.factory.dao;

public class TestDAOFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		UserDAO oracleUserDao = DAOFactory.getInstance(
				"com.cts.elt.designpattern.factory.dao.OracleDAOFactory")
				.getUserDAO();
		oracleUserDao.insert();
		oracleUserDao.update();
		oracleUserDao.delete();
		oracleUserDao.select();

		UserDAO db2Dao = DAOFactory.getInstance(
				"com.cts.elt.designpattern.factory.dao.DB2DAOFactory").getUserDAO();
		db2Dao.insert();
		db2Dao.update();
		db2Dao.delete();
		db2Dao.select();

	}
}
