package com.chattingweb.backend.repository.post;

import com.chattingweb.backend.entities.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  }