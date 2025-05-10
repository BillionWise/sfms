package com.sfms.studentfeedback.controller;

import com.sfms.studentfeedback.controller.request.UserRegistrationRequest;
import com.sfms.studentfeedback.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc; // This will now be auto-wired by Spring Boot

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void testRegisterUser() throws Exception {
        // Given
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("newuser");
        request.setPassword("password123");
        request.setUserType("student");

        // When
        ResultActions result = mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"newuser\", \"password\": \"password123\", \"userType\": \"student\" }"));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));

        // Verify that userService.registerUser was called once
        verify(userService, times(1)).registerUser("student", "newuser", "password123");
    }

    @Test
    void testLoginUser() throws Exception {
        // Prepare login request and expected JWT response (for simplicity)
        String loginRequestJson = "{ \"username\": \"newuser\", \"password\": \"password123\", \"userType\": \"student\" }";
        String expectedJwtToken = "mocked-jwt-token";

        // Mock successful login and JWT token generation
        when(userService.loginUser(anyString(), anyString(), anyString())).thenReturn(expectedJwtToken);

        // When
        ResultActions result = mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().json("{\"token\": \"mocked-jwt-token\"}"));

        // Verify the loginUser method was called with the correct parameters
        verify(userService, times(1)).loginUser("newuser", "password123", "student");
    }
}
