package com.sfms.studentfeedback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

    @Column(name = "role", nullable = false)
    private String role = "ROLE_ADMIN";

    public Admin() {
        super(); // Call User's no-arg constructor
    }

    public Admin(String username, String password) {
        super(username, password); // Call User's constructor with username + password
        this.role = "ROLE_ADMIN";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
