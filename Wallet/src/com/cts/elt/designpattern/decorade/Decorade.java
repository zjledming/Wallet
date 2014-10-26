package com.cts.elt.designpattern.decorade;

public class Decorade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Work mywork = new WorkImpl();
		mywork.paint();
		mywork = new Son(new Father(new Mother(mywork)));
		mywork.paint();

	}

}
