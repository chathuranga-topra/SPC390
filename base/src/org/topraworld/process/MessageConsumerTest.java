package org.topraworld.process;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageConsumerTest implements MessageListener {

	public static void main(String[] args) {
		
		new MessageConsumerTest().consume();

	}
	
	private void consume(){
		
		try{
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://spc-headoffice:61616");
		    Connection conn = factory.createConnection("admin", "admin");
		    conn.setClientID("A1");
		    Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		    
		    Destination destination = session.createTopic("ExampleTopic_Product");
		    
		    MessageConsumer consumer = session.createConsumer(destination);
		    	
	    	consumer.setMessageListener(this); // class that implements MessageListener
		    conn.start();
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			String text = txtMessage.getText();
			System.out.println(this.getClass() + " " + text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
