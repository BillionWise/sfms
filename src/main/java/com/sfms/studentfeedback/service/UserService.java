package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.factory.UserFactory;
import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.model.User;
import com.sfms.studentfeedback.repository.AdminRepository;
import com.sfms.studentfeedback.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final StudentRepository studentRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(StudentRepository studentRepo, AdminRepository adminRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String userType, String username, String password) {
        // ✅ Encrypt the password before saving
        String hashedPassword = passwordEncoder.encode(password);
        User user = UserFactory.createUser(userType, username, hashedPassword);

        if (user instanceof Student) {
            studentRepo.save((Student) user);
        } else if (user instanceof Admin) {
            adminRepo.save((Admin) user);
        }
    }

    public String loginUser(String userType, String username, String password) {
        if ("student".equalsIgnoreCase(userType)) {
            Optional<Student> studentOpt = studentRepo.findByUsername(username);
            if (studentOpt.isEmpty()) return "User not found!";

            Student student = studentOpt.get();
            if (!passwordEncoder.matches(password, student.getPassword())) return "Incorrect password!";

            return "Login successful!";
        } else if ("admin".equalsIgnoreCase(userType)) {
            Optional<Admin> adminOpt = adminRepo.findByUsername(username);
            if (adminOpt.isEmpty()) return "User not found!";

            Admin admin = adminOpt.get();
            if (!passwordEncoder.matches(password, admin.getPassword())) return "Incorrect password!";

            return "Login successful!";
        }

        return "User type mismatch!";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try student first
        Optional<Student> studentOpt = studentRepo.findByUsername(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(student.getUsername())
                    .password(student.getPassword())
                    .authorities(student.getRole())    // grants ROLE_STUDENT
                    .build();
        }

        // Then admin
        Optional<Admin> adminOpt = adminRepo.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .authorities(admin.getRole())      // grants ROLE_ADMIN
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }

}
