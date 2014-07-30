package cn.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyServletRequestAttributeListener implements ServletRequestAttributeListener{

	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("attributeAdded~~~~~~~~~~~~~~~~");
		
	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("attributeRemoved~~~~~~~~~~~~~~~~");
		
	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("attributeReplaced~~~~~~~~~~~~~~~~");
		
	}

}
