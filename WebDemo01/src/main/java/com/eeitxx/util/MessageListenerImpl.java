package com.eeitxx.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageListenerImpl implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		TextMessage tmp = (TextMessage)arg0;
		try {
			System.out.println("onMessage["+ tmp.getText() +"]");
			Thread.sleep(1000);
		} catch (JMSException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
