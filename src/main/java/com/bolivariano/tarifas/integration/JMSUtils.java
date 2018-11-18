package com.bolivariano.tarifas.integration;

import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.stereotype.Service;

import com.bolivariano.tarifas.modelos.Tarifa;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("jmsutils")
public class JMSUtils {
	// Defines the JNDI context factory.
	   public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
	 
	   // Defines the JMS context factory.
	   public final static String JMS_FACTORY="jms/TopicBolivarianoFactory";
	 
	   // Defines the topic.
	   public final static String MyTOPIC="jms/TopicBolivariano";
	          
	   // Defines the WebLogic URL. The port is for the Managed Server or
	   // the Admin Server where the JMS Server is running.
	   public final static String WebLogicURL="t3://localhost:7001";   
	     
	  
	   public void sendMessage(Tarifa tarifa) throws Exception {
		   
			try {
				System.out.println("Entra enviar mensaje");
			       //Create and start connection 
			       Hashtable env = new Hashtable();
			       env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
			       env.put(Context.PROVIDER_URL, WebLogicURL);
			        
			       InitialContext ic=new InitialContext(env);   
			        
			       TopicConnectionFactory f=(TopicConnectionFactory)ic.lookup(JMS_FACTORY) ;   
			        
			       TopicConnection con=f.createTopicConnection();  
			       con.start();  
			        
			       //2) create topic session  
			       TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);  
			        
			       //3) get the Topic object  
			       Topic topic = (Topic)ic.lookup(MyTOPIC);  
			        
			       //4)create topic publisher          
			       TopicPublisher topicPublisher = ses.createPublisher(topic);  
			       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			        
			       //5) create TextMessage object  
			        TextMessage msg= ses.createTextMessage();
			        
			        ObjectMapper mapper = new ObjectMapper();
			        msg.setText(mapper.writeValueAsString(tarifa));
			         
			       //7) send message  
			        topicPublisher.publish(msg);  
			        
			       System.out.println("Message successfully published: " + msg);  
			 
			       //8) connection close 
			       con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
	          
	        
	   } 
	   
}