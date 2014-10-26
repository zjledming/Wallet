package com.cts.elt.hashcodeandequals;

public class Person {

	public Person(int age, String name, Student std) {
		this.age = age;
		this.name = name;
		this.std = std;
	}

	public Student getStd() {
		return std;
	}

	public void setStd(Student std) {
		this.std = std;
	}

	private int age = 0;
	private String name = "";
	private Student std = null;

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
		if (!(o instanceof Person)) {
			return false;
		}
		Person p = (Person) o;
		return (age == p.age) && (name.equals(p.name))
				&& (getStd().getName().equals(p.getStd().getName()))
				&& (getStd().getAge() == p.getStd().getAge());
	}

//	public int hashCode() {
//		int result = 17;
//		result = 37 * result + age;
//		result = 37 * result + name.hashCode();
//		return result;
//
//	}

	public String toString() {
		return (age + "{" + name + "}");
	}

}
