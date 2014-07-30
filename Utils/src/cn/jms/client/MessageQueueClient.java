package cn.jms.client;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageQueueClient {

	public static void main(String[] args) {
		QueueConnection conn;
		QueueSession session;
		Queue queue;
		QueueSender sender;
		TextMessage msg;
		try {
			InitialContext ctx = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");
			conn = qcf.createQueueConnection();
			session = conn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup("queue/jms");
			msg = session.createTextMessage("你好，好久不见！");
			sender = session.createSender(queue);
			sender.send(msg);
			sender.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}