package com.sfms.studentfeedback.controller;

import com.sfms.studentfeedback.controller.request.UserLoginRequest;
import com.sfms.studentfeedback.controller.request.UserRegistrationRequest;
import com.sfms.studentfeedback.controller.response.JwtResponse;
import com.sfms.studentfeedback.security.JwtUtil;
import com.sfms.studentfeedback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request.getUserType(), request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest request) {
        // 1. Authenticate user credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Generate token (prefixing ROLE_ for clarity)
        String role = request.getUserType().toUpperCase();
        String token = jwtUtil.generateToken(request.getUsername(), role);

        // 3. Return token in response
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
