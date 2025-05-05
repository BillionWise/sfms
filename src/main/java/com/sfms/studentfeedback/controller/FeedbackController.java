package com.sfms.studentfeedback.controller;

import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Endpoint to Submit Feedback
    @PostMapping("/submit")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback) {
        // feedback.studentName, course and comment must be set in the JSON body
        feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully!");
    }

    // Endpoint to Get All Feedbacks
    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    // Filter Feedbacks by Course or Student Name
    @GetMapping("/filter")
    public ResponseEntity<List<Feedback>> filterFeedbacks(
            @RequestParam String by,
            @RequestParam String value) {
        return ResponseEntity.ok(feedbackService.filterFeedback(by, value));
    }
}
