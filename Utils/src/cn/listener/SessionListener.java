package cn.listener;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: SessionListener
 * @Description: 监听Session对象的创建和销毁
 * @author XiMing.Fu
 * @date 2014-3-27 上午11:52:05
 * 
 */
public class SessionListener implements HttpSessionListener, Serializable {
	private static final long serialVersionUID = 1L;

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = (HttpSession) event.getSource();

		Map principals = (Map) session.getAttribute("PRINCIPAL_INDEXS");

		if (principals != null) {
			//AccessControl.logoutdirect(session);
		}
	}
}