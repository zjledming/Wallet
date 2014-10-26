package com.cts.elt.designpattern.factory.copy;

public abstract class FileCopyFactory {

	public abstract FileCopy getCopySub();

	public static FileCopyFactory getInstance(String className) {
		try {
			return (FileCopyFactory) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
