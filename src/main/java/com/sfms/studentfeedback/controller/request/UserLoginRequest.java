package com.sfms.studentfeedback.controller.request;

public class UserLoginRequest {
    private String userType;
    private String username;
    private String password;

    // Getters and setters
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
