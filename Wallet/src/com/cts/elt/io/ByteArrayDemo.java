package com.cts.elt.io;

import java.io.*;

public class ByteArrayDemo {

	/**
	 * @param args
	 */
	public void testByteArray() {
		String str = "ROLLENHOLT";
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		try {
			input = new ByteArrayInputStream(str.getBytes());
			output = new ByteArrayOutputStream();
			int temp = 0;
			while ((temp = input.read()) != -1) {
				char ch = (char) temp;
				output.write(Character.toLowerCase(ch));
			}
			String outStr = output.toString();
			input.close();
			output.close();
			System.out.println(outStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
					output = null;
				}
			} catch (Exception e) {
			}
			try {
				if (input != null) {
					input.close();
					input = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		ByteArrayDemo bd = new ByteArrayDemo();
		bd.testByteArray();

	}

}
