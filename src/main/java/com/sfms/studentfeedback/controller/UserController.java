//package com.sfms.studentfeedback.controller;
//
//import com.sfms.studentfeedback.controller.request.UserLoginRequest;
//import com.sfms.studentfeedback.controller.request.UserRegistrationRequest;
//import com.sfms.studentfeedback.controller.response.JwtResponse;
//import com.sfms.studentfeedback.service.UserService;
//import com.sfms.studentfeedback.security.JwtUtil;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//
//    public UserController(UserService userService, JwtUtil jwtUtil) {
//        this.userService = userService;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest req) {
//        userService.registerUser(req.getUserType(), req.getUsername(), req.getPassword());
//        return ResponseEntity.ok("Registered");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLoginRequest req) {
//        // Call the loginUser method from UserService
//        String result = userService.loginUser(req.getUsername(), req.getPassword(), req.getUserType());
//
//        if ("Login successful!".equals(result)) {
//            // If login is successful, generate the JWT token
//            String token = jwtUtil.generateToken(req.getUsername(), req.getUserType());
//            return ResponseEntity.ok(new JwtResponse(token));
//        } else {
//            return ResponseEntity.status(401).body(result); // Unauthorized
//        }
//    }
//}
