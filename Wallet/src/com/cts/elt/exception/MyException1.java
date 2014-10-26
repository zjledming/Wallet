package com.cts.elt.exception;

public class MyException1 extends Exception {
	public MyException1() {

		super();

	}

	public MyException1(String message) {

		super(message);

	}

	public MyException1(String message, Throwable cause) {

		super(message, cause);

	}

	public MyException1(Throwable cause) {

		super(cause);

	}
}
