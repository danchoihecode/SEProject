package com.chattingweb.backend.repository.post;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByUser(User user, Pageable pageable);
}