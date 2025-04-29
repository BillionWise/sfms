package com.sfms.studentfeedback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {

    @Column(name = "role", nullable = false)
    private String role = "ROLE_STUDENT";

    public Student() {
        super(); // Call User's no-arg constructor
    }

    public Student(String username, String password) {
        super(username, password); // Call User's constructor with username + password
        this.role = "ROLE_STUDENT";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
