package com.cts.elt.clone;
import java.io.*;
public class DeepClone implements Serializable{

	private String name="";
	private int age=0;
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
}
