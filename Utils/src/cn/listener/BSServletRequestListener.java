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
	 * ���û����������������ʱ�����÷��� (non-Javadoc)
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
	 * ���û����󵽴����ʼ��ʱ�����÷��� (non-Javadoc)
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

	// ��������request��Χ�������ʱ�����÷���
	public void attributeAdded(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// ��ȡ��ӵ�������������ֵ
		String name = event.getName();
		Object value = event.getValue();
		System.out
				.println(request + "��Χ���������Ϊ" + name + "��ֵΪ" + value + "������!");
	}

	// �������request��Χɾ������ʱ�����÷���
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// ��ȡ��ɾ����������������ֵ
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(request + "��Χ����Ϊ" + name + "��ֵΪ" + value
				+ "�����Ա�ɾ����!");
	}

	// ��request��Χ�����Ա��滻ʱ�����÷���
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		ServletRequest request = event.getServletRequest();
		// ��ȡ���滻��������������ֵ
	}

}