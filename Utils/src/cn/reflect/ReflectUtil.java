package cn.reflect;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.junit.Test;

import cn.com.service.Helloworld;

/**
 * @ClassName: ReflectUtil
 * @Description: 反射工具类
 * @author XiMing.Fu
 * @date 2014-3-10 下午02:19:48
 * 
 */
public class ReflectUtil {
	private static final Logger LOG = Logger.getLogger(ReflectUtil.class);

	/**
	 * @Title: executeMethod
	 * @Description:执行类className中的方法method，参数为param
	 * @param className
	 * @param method
	 * @param param
	 * @return
	 * @return Object
	 * @throws
	 * @author XiMing.Fu
	 */
	public static Object executeMethod(String className, String method,
			String param) {
		if (className == null || "".equals(className) || method == null
				|| "".equals(className)) {
			return null;
		}
		try {
			Class<?> obj = Class.forName(className);
			Method mt = obj.getMethod(method, String.class);
			return mt.invoke(obj.newInstance(), param);
		} catch (Exception e) {
			LOG.error("socket接收消息回调方法错误：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取实例
	 * 
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public Helloworld getEngineStartupListener(String className)
			throws Exception {
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		if (!(obj instanceof Helloworld)) {
			throw new Exception("类型错误");
		}
		return (Helloworld) obj;
	}

	/**
	 * @Title: getClassFile
	 * @Description: 获取class文件
	 * @param clazz
	 * @return
	 * @return File
	 * @throws
	 * @author XiMing.Fu
	 */
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

	public static File getClassPathFile(Class<?> clazz) {
		File file = getClassFile(clazz);
		for (int i = 0, count = clazz.getName().split("[.]").length; i < count; i++)
			file = file.getParentFile();
		if (file.getName().toUpperCase().endsWith(".JAR!")) {
			file = file.getParentFile();
		}
		return file;
	}

	public static String getClassPath(Class<?> clazz)
			throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(getClassPathFile(clazz)
				.getAbsolutePath(), "UTF-8");

	}

	public static Object getClassInstance(String classPath)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Class<?> t = Class.forName(classPath); // 反射类的Class对象
		Object res = t.newInstance(); // 生成反射类
		return res;
	}

	/**
	 * <b>Summary: </b> getBeanDButil(通过反射机制将Element中的值赋值到object中)
	 * 
	 * @param e
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static Object getBeanDButil(Element e, Object vo) throws Exception {
		for (Iterator iterInner = e.elementIterator(); iterInner.hasNext();) {
			Element elementInner = (Element) iterInner.next();
			String rootName = elementInner.getName();// 节点的名称，如title
			String rootValue = elementInner.getText();// 节点的内容，如title标签里的内容
			Method method = vo.getClass().getMethod(
					"set" + getSetterName(rootName),
					new Class[] { Class.forName("java.lang.String") });
			method.invoke(vo, new Object[] { rootValue });
		}
		return vo;
	}

	/**
	 * 转换列名成getter形式 如：COLUMN_NAME 转换成Column_name
	 * 
	 * @param columnName
	 * @return
	 */
	public static String getSetterName(String columnName) {
		String head = "";
		String tail = "";
		int len = columnName.length();
		head = columnName.substring(0, 1).toUpperCase();
		if (len > 1) {
			tail = columnName.substring(1).toLowerCase();
		}
		return head + tail;
	}

	/**
	 * @Title: method
	 * @Description: 执行cls对象的字段fieldName的get方法
	 * @param cls
	 * @param fieldName
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @author XiMing.Fu
	 */
	public String method(Class cls, String fieldName) throws Exception {
		fieldName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method meth = cls.getDeclaredMethod("get" + fieldName, new Class[0]);
		return (String) meth.invoke(cls, new Object[] {});
	}
	
	@Test
	public void getPath(){
		
		System.out.println(ReflectUtil.class.getClassLoader());
	}

	public static void main(String[] args) {
		executeMethod(
				"com.chinacreator.v2.xzsp.charge.service.impl.SocketMsgServiceImpl",
				"responseMessage",
				"<MSG><MsgID>4</MsgID><MsgClass>1</MsgClass><CmdType>1001</CmdType></MSG>");
	}
}
