package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}