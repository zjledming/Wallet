package cn.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class BSServletRequestListener implements ServletRequestListener {
	private static final Logger log = Logger
			.getLogger(BSServletRequestListener.class);

	/*
	 * 当用户请求结束、被销毁时触发该方法 (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.
	 * ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		if ((requestEvent.getServletRequest() instanceof HttpServletRequest)) {
			HttpServletRequest request = (HttpServletRequest) requestEvent
					.getServletRequest();
			String uri = request.getRequestURI();

			if ((uri.endsWith(".jsp")) || (uri.endsWith(".do"))) {
//				boolean state = TransactionManager.destroyTransaction();
//				if (state) {
//					log.debug("A DB transaction leaked in Page [" + uri
//							+ "] has been forcibly destoried. ");
//					System.out.println("A DB transaction leaked in Page ["
//							+ uri + "] has been forcibly destoried. ");
//				}
//				AccessControl.init(null);
			} else if (uri.endsWith(".frame")) {
//				AccessControl.init(null);
			}
		}
	}

	/*
	 * 当用户请求到达、被初始化时触发该方法 (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletRequestListener#requestInitialized(javax.servlet
	 * .ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent requestEvent) {
		if ((requestEvent.getServletRequest() instanceof HttpServletRequest)) {
			HttpServletRequest request = (HttpServletRequest) requestEvent
					.getServletRequest();
			String uri = request.getRequestURI();

			if ((uri.endsWith(".jsp")) || (uri.endsWith(".do"))) {
//				boolean state = TransactionManager.destroyTransaction();
//				if (state) {
//					log.debug("A DB transaction leaked before Page [" + uri
//							+ "] has been forcibly destoried. ");
//					System.out.println("A DB transaction leaked before Page ["
//							+ uri + "] has been forcibly destoried. ");
//				}
//				AccessControl.init(null);
			} else if (uri.endsWith(".frame")) {
//				AccessControl.init(null);
			}
		}
	}

	// 当程序向request范围添加属性时触发该方法
	public void attributeAdded(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// 获取添加的属性名和属性值
		String name = event.getName();
		Object value = event.getValue();
		System.out
				.println(request + "范围内添加了名为" + name + "，值为" + value + "的属性!");
	}

	// 当程序从request范围删除属性时触发该方法
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// 获取被删除的属性名和属性值
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(request + "范围内名为" + name + "，值为" + value
				+ "的属性被删除了!");
	}

	// 当request范围的属性被替换时触发该方法
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// 获取被替换的属性名和属性值
	}

}