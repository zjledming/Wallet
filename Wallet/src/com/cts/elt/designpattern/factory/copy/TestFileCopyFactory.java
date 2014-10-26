package com.cts.elt.designpattern.factory.copy;

import java.io.File;

public class TestFileCopyFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String src = "D:" + File.separator + "UltraEdit.zip";
		String des = "D:" + File.separator + "UltraEdit_Copy.zip";
		FileCopy normalCopy = FileCopyFactory.getInstance(
				"com.cts.elt.designpattern.factory.copy.NormalCopyFactory")
				.getCopySub();
		//normalCopy.copy(src, des);
		FileCopy bufferedCopy = FileCopyFactory.getInstance(
				"com.cts.elt.designpattern.factory.copy.BufferedCopyFactory")
				.getCopySub();
		bufferedCopy.copy(src, des);

	}

}
