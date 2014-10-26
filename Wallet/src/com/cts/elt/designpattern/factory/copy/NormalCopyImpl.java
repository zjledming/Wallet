package com.cts.elt.designpattern.factory.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class NormalCopyImpl implements FileCopy {

	@Override
	public void copy(String src, String des) {
		File srcFile = new File(src);
		File desFile = new File(des);
		InputStream in = null;
		OutputStream out = null;
		try {
			long sTime = System.currentTimeMillis();
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(desFile);
			byte[] b = new byte[(int) srcFile.length()];
			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) in.read();
			}
			out.write(b);
			System.out.println("copied [" + srcFile.getName() + "]    with    "
					+ srcFile.length());
			long eTime = System.currentTimeMillis();
			System.out.println("Total spend: " + (eTime - sTime));
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

}
