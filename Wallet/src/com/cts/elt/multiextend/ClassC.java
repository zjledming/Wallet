package com.cts.elt.multiextend;

public class ClassC implements InterC {


	public void methodA() {
		InterA classA=new ClassA();
		classA.methodA();
	}


	public void methodB() {
		InterB classB=new ClassB();
		classB.methodB();

	}


	public void methodC() {
		System.out.println("methodC from ClassC");

	}

}
