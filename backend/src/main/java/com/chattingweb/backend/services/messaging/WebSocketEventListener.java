package com.chattingweb.backend.services.messaging;

import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.models.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@Configuration
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event){
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        UUID userId = (UUID) headerAccessor.getSessionAttributes().get("userId");

        if(userId != null){
            log.info("User Disconnect{}", userId);
            MessageData messageData = new MessageData();
            messageData.setMessageType(MessageType.DISCONNECT);
            messageData.setSenderUserId(userId);
            messagingTemplate.convertAndSend("/topic/public",messageData);
        }
    }
}
