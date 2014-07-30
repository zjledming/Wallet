package cn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserCountListener implements ServletContextListener,HttpSessionListener {
	private ServletContext sc;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		this.sc = sc;
		sc.setAttribute("Users", new Integer(0));
	}

	public void sessionCreated(HttpSessionEvent event) {
		Integer users = (Integer) sc.getAttribute("Users");
		System.out.println("sessionCreated~~~~~~~~~~~~~~~~~~");
		sc.setAttribute("Users",++users);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		Integer users = (Integer) sc.getAttribute("Users");
		sc.setAttribute("Users",--users);
		
	}

}
