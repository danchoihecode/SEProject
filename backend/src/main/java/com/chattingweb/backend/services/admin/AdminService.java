package com.chattingweb.backend.services.admin;

import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.BannedUserId;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.admin.Report;
import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.admin.BannedUserRepository;
import com.chattingweb.backend.repository.admin.DeletedPostRepository;
import com.chattingweb.backend.repository.admin.ReportRepository;
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
    private final ReportRepository reportRepository;

    public AdminService(UserRepository userRepository,BannedUserRepository bannedUserRepository,DeletedPostRepository deletedPostRepository, PostRepository postRepository, ReportRepository reportRepository) {
        this.userRepository=userRepository;
        this.bannedUserRepository=bannedUserRepository;
        this.deletedPostRepository=deletedPostRepository;
        this.postRepository=postRepository;
        this.reportRepository=reportRepository;
    }
    public void approveReport(Report report,ApproveReport approveReport){
        User user= report.getUser();
        BannedUser  bannedUser= new BannedUser();
        BannedUserId bannedUserId=new BannedUserId();
        bannedUserId.setUserId(user.getId());
        bannedUserId.setBandDate(Instant.now().plus(Period.ofDays(approveReport.getDuration())));
        bannedUser.setId(bannedUserId);
        bannedUser.setUser(user);
        bannedUser.setBanReason(approveReport.getBanReason());
        bannedUser.setDuration(approveReport.getDuration());
        User admin = userRepository.findByIsAdminTrue().orElseThrow(() -> new RuntimeException("Admin not found"));
        bannedUser.setAdmin(admin);
        bannedUserRepository.save(bannedUser);

        DeletedPost deletedPost= new DeletedPost();
        deletedPost.setAdmin(admin);
        deletedPost.setDeleteReason(approveReport.getBanReason());   
        deletedPost.setPostText(report.getPost().getPostText());
        deletedPost.setId(report.getPost().getId());
        deletedPostRepository.save(deletedPost);
      
        reportRepository.deleteAllByPostId(report.getPost().getId());
        postRepository.deleteById(report.getPost().getId());
        
        
    }

    public void declineReport(Report report){
        reportRepository.delete(report);
    }
    
    
}
