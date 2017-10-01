package com.eeitxx.util;

import java.sql.Date;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class ReceiveMqDemo01 {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = 
//				new ActiveMQConnectionFactory("tcp://192.168.2.109:61616");
				new ActiveMQConnectionFactory("tcp://192.168.0.86:61616");
		Connection conn = factory.createConnection();
		conn.start();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		ActiveMQQueue dest = new ActiveMQQueue("foo.bar"); //目的點
		MessageConsumer consumer = session.createConsumer(dest); //消息接收者
//		TextMessage message = (TextMessage)consumer.receive();  //同步接收消息，若沒消息會等待下一筆
		
		consumer.setMessageListener(new MessageListenerImpl()); //異步接收消息，
		
//		System.out.println("message["+message.getText()+"]");
		
//		while(true){
//		}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		conn.start();
		session.close();
		conn.close();
	}
}
