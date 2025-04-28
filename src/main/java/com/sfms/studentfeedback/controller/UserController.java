package com.sfms.studentfeedback.controller;

import com.sfms.studentfeedback.service.UserService;
import com.sfms.studentfeedback.controller.request.UserRegistrationRequest;
import com.sfms.studentfeedback.controller.request.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        String userType = request.getUserType();
        String username = request.getUsername();
        String password = request.getPassword();

        userService.registerUser(userType, username, password);
        return ResponseEntity.ok(userType + " registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        String userType = request.getUserType();
        String username = request.getUsername();
        String password = request.getPassword();

        String result = userService.loginUser(userType, username, password);
        return ResponseEntity.ok(result);
    }
}
