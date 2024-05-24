package com.chattingweb.backend.controller;

import com.chattingweb.backend.entities.admin.Admin;
import com.chattingweb.backend.repository.admin.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final
    AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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
}
