package com.chattingweb.backend.controller.message;


import com.chattingweb.backend.models.MessageData;

import com.chattingweb.backend.models.MessageType;
import com.chattingweb.backend.services.messaging.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
@SecurityRequirement(name = "Authentication")
public class MessageController {

    final
    SimpMessagingTemplate simpMessagingTemplate;

    final MessageService messageService;

    public MessageController(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/message")
    public void privateMessage(@Payload MessageData messageData) {
        log.info("Message received: {}", messageData.getMessageType());
        if(messageData.getMessageType() == MessageType.CHAT){
            messageService.saveNewMessage(messageData);
        }
        simpMessagingTemplate.convertAndSend( "/topic/private/" + messageData.getConversationId().toString(), messageData);
    }
}
