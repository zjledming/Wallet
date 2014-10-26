package com.cts.elt.designpattern.factory.copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedCopyImpl implements FileCopy {

	@Override
	public void copy(String src, String des) {
		File srcFile = new File(src);
		File desFile = new File(des);
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			long sTime = System.currentTimeMillis();
			bin = new BufferedInputStream(new FileInputStream(srcFile));
			bout = new BufferedOutputStream(new FileOutputStream(desFile));
			byte[] b = new byte[1024];
			while (bin.read(b) != -1) {
				bout.write(b);
			}
			bout.flush();
			System.out.println("copied [" + srcFile.getName() + "]    with    "
					+ srcFile.length());
			long eTime = System.currentTimeMillis();
			System.out.println("Total spend: " + (eTime - sTime));
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

}
