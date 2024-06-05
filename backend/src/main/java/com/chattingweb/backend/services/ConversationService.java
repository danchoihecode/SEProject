package com.chattingweb.backend.services;

import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.models.ConversationListItem;
import com.chattingweb.backend.repository.conversation.ConversationMemberRepository;
import com.chattingweb.backend.repository.conversation.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ConversationService {

    final
    ConversationMemberRepository conversationMemberRepository;

    final MessageRepository messageRepository;

    public ConversationService(
            ConversationMemberRepository conversationMemberRepository,
            MessageRepository messageRepository) {
        this.conversationMemberRepository = conversationMemberRepository;
        this.messageRepository = messageRepository;
    }

    public List<ConversationListItem> getConversationList(UUID userId){
        log.debug("get conversation with one user");
        List<ConversationListItem> conversationListItems = conversationMemberRepository.findAllConversationList(userId);

        log.debug("get group list");
        conversationListItems.addAll(conversationMemberRepository.findAllGroupList(userId));
        if(conversationListItems.isEmpty()){
            log.warn("Conversation list is empty");
            return null;
        }

        log.debug("get last message");
        for(ConversationListItem conversationListItem : conversationListItems){
            Optional<Message> optionalMessage
                    = messageRepository.findFirstByConversation_IdOrderByMessageDateDesc(conversationListItem.getConversationId());
            optionalMessage.ifPresent(message -> conversationListItem.setLatestMessage(message.getMessageContent()));
        }

        log.info("Size of conversation list is {}", conversationListItems.size());
        return conversationListItems;
    }
}
