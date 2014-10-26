package com.cts.elt.xml;

import java.io.File;
import java.net.URL;
import java.util.HashMap;


public class TestDom4jReadExmple {
	public static void main(String[] args) {
		try {

			HashMap<String, String> hashMap;
			Dom4jReadExmple drb = new Dom4jReadExmple();
			hashMap = new HashMap<String, String>();
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			URL url = classLoader.getResource("StudentInfo.xml");
			if (url == null) {
				classLoader = ClassLoader.getSystemClassLoader();
				url = classLoader.getResource("studentInfo.xml");
			}
			File file = new File(url.getFile());
			drb.iterateWholeXML(file.getPath(), hashMap);
			System.out.println("����\t����\tѧԺ\tѧԺ�쵼\t�绰\t\t��ע");
			for (int i = 0; i < hashMap.size(); i += 6) {
				int j = i / 6;
				System.out.print(hashMap.get("name" + j) + "\t");
				System.out.print(hashMap.get("student-age" + j) + "\t");
				System.out.print(hashMap.get("college" + j) + "\t");
				System.out.print(hashMap.get("college-leader" + j) + "\t");
				System.out.print(hashMap.get("telephone" + j) + "\t");
				System.out.println(hashMap.get("notes" + j) + "\t");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
