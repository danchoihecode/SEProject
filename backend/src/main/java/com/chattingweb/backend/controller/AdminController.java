package com.chattingweb.backend.controller;

import com.chattingweb.backend.repository.admin.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final
    AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public ResponseEntity<Void> getAdmin(){
        adminRepository.findAll();
        return ResponseEntity.ok().build();
    }
}
