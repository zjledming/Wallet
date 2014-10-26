package com.cts.elt.io;

import java.io.*;

public class ListMyDirWithSubDir {

	/**
	 * @param args
	 */
	public void print(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				File[] fileArray = f.listFiles();
				if (fileArray != null) {
					for (int i = 0; i < fileArray.length; i++) {
						print(fileArray[i]);
					}
				}
			} else {
				System.out.println(f);
			}
		}
	}

	public static void main(String[] args) {
		String fileName = "D:" + File.separator + "tomcat2";
		File f = new File(fileName);
		ListMyDirWithSubDir listDir = new ListMyDirWithSubDir();
		listDir.print(f);

	}
}
