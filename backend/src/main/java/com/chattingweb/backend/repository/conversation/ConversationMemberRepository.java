package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, ConversationMemberId> {
}