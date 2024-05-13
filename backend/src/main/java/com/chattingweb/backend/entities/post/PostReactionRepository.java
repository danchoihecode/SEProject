package com.chattingweb.backend.entities.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionId> {
}