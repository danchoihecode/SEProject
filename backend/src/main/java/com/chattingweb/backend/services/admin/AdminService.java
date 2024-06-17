package com.chattingweb.backend.services.admin;

import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.BannedUserId;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.admin.BannedUserRepository;
import com.chattingweb.backend.repository.admin.DeletedPostRepository;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BannedUserRepository bannedUserRepository;
    private final DeletedPostRepository deletedPostRepository;

    public AdminService(UserRepository userRepository,BannedUserRepository bannedUserRepository,DeletedPostRepository deletedPostRepository, PostRepository postRepository) {
        this.userRepository=userRepository;
        this.bannedUserRepository=bannedUserRepository;
        this.deletedPostRepository=deletedPostRepository;
        this.postRepository=postRepository;
    }
    public BannedUser banUser(BanUser banUser){
        BannedUser  bannedUser= new BannedUser();
        BannedUserId bannedUserId=new BannedUserId();
        bannedUserId.setUserId(banUser.getID());
        bannedUserId.setBandDate(Instant.now().plus(Period.ofDays(banUser.getDuration())));
        bannedUser.setId(bannedUserId);
        User user= userRepository.findById(banUser.getID()).get();
        bannedUser.setUser(user);
        bannedUser.setBanReason(banUser.getBanReason());
        bannedUser.setDuration(banUser.getDuration());
        User admin= userRepository.findAdmin().get();
        bannedUser.setAdmin(admin);
        return bannedUserRepository.save(bannedUser);
    }
    public DeletedPost deletePost(DeletePost deletePost){
        DeletedPost deletedPost= new DeletedPost();
        User admin= userRepository.findAdmin().get();
        deletedPost.setAdmin(admin);
        deletedPost.setDeleteReason(deletePost.getdeleteReason());
        deletedPost.setId(deletePost.getID());
        Post post =postRepository.findById(deletePost.getID()).get();
        deletedPost.setPost(post);
        postRepository.deleteById(deletePost.getID());
        return deletedPostRepository.save(deletedPost);
    }
    
    
}
