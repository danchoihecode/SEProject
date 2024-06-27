package com.chattingweb.backend.controller.friends;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chattingweb.backend.models.ConversationListItem;
import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.services.ConversationService;
import com.chattingweb.backend.services.messaging.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@RestController
@SecurityRequirement(name= "Authentication")
@RequestMapping("/conversations")
@Slf4j
public class ConversationController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    public ConversationController(ConversationService conversationService, MessageService messageService) {
        this.conversationService = conversationService;
		this.messageService = messageService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ConversationListItem>> getAllConversations(
            @RequestParam(name = "user-id") UUID userId) {
        log.info("Finding conversations for {}", userId);
        List<ConversationListItem> responseList = conversationService.getConversationList(userId);
        if(!responseList.isEmpty()) {
        log.info("Found {} conversations", responseList.size());
            return ResponseEntity.ok(responseList);
        }
        log.warn("No conversations found for {}", userId);
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{conversationId}")
    public ResponseEntity<List<MessageData>> getConversationMessages(@PathVariable UUID conversationId) {
	    List<MessageData> messages = messageService.getConversationMessages(conversationId);
	    return ResponseEntity.ok(messages);
    }
}
