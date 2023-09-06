package com.andy.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class SimpleProducer {


    @Value("${rabbitmq.simple.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.simple.queue.routing-key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public SimpleProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
