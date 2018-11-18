package com.bolivariano.tarifas.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolivariano.tarifas.configuration.MessagePublisherConfiguration;
import com.bolivariano.tarifas.modelos.Tarifa;

@Component("messagePublisher")
public class MessagePublisher {
	
	@Autowired
	private MessagePublisherConfiguration messagePublConfiguration;

	public void sendNewMessage(Tarifa tarifa, String queueName) {    	
    	messagePublConfiguration.rabbitTemplate().convertAndSend(queueName, tarifa);
    }

}