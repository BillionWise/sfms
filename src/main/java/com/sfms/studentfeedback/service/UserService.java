package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.factory.UserFactory;
import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.model.User;
import com.sfms.studentfeedback.repository.AdminRepository;
import com.sfms.studentfeedback.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final StudentRepository studentRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(StudentRepository studentRepo, AdminRepository adminRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String userType, String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = UserFactory.createUser(userType, username, hashedPassword);

        if (user instanceof Student) {
            studentRepo.save((Student) user);
        } else if (user instanceof Admin) {
            adminRepo.save((Admin) user);
        }
    }

    public String loginUser(String username, String password, String userType) {
        if ("student".equalsIgnoreCase(userType)) {
            Student student = studentRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (!passwordEncoder.matches(password, student.getPassword())) {
                return "Incorrect password!";
            }
            return "Login successful!";
        } else if ("admin".equalsIgnoreCase(userType)) {
            Admin admin = adminRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (!passwordEncoder.matches(password, admin.getPassword())) {
                return "Incorrect password!";
            }
            return "Login successful!";
        }
        return "User type mismatch!";
    }
}
