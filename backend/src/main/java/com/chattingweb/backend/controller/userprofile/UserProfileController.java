package com.chattingweb.backend.controller.userprofile;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.modules.UserProfile;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {
    final UserRepository userRepository;
    private final PostRepository postRepository;
    public UserProfileController( UserRepository userRepository,PostRepository postRepository) {
        this.userRepository=userRepository;
        this.postRepository=postRepository;
    }

    @GetMapping("/")
    public ResponseEntity<UserProfile> userProfile (@RequestParam(name="id") UUID id, @RequestParam(name="page") Integer page){
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            List<Post> posts=postRepository.findByUser(user.get(), PageRequest.of(page,10));
            UserProfile userprofile= new UserProfile();
            userprofile.setAvatar(user.get().getAvatar());
            userprofile.setUuid(user.get().getId());
            userprofile.setNickName(user.get().getNickName());
            userprofile.setFullName(user.get().getFullName());
            userprofile.setPostList(posts);
            return ResponseEntity.ok(userprofile);

        }
        return ResponseEntity.notFound().build();
    }


}
