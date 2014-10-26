package com.cts.elt.io;

import java.io.*;

public class BufferInputStreamDemo {

	/**
	 * @param args
	 */
	public void copy(String src, String des) {
		File srcFile = new File(src);
		File desFile = new File(des);
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(srcFile));
			bout = new BufferedOutputStream(new FileOutputStream(desFile));
			byte[] b = new byte[1024];
			while (bin.read(b) != -1) {
				bout.write(b);
			}
			bout.flush();
			System.out.println("copied [" + srcFile.getName() + "]    with    "
					+ srcFile.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bout != null) {
					bout.close();
					bout = null;
				}
			} catch (Exception e) {
			}
			try {
				if (bin != null) {
					bin.close();
					bin = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		BufferInputStreamDemo bd = new BufferInputStreamDemo();
		String src = "D:" + File.separator + "UltraEdit.zip";
		String des = "D:" + File.separator + "UltraEdit_Copy.zip";
		long sTime = System.currentTimeMillis();
		bd.copy(src, des);
		long eTime = System.currentTimeMillis();
		System.out.println("Total spend: " + (eTime - sTime));

	}

}
