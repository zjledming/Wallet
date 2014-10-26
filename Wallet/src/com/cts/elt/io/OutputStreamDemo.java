package com.cts.elt.io;

import java.io.*;

public class OutputStreamDemo {

	public void writeWithByte() {
		String fileName = "D:" + File.separator + "hello.txt";
		OutputStream out = null;
		File f = new File(fileName);
		try {
			out = new FileOutputStream(f, true);
			String str = "   [hello with bytearray]";
			byte[] b = str.getBytes();
			out.write(b);
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

	public void writeWithByteArray() {
		String fileName = "D:" + File.separator + "hello.txt";
		OutputStream out = null;
		File f = new File(fileName);
		try {
			out = new FileOutputStream(f, true);
			String str = "   [hello with byte yi ge ge xie]";
			byte[] b = str.getBytes();
			for (int i = 0; i < b.length; i++) {
				out.write(b[i]);
			}
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

	public static void main(String[] args) {
		OutputStreamDemo od = new OutputStreamDemo();
		od.writeWithByte();
		od.writeWithByteArray();

	}

}
