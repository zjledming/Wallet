package com.cts.elt.polymorphism.override;

public class Shadow {
	public static void main(String s[]) {
		S1 s1 = new S1();
		S2 s2 = new S2();

		System.out.println(s1.s); // prints S1
		System.out.println(s1.getS()); // prints S1
		System.out.println(s2.s); // prints S2
		System.out.println(s2.getS()); // prints S2

		s1 = s2;
		System.out.println(s1.s); // prints S1, not S2 -
		// since variable is resolved at compile time
		System.out.println(s1.getS()); // prints S2 -
		// since method is resolved at run time
	}
}
