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
				.addAttribute("blog", "www.zhangshan.com").addText("我是中文的名字");
		element1.setText("博客");

		Element name = element1.addElement("网站地址");
		name.setText("www.zhangshan.com");

		Element element2 = root.addElement("网站").addText("JAVA博客");
		element2.setText("www.zhangshan.com");

		Element node = root.element("user");
		Element newNode = node.addElement("流量");
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
