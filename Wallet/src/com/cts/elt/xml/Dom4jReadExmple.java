package com.cts.elt.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jReadExmple {
	public void iterateWholeXML(String filename, HashMap<String, String> hm) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			int num = -1;
			for (Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				num++;
				Attribute ageAttr = element.attribute("age");
				if (ageAttr != null) {
					String age = ageAttr.getValue();
					if (age != null && !age.equals("")) {
						hm.put(element.getName() + "-" + ageAttr.getName()
								+ num, age);
					} else {
						hm.put(element.getName() + "-" + ageAttr.getName()
								+ num, "20");
					}
				} else {
					hm.put(element.getName() + "-age" + num, "20");
				}
				for (Iterator iterInner = element.elementIterator(); iterInner
						.hasNext();) {
					Element elementInner = (Element) iterInner.next();
					hm.put(elementInner.getName() + num,
							elementInner.getText());
				}
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
