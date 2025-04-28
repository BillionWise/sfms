package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.factory.UserFactory;
import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.model.User;
import com.sfms.studentfeedback.repository.AdminRepository;
import com.sfms.studentfeedback.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;

    public UserService(StudentRepository studentRepository, AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
    }

    public void registerUser(String userType, String username, String password) {
        User user = UserFactory.createUser(userType, username, password);

        if (user instanceof Student) {
            studentRepository.save((Student) user);
        } else if (user instanceof Admin) {
            adminRepository.save((Admin) user);
        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public String loginUser(String userType, String username, String password) {
        if ("student".equalsIgnoreCase(userType)) {
            Optional<Student> studentOpt = studentRepository.findByUsername(username);
            if (studentOpt.isEmpty()) {
                return "Student not found!";
            }
            Student student = studentOpt.get();
            if (!student.getPassword().equals(password)) {
                return "Incorrect password!";
            }
            return "Login successful!";
        } else if ("admin".equalsIgnoreCase(userType)) {
            Optional<Admin> adminOpt = adminRepository.findByUsername(username);
            if (adminOpt.isEmpty()) {
                return "Admin not found!";
            }
            Admin admin = adminOpt.get();
            if (!admin.getPassword().equals(password)) {
                return "Incorrect password!";
            }
            return "Login successful!";
        } else {
            return "Invalid user type!";
        }
    }
}
