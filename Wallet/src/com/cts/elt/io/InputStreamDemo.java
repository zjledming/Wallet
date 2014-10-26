package com.cts.elt.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamDemo {
	public void readFile(String fileName) {
		File srcFile = new File(fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(srcFile);
			byte[] b = new byte[(int) srcFile.length()];
			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) in.read();
			}
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		InputStreamDemo id = new InputStreamDemo();
		String src = "D:" + File.separator + "hello.txt";
		id.readFile(src);
	}

}
