package com.cts.elt.singleton;

public class SingletonVisit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Singleton mySingle = Singleton.getInstance();
		
		System.out.println(mySingle.getName());
		Singleton mySingleRepeat = Singleton.getInstance();
		System.out.println(mySingle.getName());
	}

}
