package com.sfms.studentfeedback.controller.request;

public class UserLoginRequest {
    private String username;
    private String password;
    private String userType;

    public UserLoginRequest(String testuser, String testpass, String student) {
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
