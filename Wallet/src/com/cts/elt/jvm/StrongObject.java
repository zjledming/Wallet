package com.cts.elt.jvm;

public class StrongObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("abc");
		p1.setAge(101);
		//Person p2 = p1;
		p1=null;
		System.gc();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		if (p1 != null) {
			System.out.println("p1 object exist");
		} else {
			System.out.println("p1 object has been remove");
		}
//		if (p2 != null) {
//			System.out.println("object exist");
//		} else {
//			System.out.println("object has been remove");
//		}
	}

}
