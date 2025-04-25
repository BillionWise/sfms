package com.sfms.studentfeedback.repository;

import com.sfms.studentfeedback.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
