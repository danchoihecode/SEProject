package com.chattingweb.backend.repository.admin;

import com.chattingweb.backend.entities.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}