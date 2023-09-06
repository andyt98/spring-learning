package com.andy.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);


    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consume(User user) {
        LOGGER.info(String.format("Received message -> %s", user));
    }
}
