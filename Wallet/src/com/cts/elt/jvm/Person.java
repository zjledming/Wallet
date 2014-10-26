package com.cts.elt.jvm;

public class Person {

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private int age = 0;

	public String toString() {
		return "name:   " + this.name + "  age   " + this.age;
	}
}
