package cn.reflect;

import java.io.File;
import java.net.URL;

public class ClassPathUtil {
	public static void main(String[] args) {
		ClassPathUtil classPathUtil = new ClassPathUtil();
		String classPath = classPathUtil.getCurrentClassPath();
		System.out.println(classPath);
	}

	public String getCurrentClassPath() {
		String classname = this.getClass().getName();
		System.out.println(classname); // cn.reflect.ClassPathUtil
		// get class
		String classname_resource = "/" + classname.replace('.', '/')
				+ ".class";
		System.out.println(classname_resource); // /cn/reflect/ClassPathUtil.class
		// get class URL
		URL url = this.getClass().getResource(classname_resource);
		String urlPath = url.getPath();
		System.out.println(urlPath);// /E:/workspace/Utils/WebRoot/WEB-INF/classes/cn/reflect/ClassPathUtil.class
		// get File
		File classFile = new File(urlPath);
		String classPath = classFile.getPath();
		System.out.println(classname + "'s path is " + classPath);
		String re = classPath.substring(0, classPath.lastIndexOf("\\"));
		System.out.println("父目录："+re);
		return classPath;
	}
	
	/**
	 * 取当前class文件所在的物理地址-使用版，参数pah多余
	 * 
	 * @return
	 */
	public String getCurrentClassPath(String pah) {
		String classPath = "";
		try {
			String classname = this.getClass().getName();
			String classname_resource = "/" + classname.replace('.', '/')
					+ ".class";
			URL url = this.getClass().getResource(classname_resource);
			String urlPath = url.getPath();
			File classFile = new File(urlPath);
			classPath = classFile.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classPath;
	}

}
