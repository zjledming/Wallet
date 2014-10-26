package com.cts.elt.xml;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class Dom4jWriteXml {

	public void testWrite()  {
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer=null;

		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("root");

		Element element1 = root.addElement("user")
				.addAttribute("name", "zhangshan")
				.addAttribute("blog", "www.zhangshan.com").addText("�������ĵ�����");
		element1.setText("����");

		Element name = element1.addElement("��վ��ַ");
		name.setText("www.zhangshan.com");

		Element element2 = root.addElement("��վ").addText("JAVA����");
		element2.setText("www.zhangshan.com");

		Element node = root.element("user");
		Element newNode = node.addElement("����");
		newNode.setText("100");
		// end
		try{
		writer = new XMLWriter(new FileOutputStream("d:"
				+ File.separator + "dom4j.xml"),format);

		writer.write(document);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(writer!=null){
					writer.close();
					writer=null;
				}
			}catch(Exception e){}
		}

	}

	public static void main(String[] args) {
		Dom4jWriteXml writeXml = new Dom4jWriteXml();
		try {
			writeXml.testWrite();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
