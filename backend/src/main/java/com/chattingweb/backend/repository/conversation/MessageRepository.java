package com.chattingweb.backend.repository.conversation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chattingweb.backend.entities.conversation.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
  Optional<Message> findFirstByConversation_IdOrderByMessageDateDesc(UUID conversationId);


    List<Message> findAllByConversationId(UUID conversationId);
    
    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId ORDER BY m.messageDate ASC")
    List<Message> findByConversationIdOrderByMessageDateAsc(@Param("conversationId") UUID conversationId);
}