package com.chattingweb.backend.services.messaging;

import com.chattingweb.backend.models.MessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Receiver {

    private final SimpMessageSendingOperations messagingTemplate;
    private final SimpUserRegistry userRegistry;

    public Receiver(SimpMessageSendingOperations messagingTemplate, SimpUserRegistry userRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.userRegistry = userRegistry;
    }

    @KafkaListener(topics = "messaging",groupId = "chat")
    public void consume(MessageData messageData){
        log.info("Received message from Kafka:{}", messageData);
        for (SimpUser user : userRegistry.getUsers()) {
            for (SimpSession session:user.getSessions()){
                if (!session.getId().equals(messageData.getSessionId())) {
                    messagingTemplate.convertAndSendToUser(session.getId(),"/topic/public",messageData);
                }
            }
        }
    }

}
