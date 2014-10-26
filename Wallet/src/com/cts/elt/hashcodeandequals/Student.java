package com.cts.elt.hashcodeandequals;

public class Student {

	private int age = 0;
	private String name = "";

	public Student(int age, String name) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Object o) {
		if (!(o instanceof Student)) {
			return false;
		}
		Student p = (Student) o;
		return (age == p.age) && (name.equals(p.name));
	}
	public int hashCode() {
		int result = 17;
		result = 37 * result + age;
		result = 37 * result + name.hashCode();
		return result;

	}

	public String toString() {
		return (age + "{" + name + "}");
	}
}
