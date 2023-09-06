package com.andy.rabbitmqdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json-publish")
public class JsomMessageController {

    private final JsonProducer producer;

    public JsomMessageController(JsonProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage (@RequestBody User user){
        producer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
