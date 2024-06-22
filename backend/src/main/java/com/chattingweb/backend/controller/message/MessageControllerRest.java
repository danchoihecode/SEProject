package com.chattingweb.backend.controller.message;

import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.services.messaging.MessageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@SecurityRequirement(name= "Authentication")
@RequestMapping("/message")
@Slf4j
public class MessageControllerRest {

    private final MessageService messageService;

    public MessageControllerRest(MessageService messageService) {
        this.messageService = messageService;
    }

    @PutMapping("/message-update")
    public ResponseEntity<Message> updateMessage(
            @RequestBody Map<String, Object> request) {
        
        try {
			Long id = (Long) request.get("messageId");
			String newContent = (String) request.get("content");

			messageService.updateMessage(id, newContent);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
    }
    

    @PostMapping("/message-delete")
    public ResponseEntity<Message> deleteMessage(
        @RequestBody Map<String, Object> request) {
        
        try {
			Long id = (Long) request.get("messageId");
			messageService.deleteMessage(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
    }
}
