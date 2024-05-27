package com.chattingweb.backend.controller;

import com.chattingweb.backend.entities.admin.Admin;
import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.admin.AdminRepository;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import com.chattingweb.backend.services.admin.AdminService;
import com.chattingweb.backend.services.admin.BanUser;
import com.chattingweb.backend.services.admin.DeletePost;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final
    AdminRepository adminRepository;
    final
    UserRepository userRepository;
    private final AdminService adminService;
    private final PostRepository postRepository;

    public AdminController(AdminRepository adminRepository, UserRepository userRepository, AdminService adminService,PostRepository postRepository) {
        this.adminRepository = adminRepository;
        this.userRepository=userRepository;
        this.adminService=adminService;
        this.postRepository=postRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Integer requestedId){
        Optional<Admin> admin = adminRepository.findById(requestedId);
        if(admin.isPresent()){
            return ResponseEntity.ok(admin.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/banUser")
    public ResponseEntity<BannedUser> banUser (@RequestBody BanUser banUser){
        Integer bannedID=banUser.getID();
        Optional<User> user = userRepository.findById(bannedID);
        if(user.isPresent()){
            adminService.banUser(banUser);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/deletePost")
    public ResponseEntity<DeletedPost> banUser (@RequestBody DeletePost deletePost){
        Optional<Post> post = postRepository.findById(deletePost.getID());
        if(post.isPresent()){
            adminService.deletePost(deletePost);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
