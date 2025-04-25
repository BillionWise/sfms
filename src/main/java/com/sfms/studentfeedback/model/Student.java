package com.sfms.studentfeedback.model;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    private String fullName;
    private String matricNo;

    public Student() {
    }

    public Student(String username, String password, String fullName, String matricNo) {
        super(username, password);
        this.fullName = fullName;
        this.matricNo = matricNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }
}
