package com.cts.elt.xml.jaxb;

import javax.xml.bind.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class TestJaxbWrite {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		PersonList pList = new PersonList();
		PersonList.Person p = new PersonList.Person();
		p.setId("101");
		p.setName("abcdefg");
		PersonList.Person.Address address = new PersonList.Person.Address();
		address.setCity("sh");
		address.setStreet("sh street");
		address.setZip("200000");
		pList.getPerson().add(p);
		p = new PersonList.Person();
		p.setId("102");
		p.setName("defg123");
		address.setCity("sh");
		address.setStreet("sicuan street");
		address.setZip("200000");
		pList.getPerson().add(p);
		JAXBContext jc = JAXBContext.newInstance(PersonList.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);

		marshaller.marshal(pList, new FileOutputStream("test.xml"));
	}

}
