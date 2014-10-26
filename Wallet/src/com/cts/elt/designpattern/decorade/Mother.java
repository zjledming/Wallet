package com.cts.elt.designpattern.decorade;

public class Mother implements Work {
	private Work work = null;

	public Mother(Work work) {
		this.work = work;
	}

	public void paint() {
		System.out.println("mom paint the color...");
		work.paint();
	}

}
