package com.cts.elt.xml;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.net.URL;

public class SaxReaderSample extends DefaultHandler {
	public void startDocument() throws SAXException {
		System.out.println("SAX Event: START DOCUMENT");
	}

	public void endDocument() throws SAXException {
		System.out.println("SAX Event: END DOCUMENT");
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		System.out.println("SAX Event: START ELEMENT[ " + localName + " ]");
		for (int i = 0; i < attr.getLength(); i++) {
			System.out.println(" ATTRIBUTE: " + attr.getLocalName(i)
					+ " VALUE: " + attr.getValue(i));
		}

	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		System.out.println("SAX Event: END ELEMENT[ " + localName + " ]");
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		System.out.print("SAX Event: CHARACTERS[ ");

		try {
			OutputStreamWriter outw = new OutputStreamWriter(System.out);
			outw.write(ch, start, length);
			outw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(" )");

	}

	public static void main(String[] argv) {

		System.out.println("Example1 SAX Events:");
		try {

			// SAXParserFactory spFactory = SAXParserFactory.newInstance();
			// SAXParser sParser = spFactory.newSAXParser();
			XMLReader xr = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
			xr.setContentHandler(new SaxReaderSample());
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			URL url = classLoader.getResource("PersonInfo.xml");
			if (url == null) {
				classLoader = ClassLoader.getSystemClassLoader();
				url = classLoader.getResource("PersonInfo.xml");
			}
			File file = new File(url.getFile());
			xr.parse(new InputSource(new FileReader(file.getPath())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
