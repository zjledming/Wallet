package cn.classloader;

import java.net.URL;

public class BootstrapClassloader {
	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].getPath());
		}
		System.out.println("================================================");
		System.out.println(System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader()
				.getParent();
		System.out.println("the parent of extension classloader : "
				+ extensionClassloader.getParent());
		System.out.println("================================================");
		System.out.println(System.getProperty("java.class.path"));
		
		System.out.println(System.class.getClassLoader());
		
		  System.out.println("the Launcher's classloader is "+sun.misc.Launcher.getLauncher().getClass().getClassLoader());

	}

}
