package com.sfms.studentfeedback.factory;

import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.model.User;

public class UserFactory {
    /**
     * Build a Student or Admin based on the userType string.
     */
    public static User createUser(String userType, String username, String password) {
        if ("student".equalsIgnoreCase(userType)) {
            return new Student(username, password);
        } else if ("admin".equalsIgnoreCase(userType)) {
            return new Admin(username, password);
        }
        throw new IllegalArgumentException("Unknown userType: " + userType);
    }
}
