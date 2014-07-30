package cn.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/** 
 * @ClassName: MyServletRequestListener
 * @Description: 用于监听绑定到HttpSession域中的某个对象的状态的事件监听器
 * @author XiMing.Fu
 * @date 2014-3-24 下午05:28:39
 * 
 */
public class MyServletRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("requestDestroyed~~~~~~~~~");
		
	}

	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("requestInitialized~~~~~~~~~");
		
	}

}
