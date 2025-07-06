package com.example.account_management.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "fund-transfers";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishTransferEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
