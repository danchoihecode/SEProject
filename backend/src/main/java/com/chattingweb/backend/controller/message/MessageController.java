package com.chattingweb.backend.controller.message;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.conversation.MessageRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import com.chattingweb.backend.services.messaging.Sender;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@Slf4j
@SecurityRequirement(name = "Authentication")
@MessageMapping("/chat")
public class MessageController {
    private final ConversationRepository conversationRepository;
    private final Sender sender;
    private final SimpMessageSendingOperations messagingTemplate;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    public MessageController(Sender sender, SimpMessageSendingOperations messagingTemplate,
                             ConversationRepository conversationRepository, UserRepository userRepository,MessageRepository messageRepository) {
        this.sender = sender;
        this.messagingTemplate = messagingTemplate;
        this.conversationRepository = conversationRepository;
        this.userRepository=userRepository;
        this.messageRepository=messageRepository;
    }

    @MessageMapping("/message-sending")
    public void sendMessage(@Payload MessageData messageData, SimpMessageHeaderAccessor headerAccessor) {
        messageData.setSessionId(headerAccessor.getSessionId());
        Message message= new Message();
        message.setMessageContent(messageData.getMessageContent());
        Optional<Conversation> conversation=conversationRepository.findById(messageData.getConversationId());
        Optional<User> user= userRepository.findById(messageData.getSenderUserId());
        if(conversation.isPresent()&&user.isPresent()){
            message.setConversation(conversation.get());
            message.setSender(user.get());;
            sender.send("messaging", messageData);
            log.info("Sending message to /topic/public: {}", messageData);
            messagingTemplate.convertAndSend("/topic/public", messageData);
            log.info("Message sent to /topic/public: {}", messageData);
            messageRepository.save(message);
        }
    }

    @MessageMapping("/user-adding")
    public MessageData addUser(@Payload MessageData messageData, SimpMessageHeaderAccessor headerAccessor) {
        if(headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("userId", messageData.getSenderUserId());
        }
        return messageData;
    }

}
