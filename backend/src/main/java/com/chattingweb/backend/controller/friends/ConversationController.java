package com.chattingweb.backend.controller.friends;

import com.chattingweb.backend.models.ConversationListItem;
import com.chattingweb.backend.services.ConversationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name= "Authentication")
@RequestMapping("/conversations")
@Slf4j
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
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
}
