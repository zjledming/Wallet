package com.cts.elt.designpattern.factory.dao;

public class OracleUserDAOImpl implements UserDAO {

	@Override
	public void insert() {
		System.out.println("oracle insert");

	}

	@Override
	public void update() {
		System.out.println("oracle update");

	}

	@Override
	public void delete() {
		System.out.println("oracle delete");

	}

	@Override
	public void select() {
		System.out.println("oracle select");

	}

}
