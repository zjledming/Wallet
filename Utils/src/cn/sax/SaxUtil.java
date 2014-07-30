package cn.sax;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SaxUtil {
	private static final Log LOG = LogFactory.getLog(SaxUtil.class);

	/**
	 * 通过反射机制将XML格式的字符串赋值给对像
	 * 
	 * @param xml
	 * @param bean
	 * @return
	 */
	public static void fromXmlToBean(String xml, Object bean) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc;
		try {
			builder = factory.newDocumentBuilder();
			try {
				doc = builder.parse(new InputSource(new StringReader(xml)));
				Element root = doc.getDocumentElement();
				String fieldname;
				// 子节点
				NodeList childList = root.getChildNodes();
				for (int i = 0; i < childList.getLength(); i++) {
					Node childNode = childList.item(i);
					// 判断取出的值是否属于Element元素,目的是过滤掉
					if (childNode instanceof Element) {
						// 得到子节点的名字
						String nodename = childNode.getNodeName();
						String value = childNode.getTextContent();
						fieldname = nodename.substring(0, 1).toLowerCase()
								+ nodename.substring(1);
						setField(bean, fieldname, value);
					}
				}
			} catch (SAXException e) {
				LOG.error(e);
			} catch (IOException e) {
				LOG.error(e);
			}
		} catch (ParserConfigurationException e) {
			LOG.error(e);
		}
	}

	public static void setField(Object bean, String fieldname, String value) {
		Class c = bean.getClass();
		Field field;
		try {
			field = c.getDeclaredField(fieldname);
			field.setAccessible(true);
			try {
				field.set(bean, value);
			} catch (IllegalArgumentException e) {
				LOG.error(e);
			} catch (IllegalAccessException e) {
				LOG.error(e);
			}
		} catch (SecurityException e) {
			LOG.error(e);
		} catch (NoSuchFieldException e) {
			LOG.error(e);
		}

	}

}
