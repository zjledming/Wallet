package com.cts.elt.designpattern.factory.dao;

public class DB2UserDAOImpl implements UserDAO {

	@Override
	public void insert() {
		System.out.println("db2 insert");

	}

	@Override
	public void update() {
		System.out.println("db2 update");

	}

	@Override
	public void delete() {
		System.out.println("db2 delete");

	}

	@Override
	public void select() {
		System.out.println("db2 select");

	}

}
