package com.andy.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConsumer.class);


    @RabbitListener(queues = "${rabbitmq.simple.queue.name}")
    public void consume(String message) {
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
