package com.cts.elt.io;

import java.io.*;

public class OutputFromConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStream out = null;
		try {
			out.write("hello".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
			}

		}

	}

}
