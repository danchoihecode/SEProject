package com.chattingweb.backend.services.messaging;

import com.chattingweb.backend.models.MessageData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    private KafkaTemplate<String, MessageData> kafkaTemplate;

    public Sender(KafkaTemplate<String, MessageData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, MessageData messageData) {
        kafkaTemplate.send(topic, messageData);
    }
}
