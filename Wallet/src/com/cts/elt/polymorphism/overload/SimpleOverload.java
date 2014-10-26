package com.cts.elt.polymorphism.overload;

public class SimpleOverload {
	public void print(String some) {
		System.out.println("String value======>" + some);
	}

	public void print(int some) {
		System.out.println("int value======>" + some);
	}
	public void print(Object some){
		System.out.println("Object value======>" + some);
	}

	public static void main(String[] args) {
		SimpleOverload simpleOverload = new SimpleOverload();
		simpleOverload.print(1);
		simpleOverload.print("a");
		simpleOverload.print(null);
		simpleOverload.print(new Integer("1"));
		
	}
}
