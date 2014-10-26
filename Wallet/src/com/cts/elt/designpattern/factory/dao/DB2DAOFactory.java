package com.cts.elt.designpattern.factory.dao;

public class DB2DAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new DB2UserDAOImpl();
	}

}
