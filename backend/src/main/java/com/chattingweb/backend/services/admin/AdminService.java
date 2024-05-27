package com.chattingweb.backend.services.admin;

import com.chattingweb.backend.entities.admin.Admin;
import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.BannedUserId;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.admin.AdminRepository;
import com.chattingweb.backend.repository.admin.BannedUserRepository;
import com.chattingweb.backend.repository.admin.DeletedPostRepository;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import java.time.Instant;
import java.time.Period;

public class AdminService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PostRepository postRepository;
    private final BannedUserRepository bannedUserRepository;
    private final DeletedPostRepository deletedPostRepository;

    public AdminService(UserRepository userRepository, AdminRepository adminRepository,BannedUserRepository bannedUserRepository,DeletedPostRepository deletedPostRepository, PostRepository postRepository) {
        this.userRepository=userRepository;
        this.adminRepository=adminRepository;
        this.bannedUserRepository=bannedUserRepository;
        this.deletedPostRepository=deletedPostRepository;
        this.postRepository=postRepository;
    }
    public BannedUser banUser(BanUser banUser){
        BannedUser  bannedUser= new BannedUser();
        BannedUserId bannedUserId=new BannedUserId();
        bannedUserId.setUserID(banUser.getID());
        bannedUserId.setBanDate(Instant.now().plus(Period.ofDays(banUser.getDuration())));
        bannedUser.setId(bannedUserId);
        User user= userRepository.findById(banUser.getID()).get();
        bannedUser.setUserID(user);
        bannedUser.setBanReason(banUser.getBanReason());
        bannedUser.setDuration(banUser.getDuration());
        Admin admin= adminRepository.findById(0).get();
        bannedUser.setAdminID(admin);
        return bannedUserRepository.save(bannedUser);
    }
    public DeletedPost deletePost(DeletePost deletePost){
        DeletedPost deletedPost= new DeletedPost();
        Admin admin= adminRepository.findById(0).get();
        deletedPost.setAdminID(admin);
        deletedPost.setDeleteReason(deletePost.getdeleteReason());
        deletedPost.setId(deletePost.getID());
        Post post =postRepository.findById(deletePost.getID()).get();
        deletedPost.setPost(post);
        postRepository.deleteById(deletePost.getID());
        return deletedPostRepository.save(deletedPost);
    }
    
    
}
