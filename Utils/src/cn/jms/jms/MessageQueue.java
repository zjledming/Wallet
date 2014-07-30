package cn.jms.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/jms") })
public class MessageQueue implements MessageListener {

	public MessageQueue() {

	}

	public void onMessage(Message message) {
		TextMessage tmsg = (TextMessage) message;
		try {
			System.out.println(tmsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}