package com.cts.elt.polymorphism.override;

public class BaseSub extends Base {
	int a = 200;

	public void show(String s) {
		System.out.println(s);
	}
	public static void main(String[] args){
		BaseSub testSubBase=new BaseSub();
		testSubBase.show("ok");
	}
}
