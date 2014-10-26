package com.cts.elt.clone;

public class NoCloneCopy {

	public void copyObject() {
		A a = new A();
		a.name [0]="a";
		a.name[1]="a";
		System.out.println("before copy");
		for (int i = 0; i < a.name.length; i++) {
			System.out.print(a.name[i] + "    ");
		}
		System.out.println("");
		A b = a;
		b.name[0]="b";
		b.name[1]="b";
		System.out.println("after copy b.name:");
		for (int i = 0; i < b.name.length; i++) {
			System.out.print(b.name[i] + "    ");
		}
		System.out.println("");
		System.out.println("after copy a.name:");
		for (int i = 0; i < a.name.length; i++) {
			System.out.print(a.name[i] + "    ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		NoCloneCopy testNoClone = new NoCloneCopy();
		testNoClone.copyObject();

	}

}
