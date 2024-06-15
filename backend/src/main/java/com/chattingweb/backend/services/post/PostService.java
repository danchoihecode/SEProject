package com.chattingweb.backend.services.post;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(MultipartFile postImage, CreatePostRequest createPostRequest) {
        Post post = new Post();
        if (postImage != null) {
            try {
				post.setPostImage(postImage.getBytes());
			} catch (IOException e) {
				System.out.println(e);
				throw new RuntimeException("Error processing post image", e);
			}
        }
        post.setPostText(createPostRequest.getPostText());
        post.setPostDate(LocalDateTime.now());
        post.setNoLike(createPostRequest.getNoLike() == null ? 0 : createPostRequest.getNoLike());

        Optional<User> userOptional = userRepository.findById(createPostRequest.getUserId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOptional.get();
        post.setUser(user);
        return postRepository.save(post);
    }
}
