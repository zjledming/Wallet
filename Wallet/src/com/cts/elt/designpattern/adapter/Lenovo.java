package com.cts.elt.designpattern.adapter;

public class Lenovo implements Computer {

	private BlueTooth bt = null;

	public Lenovo(BlueTooth bt) {
		this.bt = bt;
	}

	public Lenovo() {
	}

	public void buy() {
		System.out.println("     lenovo computer");
		if (bt != null) {
			bt.bluetooth();
		}

	}

}
