package com.cts.elt.designpattern.factory.dao;


public class OracleDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAOImpl();
	}

}
