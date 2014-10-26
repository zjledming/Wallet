package com.cts.elt.immutable;
import java.util.Arrays;
public class TestMyImmutable {

	/**
	 * @param args
	 */
	public void testWrong(){
		int array[]={1,2};
		MyImmutableWrong wm=new MyImmutableWrong(array);
		System.out.println("before constructing:"+wm.toString());
		array[1]=5;
		System.out.println("after constructing:"+wm.toString());
	}
	public void testCorrect(){
		int array[]={1,2};
		MyImmutableCorrect wm=new MyImmutableCorrect(array);
		System.out.println("before constructing:"+wm.toString());
		array[1]=5;
		System.out.println("after constructing:"+wm.toString());
	}
	public static void main(String[] args) {
		TestMyImmutable tm = new TestMyImmutable();
		tm.testWrong();
		
		tm.testCorrect();

	}

}
