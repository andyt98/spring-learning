package com.andy.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {

    @Value("${rabbitmq.json.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.json.queue.routing-key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public JsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}
