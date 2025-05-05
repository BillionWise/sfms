package com.sfms.studentfeedback.controller;

import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedback/form")
    public String showFeedbackForm() {
        return "submit_feedback";  // Looks for submit_feedback.html in templates
    }

    @GetMapping("/feedbacks")
    public String viewFeedbacks(Model model) {
        List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbackList);
        return "view_feedbacks";
    }
}
