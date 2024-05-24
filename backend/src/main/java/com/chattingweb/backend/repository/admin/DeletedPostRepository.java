package com.chattingweb.backend.repository.admin;

import com.chattingweb.backend.entities.admin.DeletedPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedPostRepository extends JpaRepository<DeletedPost, Integer> {
}