package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}