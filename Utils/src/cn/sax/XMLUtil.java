package cn.sax;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil {
	private static final Log LOG = LogFactory.getLog(XMLUtil.class);
	public static LinkMendProofDataConfigBean bean = null;
	public static List<ItemIndividualPageCfgBean> list = null;

	/**
	 * @Title: initObj
	 * @Description: file ———> 创建Document对象
	 * @return
	 * @throws Exception
	 * @return Document
	 * @throws
	 * @author XiMing.Fu
	 */
	private static Document initObj() throws Exception {
		File f = new File(RelatedPath.getWebAppPath(XMLUtil.class)
				+ "\\linkmendproof-config.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(f);
		} catch (Exception e) {
			LOG.error("初始化对象失败" + e);
			throw e;
		}
		return doc;
	}

	/**
	 * @Description 初始化房管局系统提供的中间库参数
	 * @return void
	 */
	public static void initLinkmendproofServer() {
		bean = new LinkMendProofDataConfigBean();
		Document doc;
		try {
			doc = initObj();
			setField(
					bean,
					"url",
					StringUtil.deNull(doc.getElementsByTagName("url").item(0)
							.getFirstChild().getNodeValue()));
			setField(
					bean,
					"username",
					StringUtil.deNull(doc.getElementsByTagName("username")
							.item(0).getFirstChild().getNodeValue()));
			setField(
					bean,
					"password",
					StringUtil.deNull(doc.getElementsByTagName("password")
							.item(0).getFirstChild().getNodeValue()));
			setField(
					bean,
					"port",
					StringUtil.deNull(doc.getElementsByTagName("port").item(0)
							.getFirstChild().getNodeValue()));
			setField(
					bean,
					"driverClass",
					StringUtil.deNull(doc.getElementsByTagName("driverClass")
							.item(0).getFirstChild().getNodeValue()));
			NamedNodeMap chargeSqlMap = doc.getElementsByTagName("charge-sql")
					.item(0).getAttributes();
			setField(bean, "chargeSql", StringUtil.deNull(chargeSqlMap
					.getNamedItem("value").getNodeValue()));
			NamedNodeMap linkmendproofSqlMap = doc
					.getElementsByTagName("linkmendproof-sql").item(0)
					.getAttributes();
			setField(bean, "linkmendproofSql",
					StringUtil.deNull(linkmendproofSqlMap.getNamedItem("value")
							.getNodeValue()));
			NamedNodeMap updatechargeSqlMap = doc
					.getElementsByTagName("updatecharge-sql").item(0)
					.getAttributes();
			setField(bean, "updateChargeSql",
					StringUtil.deNull(updatechargeSqlMap.getNamedItem("value")
							.getNodeValue()));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @Description 初始化地税事项和个性页面配置
	 * @return void
	 */
	public static void initLinkmendproofConfig() {
		list = new ArrayList<ItemIndividualPageCfgBean>();
		Document doc;
		try {
			doc = initObj();
			NodeList members = doc.getElementsByTagName("page-config");
			NodeList childNodes = members.item(0).getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				ItemIndividualPageCfgBean cfgBean = new ItemIndividualPageCfgBean();
				if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					if ("ecid".equals(childNodes.item(i).getNodeName())) {
						NamedNodeMap map = childNodes.item(i).getAttributes();
						cfgBean.setPage_path(StringUtil.deNull(map
								.getNamedItem("page").getNodeValue()));
						cfgBean.setEc_id(StringUtil.deNull(childNodes.item(i)
								.getFirstChild().getNodeValue()));
						list.add(cfgBean);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @Title: setField
	 * @Description: 给bean对象的字段fieldname设置值value
	 * @param bean
	 * @param fieldname
	 * @param value
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	public static void setField(Object bean, String fieldname, String value) {
		Class c = bean.getClass();
		Field field;
		try {
			// 取字段：返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
			field = c.getDeclaredField(fieldname);
			// 设置访问权限：将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java
			// 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
			field.setAccessible(true);
			try {
				// 给当前对象的字段field设置新值：将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
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

	/**
	 * 动态的set参数
	 * 
	 * @param paramMap
	 * @param preparedStatement
	 * @param xmlTags
	 */
	public static void setPrepareValue(Map<String, Object> paramMap,
			PreparedStatement preparedStatement, String xmlTags) {
		try {
			List<Map<String, String>> strList = getValueStr(xmlTags);
			String filedname = "";
			for (int i = 0; i < strList.size(); i++) {
				filedname = strList.get(i).get("filedname");
				preparedStatement.setString(i + 1,
						(String) paramMap.get(filedname));
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 解析参数
	 * 
	 * @param xmlTags
	 * @return
	 */
	private static List<Map<String, String>> getValueStr(String xmlTags) {
		List<Map<String, String>> strList = new ArrayList<Map<String, String>>();
		Document doc;
		try {
			doc = initObj();
			NodeList members = doc.getElementsByTagName(xmlTags);
			NodeList childNodes = members.item(0).getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Map<String, String> strMap = new HashMap<String, String>();
					NamedNodeMap map = childNodes.item(i).getAttributes();
					strMap.put("type", map.getNamedItem("type").getNodeValue());
					strMap.put("method", map.getNamedItem("method")
							.getNodeValue());
					strMap.put("filedname", map.getNamedItem("filedname")
							.getNodeValue());
					strList.add(strMap);
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return strList;
	}

	public static Map<String, String> getZWZXAccessControl() {
		Map<String, String> map = new HashMap<String, String>();
		String path = RelatedPath.getWebAppPath(XMLUtil.class);
		File f = new File(path + "WEB-INF/zwzxaccesscontrol.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc;
		try {
			builder = factory.newDocumentBuilder();
			try {
				doc = builder.parse(f);
				NodeList members = doc.getElementsByTagName("url");
				for (int i = 0; i < members.getLength(); i++) {
					NamedNodeMap text = members.item(i).getAttributes();
					map.put(text.item(0).getNodeValue(), text.item(1)
							.getNodeValue());
					System.out.println(text.item(0).getNodeValue() + ":"
							+ text.item(1).getNodeValue());
				}
				return map;
			} catch (SAXException e) {
				LOG.error(e);
				return map;
			} catch (IOException e) {
				LOG.error(e);
				return map;
			}
		} catch (ParserConfigurationException e) {
			LOG.error(e);
			return map;
		}

	}
}