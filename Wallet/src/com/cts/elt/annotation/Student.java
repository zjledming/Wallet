package com.cts.elt.annotation;

import java.io.Serializable;

import com.cts.elt.annotation.ValueBind.fieldType;

public class Student implements Serializable {

	private String name = "";

	public String getName() {
		return name;
	}

	@ValueBind(type = fieldType.STRING, value = "aa")
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@ValueBind(type = fieldType.INT, value = "30")
	public void setAge(int age) {
		this.age = age;
	}

	public String getStudentId() {
		return studentId;
	}

	@ValueBind(type = fieldType.STRING, value = "101")
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	private int age = 0;
	private String studentId = "";
}
