package com.cts.elt.multiextend;

public class ClassD extends ClassC {

	public void testMultiExtend(){
		super.methodA();
		super.methodB();
		super.methodC();
	}
	public static void main(String[] args){
		ClassD classD=new ClassD();
		classD.testMultiExtend();
	}
}
