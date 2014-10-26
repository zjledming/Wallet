package com.cts.elt.designpattern.decorade;

public class Moca implements Coffee {

	private int milk = 5;
	private Coffee c = null;

	public Moca(Coffee c) {
		this.c = c;
	}

	public int getPrice() {
		// TODO Auto-generated method stub
		return c.getPrice() + milk;
	}

}
