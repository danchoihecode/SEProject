package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Message;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findAllByConversationId(UUID conversationId);
  }