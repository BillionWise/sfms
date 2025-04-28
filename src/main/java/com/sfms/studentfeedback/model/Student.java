package com.sfms.studentfeedback.model;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    public Student() {
        super(); // ✅ Call User's no-arg constructor
    }

    public Student(String username, String password) {
        super(username, password); // ✅ Call User's constructor with username + password
    }
}
