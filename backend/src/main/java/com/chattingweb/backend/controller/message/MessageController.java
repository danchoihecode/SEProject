package com.chattingweb.backend.controller.message;

import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.services.messaging.Sender;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@SecurityRequirement(name = "Authentication")
@MessageMapping("/chat")
public class MessageController {
    private final Sender sender;
    private final SimpMessageSendingOperations messagingTemplate;

    public MessageController(Sender sender, SimpMessageSendingOperations messagingTemplate) {
        this.sender = sender;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/message-sending")
    public void sendMessage(@Payload MessageData messageData, SimpMessageHeaderAccessor headerAccessor) {
        messageData.setSessionId(headerAccessor.getSessionId());
        sender.send("messaging", messageData);
        log.info("Sending message to /topic/public: {}", messageData);
        messagingTemplate.convertAndSend("/topic/public", messageData);
        log.info("Message sent to /topic/public: {}", messageData);
    }

    @MessageMapping("/user-adding")
    public MessageData addUser(@Payload MessageData messageData, SimpMessageHeaderAccessor headerAccessor) {
        if(headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("userId", messageData.getSenderUserId());
        }
        return messageData;
    }

}
