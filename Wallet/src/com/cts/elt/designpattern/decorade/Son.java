package com.cts.elt.designpattern.decorade;

public class Son implements Work {

	private Work work=null;
	public Son(Work work){
		this.work=work;
	}
	public void paint() {
		System.out.println("son draw a picture...");
		work.paint();

	}

}
