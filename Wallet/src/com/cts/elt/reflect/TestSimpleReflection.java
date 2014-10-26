package com.cts.elt.reflect;

public class TestSimpleReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1=new Person();
		p1.setName("tonny");
		System.out.println("before reflected p1.getName(): " + p1.getName());
		p1 = (Person) SimpleReflection.reflectMyMethod(
				"com.cts.elt.reflect.Person", "setName");
		System.out.println("after reflected p1.getName(): " + p1.getName());
		
		Person p2=new Person();
		p2.setName("jimmy");
		System.out.println("before field reflected p2.getName(): " + p2.getName());
		p2 = (Person) FieldReflection.reflectMyField(
				"com.cts.elt.reflect.Person", "name");
		System.out.println("after field reflected p2.getName(): " + p2.getName());

	}

}
