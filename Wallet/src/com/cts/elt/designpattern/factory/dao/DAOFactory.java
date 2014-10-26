package com.cts.elt.designpattern.factory.dao;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();

	public static DAOFactory getInstance(String className) throws Exception {

		return (DAOFactory) Class.forName(className).newInstance();

	}
}
