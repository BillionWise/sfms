package com.sfms.studentfeedback.model;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String course;
    private String comment;

    public Feedback() {}

    public Feedback(String studentName, String course, String comment) {
        this.studentName = studentName;
        this.course = course;
        this.comment = comment;
    }

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
