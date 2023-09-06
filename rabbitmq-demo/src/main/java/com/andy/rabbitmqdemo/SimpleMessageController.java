package com.andy.rabbitmqdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publish")
public class SimpleMessageController {

    private final SimpleProducer simpleProducer;

    public SimpleMessageController(SimpleProducer simpleProducer) {
        this.simpleProducer = simpleProducer;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        simpleProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
