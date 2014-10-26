package com.cts.elt.clone;

public class A implements Cloneable {
	public String[] name;
	public int age;
	
	public A() {
		name = new String[2];
	}

	public A clone() {
		A a = null;
		try {
			a = (A) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//a.name = new String[2];
		return a;
	}

}