package com.sfms.studentfeedback.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    private String role = "ADMIN";  // optional field

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    public String getRole() {
        return role;
    }
}
