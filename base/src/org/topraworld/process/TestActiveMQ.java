package org.topraworld.process;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.adempiere.exceptions.AdempiereException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class TestActiveMQ implements MessageListener {
	
	public boolean isActivrMQOnline(){
		
		boolean status = true;;
		
		try {
		    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://spc-headoffice:61616");
		    Connection conn = factory.createConnection("admin", "admin");
		    //Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		    
		   /* Destination destination = session.createTopic("BB");
		    
		    MessageProducer producer = session.createProducer(destination);
		    MessageConsumer consumer = session.createConsumer(destination);
		    consumer.setMessageListener(this); // class that implements MessageListener
		    conn.start();
		    TextMessage message = new ActiveMQTextMessage();
		    message.setText("TestMessage njgj ggjk jkv");
		    producer.send(message);*/
		    
		} catch (JMSException e) {
		    status = false;
		    
		    throw new AdempiereException("Login failed! Replication server goes down..");
		}
		
		return status;
		
	}
	
	public static void main(String []  args){
		
		new TestActiveMQ().isActivrMQOnline();
	}

	@Override
	public void onMessage(Message message) {
		
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			String text = txtMessage.getText();
			
			System.out.println(text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
