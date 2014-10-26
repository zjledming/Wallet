package com.cts.elt.jvm;

import java.lang.ref.SoftReference;

public class SoftObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("abc");
		p1.setAge(12);
		SoftReference sr = new SoftReference(p1);
		p1 = null;
		System.gc();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		Person p2 = new Person();
		if (sr != null && sr.get() != null) {
			p2 = (Person) sr.get();
			System.out.println(p2.toString());
		} else {
			p2 = new Person();
			sr = new SoftReference(p2);
			System.out.println("new person with no initiantance");
		}

	}

}
