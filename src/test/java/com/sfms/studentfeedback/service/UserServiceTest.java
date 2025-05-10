package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.repository.AdminRepository;
import com.sfms.studentfeedback.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private AdminRepository adminRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterStudent() {
        String username = "student1";
        String rawPassword = "pass123";
        String encodedPassword = "encodedPass";

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.registerUser("student", username, rawPassword);

        verify(studentRepo, times(1)).save(any(Student.class));
        verify(adminRepo, never()).save(any());
    }

    @Test
    void testRegisterAdmin() {
        String username = "admin1";
        String rawPassword = "adminpass";
        String encodedPassword = "encodedAdminPass";

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.registerUser("admin", username, rawPassword);

        verify(adminRepo, times(1)).save(any(Admin.class));
        verify(studentRepo, never()).save(any());
    }

    @Test
    void testLoginStudentSuccess() {
        String username = "student1";
        String rawPassword = "password123";
        String encodedPassword = "hashedPassword";

        Student student = new Student(username, encodedPassword);

        when(studentRepo.findByUsername(username)).thenReturn(Optional.of(student));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        String result = userService.loginUser(username, rawPassword, "student");
        assertEquals("Login successful!", result);
    }

    @Test
    void testLoginAdminIncorrectPassword() {
        String username = "admin1";
        String rawPassword = "wrongpass";
        String encodedPassword = "realPass";

        Admin admin = new Admin(username, encodedPassword);

        when(adminRepo.findByUsername(username)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        String result = userService.loginUser(username, rawPassword, "admin");
        assertEquals("Incorrect password!", result);
    }

    @Test
    void testLoginUserNotFound() {
        when(studentRepo.findByUsername("ghost")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loginUser("ghost", "any", "student");
        });
    }

    @Test
    void testLoginInvalidUserType() {
        String result = userService.loginUser("userX", "pass", "unknown");
        assertEquals("User type mismatch!", result);
    }
}
