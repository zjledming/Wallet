package com.cts.elt.datastructure.comparable;

public class PersonBean implements Comparable<PersonBean> {
	public PersonBean(int age, String name) {
		this.age = age;
		this.name = name;
	}

	int age = 0;
	String name = "";

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
		if (!(o instanceof PersonBean)) {
			return false;
		}
		PersonBean p = (PersonBean) o;
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

	public int compareTo(PersonBean person) {
		int cop = age - person.getAge();
		if (cop != 0)
			return cop;
		else
			return name.compareTo(person.name);
	}
}
