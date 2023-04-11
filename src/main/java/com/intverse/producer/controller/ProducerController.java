package com.intverse.producer.controller;

import com.intverse.producer.config.KafkaProducerConfig2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaProducerConfig2 kafkaProducerConfig2;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public String sendMessage() {
        kafkaTemplate.send("intverse_test_topic", "test-tharun");
        return "Message sent successfully!";
    }

    @GetMapping("/send2")
    public String sendMessage2() {
        kafkaProducerConfig2.sendMessage();
        return "Message sent successfully!";
    }
}
