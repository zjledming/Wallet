package com.cts.elt.designpattern.adapter;

public class Adapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("X730");
		Computer lenovoX730 = new Lenovo();
		lenovoX730.buy();
		System.out.println("lenovoT210");
		Computer lenovoT210 = new Lenovo(new BlueTooth());
		lenovoT210.buy();

	}

}
