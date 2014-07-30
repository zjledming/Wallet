package cn.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: MyHttpSessionListener
 * @Description: 用于监听Session对象的创建和销毁
 * @author XiMing.Fu
 * @date 2014-3-24 下午05:27:37
 * 
 */
public class MyHttpSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("sessionCreated~~~~~~~~~~~~");

	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("sessionDestroyed~~~~~~~~~~~~");

	}

}
