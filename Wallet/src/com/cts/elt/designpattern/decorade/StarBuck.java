package com.cts.elt.designpattern.decorade;

public class StarBuck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Coffee iceCoffee = new IceCoffee(new Moca(new CoffeeBean()));
		System.out.println("price=====" + iceCoffee.getPrice());

	}

}
