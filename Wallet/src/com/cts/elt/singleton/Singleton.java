package com.cts.elt.singleton;

public class Singleton {

	private Singleton() {

	}

	public static String getName() {
		return myInit.getName();
	}

	private static InitMyClass myInit;

	public static void init() {
		myInit = new InitMyClass();
		myInit.setName("aaaaaa");
	}

	private static Singleton instance = null;

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			init();
			instance = new Singleton();
		}
		return instance;
	}
}
