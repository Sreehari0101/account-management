package com.example.account_management.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "fund-transfers", groupId = "bank-group")
    public void listenToFundTransfers(ConsumerRecord<String, String> record) {
        System.out.println(" Kafka Event Received: " + record.value());
    }
}
