package cn.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * @ClassName: MyServletContextAttributeListener
 * @Description: 用于监听WEB应用属性改变的事件，包括：增加属性、删除属性、修改属性
 * @author XiMing.Fu
 * @date 2014-3-24 下午05:26:28
 * 
 */
public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("attributeAdded~~~~~~~~~~~~~~~~~~~~~~~~~");

	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("attributeRemoved~~~~~~~~~~~~~~~~~~~~~~~~~");

	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("attributeReplaced~~~~~~~~~~~~~~~~~~~~~~~~~");

	}

}
