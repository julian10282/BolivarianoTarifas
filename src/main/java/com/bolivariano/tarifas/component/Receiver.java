package com.bolivariano.tarifas.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.bolivariano.tarifas.modelos.Tarifa;

@Component
public class Receiver {
	
//	@Value("${application.topic-name}")
//    private String topicName;
//	
//    @JmsListener(destination = "jms/TopicBolivariano", containerFactory = "myFactory")
//    public void receiveMessage(Tarifa tarifa) {
//        System.out.println("Received <" + tarifa + ">");
//    }

}