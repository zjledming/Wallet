package com.cts.elt.designpattern.decorade;

public class Father implements Work {
	private Work work = null;

	public Father(Work work) {
		this.work = work;
	}

	public void paint() {
		System.out.println("father make a frame for the picture...");
		work.paint();
	}

}
