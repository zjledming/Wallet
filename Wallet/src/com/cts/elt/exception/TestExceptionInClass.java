package com.cts.elt.exception;

public class TestExceptionInClass {

	/**
	 * @param args
	 */
	public void add(float a, float b) throws MyException1 {

		if (a == b) {

			throw new MyException1("输入的数相等！");

		}
	}

	public static void main(String[] args) throws Exception {
		TestExceptionInClass te = new TestExceptionInClass();
		try {
			te.add(1.1f, 1.1f);
		} catch (MyException1 me) {
			//System.out.println("error:" + me.getMessage());
			throw new Exception(me);
			//throw new Exception("错啦!", me);
		}
	}
}
