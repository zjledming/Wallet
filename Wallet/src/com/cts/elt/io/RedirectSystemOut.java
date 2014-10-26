package com.cts.elt.io;

import java.io.*;

public class RedirectSystemOut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("can be seen from the screen");
		File file = new File("d:" + File.separator + "hello.txt");
		PrintStream ps = null;
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			ps = new PrintStream(fs);
			System.setOut(ps);
			System.out.println("only can be seen from file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (Exception e) {
			}
			try {
				if (fs != null) {
					fs.close();
					fs = null;
				}
			} catch (Exception e) {
			}
		}

	}

}
