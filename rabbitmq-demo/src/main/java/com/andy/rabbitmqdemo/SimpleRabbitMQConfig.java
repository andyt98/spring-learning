package com.andy.rabbitmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleRabbitMQConfig {

    @Value("${rabbitmq.simple.queue.name}")
    private String simpleQueue;

    @Value("${rabbitmq.simple.queue.exchange}")
    private String simpleExchange;

    @Value("${rabbitmq.simple.queue.routing-key}")
    private String simpleRoutingKey;

    @Bean
    public Queue simpleQueue(){
        return new Queue(simpleQueue);
    }

    @Bean
    public TopicExchange simpleExchange(){
        return new TopicExchange(simpleExchange);
    }

    @Bean
    public Binding simpleBinding(){
        return BindingBuilder.bind(simpleQueue())
                .to(simpleExchange())
                .with(simpleRoutingKey);
    }

}
