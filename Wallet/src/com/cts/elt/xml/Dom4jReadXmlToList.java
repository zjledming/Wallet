package com.cts.elt.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.*;

public class Dom4jReadXmlToList {
	public List<StudentBean> iterateWholeXML(String filename) {
		List<StudentBean> stdList = new ArrayList<StudentBean>();
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			int num = -1;
			for (Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				Attribute ageAttr = element.attribute("age");
				StudentBean std = new StudentBean();
				for (Iterator iterInner = element.elementIterator(); iterInner
						.hasNext();) {
					String age = "";
					if (ageAttr != null) {
						age = ageAttr.getValue();
					} else {
						age = "null";
					}
					std.setAge(age);
					Element elementInner = (Element) iterInner.next();
					if (elementInner.getName().equals("name")) {
						std.setName(elementInner.getText());
					} else if (elementInner.getName().equals("college")) {
						std.setColleage(elementInner.getText());
					} else if (elementInner.getName().equals("telephone")) {
						std.setTelno(elementInner.getText());
					} else if (elementInner.getName().equals("notes")) {
						std.setRemark(elementInner.getText());
					}
				}
				stdList.add(std);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stdList;
	}
}
