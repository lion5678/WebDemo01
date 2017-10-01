package com.eeitxx.util;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activeMQ範例
 * @author lion
 *
 */
public class SenderMqText {

	public static void main(String[] args) {

		ActiveMQConnectionFactory factory = 
//				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.2.109:61616");
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.0.86:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(true, Session.SESSION_TRANSACTED);
			Queue destination = session.createQueue("foo.bar"); //destination 目地
			MessageProducer producer = session.createProducer(destination); // producer 消息生產者
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, producer);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if(session != null){
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
    public static void sendMessage(Session session, MessageProducer producer) throws JMSException {  
    	TextMessage message = session.createTextMessage();  //TextMessage 文本消息 ，MapMessage 集合消息...
        for(int i = 0; i < 10; i++) {  
            /*创建消息*/  
            message.setText("發送消息[" + i +"]");
            try {
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("發送消息[" + i+"]");  
            /*开始发送*/  
            producer.send(message);  
        }  
    }  
}
