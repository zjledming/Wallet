import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import cn.com.bean.NoteProterties;

public class Dom4jutil {

	private static final Log LOG = LogFactory.getLog(Dom4jutil.class);

	/**
	 * 解析xml文件时生成document
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 * @throws DocumentException
	 */
	protected synchronized Document file2Document(String path)
			throws FileNotFoundException, MalformedURLException,
			DocumentException {
		SAXReader reader = null;
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException(path + " not exist");
		}
		if (reader == null) {
			reader = new SAXReader();
		}
		return reader.read(new FileInputStream(file));

	}

	/**
	 * 解析string类型xml时,获取根节点
	 * 
	 * @param str
	 * @return
	 * @throws DocumentException
	 */
	protected synchronized Element getRoot2ParseText(String str)
			throws DocumentException {
		return DocumentHelper.parseText(str).getRootElement();
	}

	/**
	 * 生成xml文件
	 * 
	 * @param doc
	 * @param encode
	 *            编码
	 * @param filePath
	 *            生成的xml文件路径
	 * @throws IOException
	 */
	protected synchronized void writerXmlFile(Document doc, String encode,
			String filePath) throws IOException {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encode);
			writer = new XMLWriter(new FileWriter(new File(filePath)), format);
			writer.write(doc);
			writer.flush();
		} finally {
			closeXmlWriter(writer);
			clear(doc);
		}
	}

	/**
	 * 生成xml string
	 * 
	 * @param doc
	 * @param encode
	 *            编码
	 * @return
	 * @throws IOException
	 */
	protected synchronized String writerXmlString(Document doc, String encode)
			throws IOException {
		XMLWriter writer = null;
		StringWriter sw = null;
		try {
			sw = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encode);
			writer = new XMLWriter(format);
			writer.setWriter(sw);
			writer.write(doc);
			writer.flush();
			sw.flush();
		} finally {
			closeXmlWriter(writer);
			closeStringWriter(sw);
			clear(doc);
		}
		return sw.toString();
	}

	/**
	 * 关闭XMLWriter
	 * 
	 * @param writer
	 */
	protected synchronized void closeXmlWriter(XMLWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				LOG.debug(e);
			}
		}
	}

	/**
	 * 关闭StringWriter
	 * 
	 * @param sw
	 */
	protected synchronized void closeStringWriter(StringWriter sw) {
		if (sw != null) {
			try {
				sw.close();
			} catch (IOException e) {
				LOG.debug(e);
			}
		}
	}

	/**
	 * 清空资源
	 * 
	 * @param doc
	 */
	protected synchronized void clear(Document doc) {
		if (doc != null) {
			doc.clearContent();
		}
	}

	/**
	 * 解析配置文件 默认为dzjc-config.xml
	 * 
	 * @param configFile
	 * @throws Exception
	 */
	public void parse(String configFile) throws Exception {
		System.out.println(Dom4jutil.class.getClassLoader());
		SAXReader reader = new SAXReader();
		File configure = new File(configFile);
		// 查找带有给定名称的资源
		URL confURL = Dom4jutil.class.getClassLoader().getResource(configFile);
		if (confURL == null)
			Dom4jutil.class.getClassLoader().getResource(
					(new StringBuilder("/")).append(configFile).toString());
		if (confURL == null)
			getTCL().getResource(configFile);
		if (confURL == null)
			getTCL().getResource(
					(new StringBuilder("/")).append(configFile).toString());
		if (confURL == null)
			confURL = ClassLoader.getSystemResource(configFile);
		if (confURL == null)
			confURL = ClassLoader.getSystemResource((new StringBuilder("/"))
					.append(configFile).toString());
		Document document = reader.read(confURL); // 加载配置文档
		parseSystemConfig(document); // 解析系统常用配置
	}

	/*
	 * 获取当前类的类加载器
	 * 
	 * @return
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws InvocationTargetException
	 */
	private ClassLoader getTCL() throws IllegalAccessException,
			InvocationTargetException {
		Method method = null;
		try {
			method = Thread.class.getMethod("getContextClassLoader", null);
		} catch (NoSuchMethodException e) {
			return null;
		}
		return (ClassLoader) method.invoke(Thread.currentThread(), null);
	}

	/*
	 * 解析系统常用配置 将配置信息装载在Map容器中，供外界使用
	 * 
	 * @param document
	 * 
	 * @throws Exception
	 */
	private void parseSystemConfig(Document document) throws Exception {
		Map systemConfig = new HashMap();
		LOG.info("【解析系统常用配置信息】开始");
		// selectNodes计算XPath表达式并返回结果的列表节点实例或根据XPath字符串实例
		List<Element> elemList = document
				.selectNodes("//config//system-config//element");
		String elemName = null;
		String elemValue = null;
		for (int i = 0; i < elemList.size(); i++) {
			elemName = elemList.get(i).attributeValue("name");
			elemValue = elemList.get(i).attributeValue("value");
			LOG.info("【解析系统常用配置信息】第" + (i + 1) + "个节点：name=" + elemName
					+ " value=" + elemValue);
			systemConfig.put(elemName, elemValue);
		}
		LOG.info("【解析系统常用配置信息】结束");
	}

	public void parseDataSource(String xmlPath, String[] dzjc, String[] middle,
			String[] r_middle, String[] sjdzjc, String[] wszwfw, String[] xndt)
			throws DocumentException, IOException {
		Document doc = file2Document(xmlPath);
		Element root = doc.getRootElement();
		List item = root.elements("connection");
		Iterator<Element> it = item.iterator();
		while (it.hasNext()) {
			Element conn = it.next();
			String name = conn.element("name").getTextTrim();
			String server = conn.element("server").getTextTrim();
			if ("dzjc".equals(name)) {
				conn.element("server").setText(dzjc[0]);
				conn.element("database").setText(dzjc[1]);
				conn.element("username").setText(dzjc[2]);
				conn.element("password").setText(dzjc[3]);
				conn.element("type").setText(dzjc[4]);
				conn.element("port").setText(dzjc[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(dzjc[5]);
						break;
					}
				}
			}
			if ("middle".equals(name)) {
				conn.element("server").setText(middle[0]);
				conn.element("database").setText(middle[1]);
				conn.element("username").setText(middle[2]);
				conn.element("password").setText(middle[3]);
				conn.element("type").setText(middle[4]);
				conn.element("port").setText(middle[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(middle[5]);
						break;
					}
				}
			}
			if ("r_middle".equals(name)) {
				conn.element("server").setText(r_middle[0]);
				conn.element("database").setText(r_middle[1]);
				conn.element("username").setText(r_middle[2]);
				conn.element("password").setText(r_middle[3]);
				conn.element("type").setText(r_middle[4]);
				conn.element("port").setText(r_middle[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(r_middle[5]);
						break;
					}
				}
			}
			if ("sjdzjc".equals(name)) {
				conn.element("server").setText(sjdzjc[0]);
				conn.element("database").setText(sjdzjc[1]);
				conn.element("username").setText(sjdzjc[2]);
				conn.element("password").setText(sjdzjc[3]);
				conn.element("type").setText(sjdzjc[4]);
				conn.element("port").setText(sjdzjc[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(sjdzjc[5]);
						break;
					}
				}
			}
			if ("wszwfw".equals(name)) {
				conn.element("server").setText(wszwfw[0]);
				conn.element("database").setText(wszwfw[1]);
				conn.element("username").setText(wszwfw[2]);
				conn.element("password").setText(wszwfw[3]);
				conn.element("type").setText(wszwfw[4]);
				conn.element("port").setText(wszwfw[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(wszwfw[5]);
						break;
					}
				}
			}
			if ("xndt".equals(name)) {
				conn.element("server").setText(xndt[0]);
				conn.element("database").setText(xndt[1]);
				conn.element("username").setText(xndt[2]);
				conn.element("password").setText(xndt[3]);
				conn.element("type").setText(xndt[4]);
				conn.element("port").setText(xndt[5]);
				List attrList = conn.element("attributes")
						.elements("attribute");
				Iterator<Element> attrs = attrList.iterator();
				while (attrs.hasNext()) {
					Element attr = attrs.next();
					String code = attr.element("code").getTextTrim();
					if ("PORT_NUMBER".equals(code)) {
						attr.element("attribute").setText(xndt[5]);
						break;
					}
				}
			}

		}
		writerXmlFile(doc, "utf-8", xmlPath);
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */

	@SuppressWarnings("unchecked")
	public void test() throws FileNotFoundException, MalformedURLException,
			DocumentException {
		File file = new File("c:\\123.xml");
		String ids = "";
		if (!file.exists()) {
			throw new FileNotFoundException("file not exist");
		}
		SAXReader reader = new SAXReader();
		Element element = reader.read(file).getDocument().getRootElement();
		List<Element> list = element.elements("ROW");
		Iterator<Element> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			Element rowElement = it.next();
			String recordId = rowElement.element("RECORD_ID").getTextTrim();
			String contentString = rowElement.element("PERMISSION_CONDITION")
					.getTextTrim();

			if (contentString.contains("？")) {
				i++;
				try {
					contentString = contentString.replaceAll("？", "");
					System.out.println(contentString);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		// id content table
		System.out.println(i);
	}

	public static File getClassFile(Class<?> clazz) {
		URL path = clazz.getResource(clazz.getName().substring(
				clazz.getName().lastIndexOf(".") + 1)
				+ ".class");
		if (path == null) {
			String name = clazz.getName().replaceAll("[.]", "/");
			path = clazz.getResource("/" + name + ".class");
		}
		return new File(path.getFile());
	}

	public static String getClassPath(Class<?> clazz)
			throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(getClassPathFile(clazz)
				.getAbsolutePath(), "UTF-8");

	}

	public static File getClassPathFile(Class<?> clazz) {
		File file = getClassFile(clazz);
		for (int i = 0, count = clazz.getName().split("[.]").length; i < count; i++)
			file = file.getParentFile();
		if (file.getName().toUpperCase().endsWith(".JAR!")) {
			file = file.getParentFile();
		}
		return file;
	}

	@Test
	public void setNoteProperties() throws Exception {
		System.out.println(getClassPath(Dom4jutil.class)); // D:\WorkSpaceMing\Utils\bin
		String PATH_NAME = "\\note.xml";

		String LOADED = "false";

		NoteProterties properties = null;
		Document doc = null;
		try {
			File file = new File(getClassPath(Dom4jutil.class) + PATH_NAME);
			if (!file.exists()) {
				throw new FileNotFoundException("note.xml not exist");
			}
			SAXReader reader = new SAXReader();
			doc = reader.read(file);
			Element root = doc.getRootElement();
			properties = new NoteProterties();
			// 取根节点下的note-used
			if (LOADED.equals(root.elementTextTrim("note-used"))) {
				properties.setNoteUsed(true);
				properties.setNoteAttemptTimes(root
						.elementTextTrim("attempt-times"));
				Map<String, Object> map = new HashMap<String, Object>();
				// 取root下的orgs下的org节点集合
				List orgs = root.element("orgs").elements("org");
				Iterator<Element> orgsIterator = orgs.iterator();
				while (orgsIterator.hasNext()) {
					Element e = orgsIterator.next();
					String param_class = e.attributeValue("param_class");
					String org_code = e.attributeValue("org_code");
					String reflect_class = e.attributeValue("reflect_class");

					// AbstractParamBean abstractParamBean = (AbstractParamBean)
					// NoteUtil
					// .getClassInstance(param_class);
					// abstractParamBean.setParam_class(param_class);
					// abstractParamBean.setReflect_class(reflect_class);
					//
					// abstractParamBean = (AbstractParamBean) NoteUtil
					// .getBeanDButil(e, abstractParamBean);
					// map.put(org_code, abstractParamBean);
				}
				properties.setMap(map);
				LOG.info("成功加载短信配置信息");
			} else {
				properties.setNoteUsed(false);
			}
		} finally {

		}
	}

	/**
	 * 获取配置文件中角色的名字
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private static List<String> getRoleName() throws DocumentException,
			IOException {
		List<String> roleList = new ArrayList<String>();
		String path = getClassPath(Dom4jutil.class) + "/rolesSP.xml";
		SAXReader reader = new SAXReader();
		File file = new File(path);
		Document doc = reader.read(file);
		// 取/root/roles/name属性值
		List list1 = doc.selectNodes("/root/roles/name"); // XPath
		for (int i = 0; i < list1.size(); i++) {
			Element e1 = (Element) list1.get(i);
			String name = e1.getText();
			System.out.println(name);
			roleList.add(name);
		}
		return roleList;
	}

	/**
	 * 获取配置文件中用于比较的角色的名字
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private static List<String> findColumnList() throws DocumentException,
			IOException {
		List<String> columnList = new ArrayList<String>();
		String path = getClassPath(Dom4jutil.class) + "/rolesSP.xml";
		SAXReader reader = new SAXReader();
		File file = new File(path);
		Document doc = reader.read(file);
		List list1 = doc.selectNodes("/root/column/name"); // XPath
		for (int i = 0; i < list1.size(); i++) {
			Element e1 = (Element) list1.get(i);
			String name = e1.getText();
			System.out.println(name);
			columnList.add(name);
		}
		return columnList;
	}

	/**
	 * 根据结点名获取结点值
	 * 
	 * @param doc
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	public static String getTextValueFromDoc(Document doc, String nodeName)
			throws Exception {
		LOG.info("[提示]获取结点" + nodeName + "的值开始...");
		List nodeList = getNodeListByName(doc, nodeName);
		if (nodeList != null && !nodeList.isEmpty()) {
			Element elem = (Element) nodeList.get(0);
			String origTextValue = elem.getText();
			// return new String(Base64.base64_decode(origTextValue));
		}
		LOG.info("[警告]结点" + nodeName + "没取到值！");
		return "";
	}

	/**
	 * 根据结点名设置结点值
	 * 
	 * @param doc
	 * @param nodeName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static void setTextValueFromDoc(Document doc, String nodeName,
			String value) throws Exception {
		LOG.info("[提示]设置结点" + nodeName + "的值开始...");
		List nodeList = getNodeListByName(doc, nodeName);
		if (nodeList != null && !nodeList.isEmpty()) {
			Element elem = (Element) nodeList.get(0);
			// elem.setText(Base64.base64_encode(value.getBytes()));

		}
		LOG.info("[警告]结点" + nodeName + "未设置到值！");
	}

	/**
	 * 根据结点名称获取结点列表
	 * 
	 * @param doc
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	public static List getNodeListByName(Document doc, String nodeName)
			throws Exception {
		String realNodeName = "//*[name()='ns:" + nodeName + "']";
		List list = doc.selectNodes(realNodeName);
		if (list.size() == 0) {
			realNodeName = "//*[name()='" + nodeName + "']";
			list = doc.selectNodes(realNodeName);
		}
		return list;
	}

	public static void main(String[] args) throws DocumentException,
			IOException {
		getRoleName();
		findColumnList();
	}

}
