package com.cts.elt.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class WeakObject {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("abc");
		p1.setAge(12);
		WeakReference sr = new WeakReference(p1);
		p1 = null;
		System.gc();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		Person p2 = new Person();
		if (sr != null&&sr.get()!=null) {
			p2 = (Person) sr.get();
			System.out.println(p2.toString());
		} else {
			System.out.println("obj has been remove");
		}

	}
}
