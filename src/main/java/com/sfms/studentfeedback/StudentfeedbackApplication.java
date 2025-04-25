package com.sfms.studentfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentfeedbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentfeedbackApplication.class, args);
    }

}







//package com.sfms.studentfeedback;
//
//import com.sfms.studentfeedback.model.Feedback;
//import com.sfms.studentfeedback.repository.FeedbackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class StudentfeedbackApplication implements CommandLineRunner {
//
//    @Autowired
//    private FeedbackRepository feedbackRepository;
//
//    public static void main(String[] args) {
//        SpringApplication.run(StudentfeedbackApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create a sample feedback and save it to the database
//        Feedback feedback = new Feedback("Uthman Ipadeola", "Java Basics", "This course is fire 🔥");
//        feedbackRepository.save(feedback);
//
//        // Print out all feedbacks stored in the DB
//        System.out.println("------ ALL FEEDBACKS ------");
//        feedbackRepository.findAll().forEach(f ->
//                System.out.println(f.getStudentName() + " - " + f.getCourse() + ": " + f.getComment()));
//    }
//}
//
