package com.chattingweb.backend.entities.conversation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, ConversationMemberId> {
}