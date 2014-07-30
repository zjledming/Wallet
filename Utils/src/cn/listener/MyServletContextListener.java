package cn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName: MyServletContextListener
 * @Description: 
 *               用于监听域对象自身的创建和销毁的事件监听器:当WEB工程启动时调用contextInitialed,停止WEB工程时调用contextDestroyed
 *               。
 * @author XiMing.Fu
 * @date 2014-3-24 下午04:49:35
 * 
 */
public class MyServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed~~~~~~~~~~~");

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized~~~~~~~~~~~~~~");

	}

}
