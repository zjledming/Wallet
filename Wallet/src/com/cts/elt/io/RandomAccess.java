package com.cts.elt.io;

import java.io.*;

public class RandomAccess {
	public void writeToFile() {
		String fileName = "D:" + File.separator + "hello.txt";
		RandomAccessFile randomIO = null;
		try {

			File f = new File(fileName);
			randomIO = new RandomAccessFile(f, "rw");
			randomIO.writeBytes("asdsad");
			randomIO.writeInt(12);
			randomIO.writeBoolean(true);
			randomIO.writeChar('A');
			randomIO.writeFloat(1.21f);
			randomIO.writeDouble(12.123);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (randomIO != null) {
					randomIO.close();
					randomIO = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		RandomAccess randomA = new RandomAccess();
		randomA.writeToFile();
	}
}
