package com.cts.elt.clone;

public class CloneCopy {
	
	public void copyObject() {
		A a = new A();
		a.name [0]="a";
		a.name[1]="a";
		a.age=10;
		System.out.println("before copy");
		for (int i = 0; i < a.name.length; i++) {
			System.out.print(a.name[i] + "    ");
		}
		System.out.println("a.age===="+a.age);
		A b = a.clone();
		b.name[0]="b";
		b.name[1]="b";
		b.age=20;
		System.out.println("after copy b.name:");
		for (int i = 0; i < b.name.length; i++) {
			System.out.print(b.name[i] + "    ");
		}
		System.out.println("b.age===="+b.age);
		System.out.println("after copy a.name:");
		for (int i = 0; i < a.name.length; i++) {
			System.out.print(a.name[i] + "    ");
		}
		System.out.println("a.age===="+a.age);
	}

	public static void main(String[] args) {
		CloneCopy testClone = new CloneCopy();
		testClone.copyObject();

	}

}
