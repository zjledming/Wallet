package com.cts.elt.io;

import java.io.*;

public class CreateFile {

	/**
	 * @param args
	 */
	public void createFile() {
		String fileName = "D:" + File.separator + "hello.txt";
		File f = new File(fileName);
		try {
			f.createNewFile();
			System.out.println("create file success");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteFile() {

		String fileName = "D:" + File.separator + "hello.txt";
		File f = new File(fileName);
		if (f.exists()) {
			f.delete();
			System.out.println("file has been deleted");
		} else {
			System.out.println("can not find this file!");
		}
	}

	public static void main(String[] args) {
		CreateFile cf = new CreateFile();
		cf.createFile();
		cf.deleteFile();
	}

}
