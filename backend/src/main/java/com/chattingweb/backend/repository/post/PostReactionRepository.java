package com.chattingweb.backend.repository.post;

import com.chattingweb.backend.entities.post.PostReaction;
import com.chattingweb.backend.entities.post.PostReactionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionId> {
  }