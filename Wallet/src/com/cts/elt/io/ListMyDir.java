package com.cts.elt.io;

import java.io.*;

public class ListMyDir {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "D:" + File.separator + "tomcat2";
		File f = new File(fileName);
		File[] fs = f.listFiles();
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].getName());
		}

	}

}
