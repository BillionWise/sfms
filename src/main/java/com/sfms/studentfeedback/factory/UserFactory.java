package com.sfms.studentfeedback.factory;

import com.sfms.studentfeedback.model.Admin;
import com.sfms.studentfeedback.model.Student;
import com.sfms.studentfeedback.model.User;

public class UserFactory {

    public static User createUser(String userType, String username, String password) {
        if (userType.equalsIgnoreCase("student")) {
            // We'll allow default values for simplicity for now
            return new Student(username, password, "Default Student", "MAT0000");
        } else if (userType.equalsIgnoreCase("admin")) {
            return new Admin(username, password);
        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
