package com.chattingweb.backend.controller.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.services.post.CreatePostRequest;
import com.chattingweb.backend.services.post.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestParam(name = "postImage", required = false) MultipartFile postImage, 
    		@RequestBody CreatePostRequest createPostRequest) {
        Post post = postService.createPost(postImage, createPostRequest);
        return ResponseEntity.ok(post);
    }

}