package com.cts.elt.io;

import java.io.*;

public class CopyFile {

	public void copy(String src, String des) {
		File srcFile = new File(src);
		File desFile = new File(des);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(desFile);
			byte[] b = new byte[(int) srcFile.length()];
			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) in.read();
			}
			out.write(b);
//			for (int i = 0; i < b.length; i++) {
//				out.write(b[i]);
//			}
			System.out.println("copied [" + srcFile.getName() + "]    with    "
					+ srcFile.length());
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
		CopyFile cp = new CopyFile();
		String src = "D:" + File.separator + "UltraEdit.zip";
		String des = "D:" + File.separator + "UltraEdit_Copy.zip";
		long sTime = System.currentTimeMillis();
		cp.copy(src, des);
		long eTime = System.currentTimeMillis();
		System.out.println("Total spend: " + (eTime - sTime));
	}

}
