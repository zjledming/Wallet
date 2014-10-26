package com.cts.elt.io;

public class InputFromConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int a = 0;
		byte[] input = new byte[1024];
		System.in.read(input);
		System.out.println("your input is: " + new String(input));

	}

}
