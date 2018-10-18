package org.topraworld.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class MessageProducerTest {

	public void produce() throws FileNotFoundException{
		
		Connection conn = null;Session session = null;
		
		try {
			
			
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://spc-headoffice:61616");
		    conn = factory.createConnection("admin", "admin");
		    session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		    
		    //Topic destination = session.createTopic("test");
		    Topic destination = session.createTopic("ExampleTopic_Product");
		    
		    MessageProducer producer = session.createProducer(destination);
		    conn.start();
		    TextMessage message = new ActiveMQTextMessage();
		   
		    
		    producer.setTimeToLive(60*1000*60*24);//1 second
			
			producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT ); // EXP_ProcessorParameter	
			//producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT ); // EXP_ProcessorParameter
			
			
			  
			Thread t = new Thread();
		    t.start();
			
			//for(int i = 0 ; i< 10 ; i++){
			int i = 0;
			while(true){
				
				message.setText(i+"");
				
				producer.send(message);
			    session.commit();
			    
			    
			    t.sleep(3000);
			    
			    System.out.println(i++);
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			new MessageProducerTest().produce();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
}
