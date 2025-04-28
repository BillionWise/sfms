package com.sfms.studentfeedback.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
        super(); // ✅ Call User's no-arg constructor
    }

    public Admin(String username, String password) {
        super(username, password); // ✅ Call User's constructor with username + password
    }
}
