package com.cts.elt.designpattern.decorade;

public class IceCoffee implements Coffee {

	private int ice = 3;
	private Coffee c = null;

	public IceCoffee(Coffee c) {
		this.c = c;
	}

	public int getPrice() {
		// TODO Auto-generated method stub
		return c.getPrice() + ice;
	}

}
