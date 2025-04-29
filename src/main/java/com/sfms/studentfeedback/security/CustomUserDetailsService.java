package com.sfms.studentfeedback.security;

import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.repository.AdminRepository;
import com.sfms.studentfeedback.repository.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final StudentRepository studentRepo;
    private final AdminRepository adminRepo;

    public CustomUserDetailsService(StudentRepository studentRepo, AdminRepository adminRepo) {
        this.studentRepo = studentRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find the student
        Student student = studentRepo.findByUsername(username).orElse(null);
        if (student != null) {
            return new User(student.getUsername(), student.getPassword(), Collections.singletonList(() -> "ROLE_STUDENT"));
        }

        // Try to find the admin
        Admin admin = adminRepo.findByUsername(username).orElse(null);
        if (admin != null) {
            return new User(admin.getUsername(), admin.getPassword(), Collections.singletonList(() -> "ROLE_ADMIN"));
        }

        throw new UsernameNotFoundException("User not found");
    }
}
