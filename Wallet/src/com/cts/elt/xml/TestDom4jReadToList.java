package com.cts.elt.xml;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDom4jReadToList {

	/**
	 * @param args
	 */
	public void test() {
		List<StudentBean> stdList = new ArrayList<StudentBean>();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		URL url = classLoader.getResource("StudentInfo.xml");
		if (url == null) {
			classLoader = ClassLoader.getSystemClassLoader();
			url = classLoader.getResource("studentInfo.xml");
		}
		File file = new File(url.getFile());
		Dom4jReadXmlToList dr = new Dom4jReadXmlToList();
		stdList = dr.iterateWholeXML(file.getPath());
		System.out.println("姓名\t年龄\t学院\t学院领导\t电话\t\t备注");
		for (StudentBean s : stdList) {
			System.out.println("");
			System.out.print(s.getName() + "\t");
			System.out.print(s.getAge() + "\t");
			System.out.print(s.getColleage() + "\t");
			System.out.print("null" + "\t");
			System.out.print(s.getTelno() + "\t");
			System.out.print(s.getRemark() + "\t");
		}
	}

	public static void main(String[] args) {
		TestDom4jReadToList tdr = new TestDom4jReadToList();
		tdr.test();

	}

}
