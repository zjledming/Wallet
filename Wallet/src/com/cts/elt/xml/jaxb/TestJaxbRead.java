package com.cts.elt.xml.jaxb;

import javax.xml.bind.*;
import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class TestJaxbRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		URL url = classLoader.getResource("Person.xml");
		if (url == null) {
			classLoader = ClassLoader.getSystemClassLoader();
			url = classLoader.getResource("Person.xml");
		}
		File file = new File(url.getFile());
		JAXBContext jc = JAXBContext.newInstance(PersonList.class);
		Unmarshaller u = jc.createUnmarshaller();
		PersonList pList = (PersonList) u.unmarshal(new FileInputStream(file));
		for (Iterator it = pList.getPerson().iterator(); it.hasNext();) {
			PersonList.Person p = (PersonList.Person) it.next();
			System.out.println("[" + p.getId() + "][" + p.getName() + "]["
					+ p.getAddress().getCity() + "]["
					+ p.getAddress().getStreet() + "]["
					+ p.getAddress().getZip() + "]");

		}

		// // JAXBContext jc = JAXBContext.newInstance("mycompany.demo");
		// Marshaller marshaller = jc.createMarshaller();
		// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
		// Boolean.FALSE);
		// customerE.setValue(cust);
		// marshaller.marshal(customerE, new FileOutputStream("test1.xml"));

	}
}
