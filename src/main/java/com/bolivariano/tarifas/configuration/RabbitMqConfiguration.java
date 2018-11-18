package com.bolivariano.tarifas.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqConfiguration.class);

	@Value("${application.rabbit.addresses}")
	private String addresses;
	@Value("${application.rabbit.user}")
	private String rabbitUser;
	@Value("${application.rabbit.pass}")
	private String rabbitPass;
	@Value("${application.topic-name}")
    private String topicName;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername(rabbitUser);
		connectionFactory.setPassword(rabbitPass);
		connectionFactory.setAddresses(addresses);
		logger.debug("[Rabbit Connection Factory STARTED][Adresses={}][User={}][Pass={}]",addresses,rabbitUser,rabbitPass);
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		AmqpAdmin adm = new RabbitAdmin(connectionFactory());
		
		Queue queue = new Queue(topicName, true, false, false);
		adm.declareQueue(queue);
		logger.info("[CREATED PRODUCE QUEUE=" + queue.getName()+ "]");
		return adm;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}