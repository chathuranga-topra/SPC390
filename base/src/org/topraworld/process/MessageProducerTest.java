package org.topraworld.process;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class MessageProducerTest {

	public void produce(){
		
		Connection conn = null;Session session = null;
		
		try {
			
			
		    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://dev-spc:61616");
		    conn = factory.createConnection("admin", "admin");
		    session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		    
		    Destination destination = session.createTopic("ExampleTopic_Product");
		    
		    MessageProducer producer = session.createProducer(destination);
		    conn.start();
		    TextMessage message = new ActiveMQTextMessage();
		    message.setText("TestMessage njgj ggjk jkv");
		    
		    producer.setTimeToLive( 1 ); // EXP_ProcessorParameter
			
			//producer.setDeliveryMode( DeliveryMode.PERSISTENT ); // EXP_ProcessorParameter	
			//producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT ); // EXP_ProcessorParameter
			
	    	producer.send(message);
		    session.commit();
		    
		} catch (JMSException e) {
			
		}finally {
			// Clean up
			if (session != null) { 
				try { session.close(); } catch (JMSException ex) { /* ignored */ }
			}
			if (conn != null) {
				try { conn.close(); } catch (JMSException ex) { /* ignored */ }
			}
		}
	}
	
	public static void main(String []  args){
		
		new MessageProducerTest().produce();
	}
	
}
