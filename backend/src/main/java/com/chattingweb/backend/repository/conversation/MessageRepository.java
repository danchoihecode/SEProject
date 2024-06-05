package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
  Optional<Message> findFirstByConversation_IdOrderByMessageDateDesc(UUID conversationId);
}