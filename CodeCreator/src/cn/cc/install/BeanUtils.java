package cn.cc.install;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import cn.cc.db.JDBCCRUD;
import cn.cc.db.Table;
import cn.cc.dialog.Dialog;
import cn.cc.util.Constant;
import cn.cc.util.StringUtil;

/**
 * @author ximing.fu<br>
 * @date 2014-6-29
 */
public class BeanUtils {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	private Bean bean;
	private Annotation annotation;

	public BeanUtils() {
		bean = new Bean();
		annotation = new Annotation();
	}

	/**
	 * 供外包调用入口
	 * 
	 * @param temp
	 * @return
	 */
	public static boolean execute(Bean temp) {
		try {
			String descPath = temp.getBeanUrl();
			String cName = temp.getName().toLowerCase();
			/*
			 * 根绝cName判断范围： 1.xxx:单表 2.xxx,xxx,...：多表 3."":全库
			 */
			String[] tablearr = cName.split(",");
			if (StringUtil.isBlank(cName)) {
				// 全库
				List<Bean> tables = JDBCCRUD.queryALLTableName("");
				for (int i = 0; i < tables.size(); i++) {
					create(descPath, tables.get(i));
				}
			} else {
				if (tablearr.length <= 1) {
					// 单表
					List<Bean> tables = JDBCCRUD.queryALLTableName(cName);
					create(descPath, tables.get(0));
				} else if (tablearr.length > 1
						&& !StringUtil.isBlank(tablearr[1])) {
					// 多表
					for (int i = 0; i < tablearr.length; i++) {
						List<Bean> tables = JDBCCRUD
								.queryALLTableName(tablearr[i]);
						create(descPath, tables.get(0));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 生成代码之前的准备工作：1.校验表cName是否
	 * 
	 * @param cName
	 * @return
	 */
	private static boolean beforeCreate(String cName) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 生成代码入口
	 * 
	 * @param descPath
	 * @param cName
	 * @throws Exception
	 */
	private static String create(String descPath, Bean bean) throws Exception {
		BeanUtils beanUtils = new BeanUtils();
		bean.setBeanUrl(descPath);
		beanUtils.init(bean);
		String cName = bean.getName().toLowerCase();
		// 路径转为小写
		descPath = descPath.toLowerCase();
		// 创建工具类
		beanUtils.createStringUtil(descPath, cName);
		if (Dialog.CODE_TYPE.contains("bean")) {
			beanUtils.createBean(descPath, cName);
		}
		if (Dialog.CODE_TYPE.contains("dao")) {
			beanUtils.createBeanDao(descPath, cName);
			beanUtils.createBeanDaoImpl(descPath, cName);
		}
		if (Dialog.CODE_TYPE.contains("service")) {
			beanUtils.createBeanService(descPath, cName);
			beanUtils.createBeanServiceImpl(descPath, cName);
		}
		if (Dialog.CODE_TYPE.contains("List")) {

		}
		return "";
	}

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 创建bean
	 * 
	 * @param descPath
	 *            E:\\workspace\\CodeCreator\\src\\cn\\cc
	 * @param fileName
	 *            tablename
	 * @throws Exception
	 */
	public void createBean(String descPath, String fileName) throws Exception {

		// E:\\workspace\\CodeCreator\\src\\cn\\cc\\entity\\user
		File filePath = new File(descPath, "entity" + File.separator + fileName);
		createFilePath(filePath);

		setPackageUrl(descPath, "entity" + File.separator + fileName);

		File file = new File(filePath, StringUtil.getUppercaseChar(fileName)
				+ "Bean.java");

		createCode(file, Constant.BEAN_TEMPLATE_VM_PATH);
	}

	/**
	 * 创建util包下的工具类
	 * 
	 * @param descPath
	 * @param fileName
	 * @throws Exception
	 */
	public void createStringUtil(String descPath, String fileName)
			throws Exception {

		// E:\\workspace\\CodeCreator\\src\\cn\\util\\entity\\user
		File filePath = new File(descPath, "util");
		createFilePath(filePath);

		File file = new File(filePath, "StringUtil.java");

		createCode(file, Constant.BEAN_UTIL_STRINGUTIL);
	}

	/**
	 * 创建bean的Dao<br>
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanDao(String descPath, String fileName)
			throws Exception {
		// E:\\workspace\\CodeCreator\\src\\cn\\cc\\dao\\user
		File filePath = new File(descPath, "dao" + File.separator + fileName);
		createFilePath(filePath);
		setPackageUrl(descPath, "dao" + File.separator + fileName);
		File file = new File(filePath, StringUtil.getUppercaseChar(fileName)
				+ "Dao.java");

		createCode(file, Constant.BEAN_DAO_TEMPLATE_VM_PATH);
	}

	/**
	 * 创建bean的Dao的实现<br>
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanDaoImpl(String descPath, String fileName)
			throws Exception {

		// E:\\workspace\\CodeCreator\\src\\cn\\cc\\dao\\user\\impl
		File filePath = new File(descPath, "dao" + File.separator + fileName
				+ File.separator + "impl");
		createFilePath(filePath);
		setPackageUrl(descPath, "dao" + File.separator + fileName
				+ File.separator + "impl");
		File file = new File(filePath, StringUtil.getUppercaseChar(fileName)
				+ "DaoImpl.java");

		createCode(file, Constant.BEAN_DAO_IMPL_TEMPLATE_VM_PATH);
	}

	/**
	 * 创建bean的Service<br>
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanService(String descPath, String fileName)
			throws Exception {
		// E:\\workspace\\CodeCreator\\src\\cn\\cc\\service\\user
		File filePath = new File(descPath, "service" + File.separator
				+ fileName);
		createFilePath(filePath);
		setPackageUrl(descPath, "service" + File.separator + fileName);
		File file = new File(filePath, StringUtil.getUppercaseChar(fileName)
				+ "Service.java");

		createCode(file, Constant.BEAN_SERVICE_TEMPLATE_VM_PATH);
	}

	/**
	 * 创建bean的Service的实现<br>
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanServiceImpl(String descPath, String fileName)
			throws Exception {

		// E:\\workspace\\CodeCreator\\src\\cn\\cc\\service\\user\\impl
		File filePath = new File(descPath, "service" + File.separator
				+ fileName + File.separator + "impl");
		createFilePath(filePath);
		setPackageUrl(descPath, "service" + File.separator + fileName
				+ File.separator + "impl");
		File file = new File(filePath, StringUtil.getUppercaseChar(fileName)
				+ "ServiceImpl.java");

		createCode(file, Constant.BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH);
	}

	/**
	 * 根据模板生成代码
	 * 
	 * @param file
	 *            将要生成的文件
	 * @param fileVMPath
	 *            模板路径
	 * @return
	 * @throws Exception
	 */
	public String createCode(File file, String fileVMPath) throws Exception {
		// 暂时只考虑文件不存在的情况
		if (!file.exists()) {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);

			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.setProperty("input.encoding", "UTF-8");
			velocityEngine.setProperty("output.encoding", "UTF-8");
			velocityEngine.init();
			Template template = velocityEngine.getTemplate(fileVMPath);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("bean", bean);
			velocityContext.put("annotation", annotation);
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);

			fw.write(stringWriter.toString());
			fw.flush();
			fw.close();

			// 转码
			transfer(file.getPath(), file.getPath(), JDBCCRUD.charsetName);
		}
		return "";
	}

	/**
	 * 将utf-8文件转码为charsetName
	 * 
	 * @param srcFileName
	 * @param destFileName
	 * @throws IOException
	 */
	public static void transfer(String srcFileName, String destFileName,
			String charsetName) throws IOException {

		String line_separator = System.getProperty("line.separator");

		FileInputStream fileInputStream = new FileInputStream(srcFileName);
		StringBuffer stringBuffer = new StringBuffer();
		DataInputStream dataInputStream = new DataInputStream(fileInputStream);

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(dataInputStream, "UTF-8"));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append(line_separator);
		}
		bufferedReader.close();
		dataInputStream.close();
		fileInputStream.close();

		Writer writer = new OutputStreamWriter(new FileOutputStream(
				destFileName), charsetName);
		writer.write(stringBuffer.toString());
		writer.close();

	}

	/**
	 * 创建文件路径:1.所有路径必须为小写
	 * 
	 * @param file
	 */
	public void createFilePath(File file) {
		if (!file.exists()) {
			System.out.println("创建[" + file.getAbsolutePath() + "]情况："
					+ file.mkdirs());
		} else {
			System.out.println("存在目录：" + file.getAbsolutePath());
		}
	}

	/**
	 * 字段列表List
	 * 
	 * @param cName
	 * @return
	 * @throws Exception
	 */
	private List<String> getColumnList(String cName) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("age");
		list.add("type");
		return list;
	}

	/**
	 * 字段列表map
	 * 
	 * @param cName
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getColumnMap(String cName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		List<Table> list = JDBCCRUD.queryTable(cName);
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i).getColumn_name();
			map.put(temp.toLowerCase(), StringUtil.getUppercaseChar(temp));
		}
		return map;
	}

	/**
	 * 取【类/接口】全路径
	 * 
	 * @param descPath
	 * @param postfix
	 * @return
	 * @throws Exception
	 */
	public String getPath(String descPath, String postfix) throws Exception {
		try {
			return (descPath.split("src\\\\")[1].replace("\\", ".") + "." + postfix
					.replace("\\", ".")).toLowerCase();
		} catch (Exception e) {
			System.out.println("源代码路径指定异常【" + descPath
					+ "】:可能性1、路径中不包含src。	exception：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 初始化bean和注解
	 * 
	 * @param c
	 */
	// String descPath, String cName, String beanRemark,String seqName
	public void init(Bean temp) throws Exception {
		String cName = temp.getName();
		String descPath = temp.getBeanUrl();
		if (!StringUtil.isBlank(descPath) && !StringUtil.isBlank(cName)) {
			bean.setName(cName);
			bean.setLowerName(StringUtil.getLowercaseChar(cName));
			bean.setUpperName(StringUtil.getUppercaseChar(cName));
			bean.setColumnList(getColumnList(cName));
			bean.setColumnMap(getColumnMap(cName));
			// 主键
			bean.setPkName(StringUtil.isBlank(temp.getPkName()) ? "1" : temp
					.getPkName());

			bean.setBeanRemark(StringUtil.isBlank(temp.getBeanRemark()) ? StringUtil
					.getUppercaseChar(temp.getName()) : temp.getBeanRemark());
			// bean.setSeqName(seqName); // 序列暂时不考虑

			// 主表信息
			bean.setPkMapList(temp.getPkMapList());

			// import urls
			bean.setBeanUrl(getPath(descPath, "entity" + File.separator + cName)
					+ "." + StringUtil.getUppercaseChar(cName) + "Bean");
			bean.setBeanDaoUrl(getPath(descPath, "dao" + File.separator + cName)
					+ "." + StringUtil.getUppercaseChar(cName) + "Dao");
			bean.setBeanDaoImplUrl(getPath(descPath, "dao" + File.separator
					+ cName + File.separator + "impl")
					+ "." + StringUtil.getUppercaseChar(cName) + "DaoImpl");
			bean.setBeanServiceUrl(getPath(descPath, "service" + File.separator
					+ cName)
					+ "." + StringUtil.getUppercaseChar(cName) + "Service");
			bean.setBeanServiceImplUrl(getPath(descPath, "service"
					+ File.separator + cName + File.separator + "impl")
					+ "." + StringUtil.getUppercaseChar(cName) + "ServiceImpl");
			bean.setUtilUrl(getPath(descPath, "util"));
			bean.setStringutilUrl(getPath(descPath, "util") + ".StringUtil");

			annotation.setAuthorName("ximing.fu");
			annotation.setAuthorMail("zjledming@sina.com");
			annotation.setDate(simpleDateFormat.format(new Date()));
			annotation.setVersion("1.0");
		}
	}

	/**
	 * 设置包路径
	 * 
	 * @param descPath
	 * @param postfix
	 * @throws Exception
	 */
	public void setPackageUrl(String descPath, String postfix) throws Exception {
		try {
			bean.setPackageUrl(getPath(descPath, postfix));
		} catch (Exception e) {
			System.out.println("源代码路径指定异常【" + descPath
					+ "】:可能性1、路径中不包含src。	exception：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 显示信息
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		System.out.println("创建文件：" + info + "成功！");
	}

	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

}
