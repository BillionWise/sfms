package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.observer.AdminObserver;
import com.sfms.studentfeedback.observer.FeedbackNotifier;
import com.sfms.studentfeedback.repository.FeedbackRepository;
import com.sfms.studentfeedback.strategy.FilterByCourseStrategy;
import com.sfms.studentfeedback.strategy.FilterByStudentNameStrategy;
import com.sfms.studentfeedback.strategy.FeedbackFilterContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackNotifier notifier;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
        this.notifier = new FeedbackNotifier();

        // Registering admin observers (can be loaded from DB/config in real-world scenarios)
        notifier.registerObserver(new AdminObserver("Admin1"));
        notifier.registerObserver(new AdminObserver("Admin2"));
    }

    public Feedback submitFeedback(Feedback feedback) {
        Feedback savedFeedback = feedbackRepository.save(feedback);

        // Notify all admin observers
        String message = "New feedback submitted by " + feedback.getStudentName();
        notifier.notifyObservers(message);

        return savedFeedback;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> filterFeedback(String by, String value) {
        FeedbackFilterContext context = new FeedbackFilterContext();
        if ("course".equalsIgnoreCase(by)) {
            context.setStrategy(new FilterByCourseStrategy());
        } else if ("student".equalsIgnoreCase(by)) {
            context.setStrategy(new FilterByStudentNameStrategy());
        } else {
            throw new IllegalArgumentException("Unsupported filter type: " + by);
        }

        List<Feedback> allFeedbacks = feedbackRepository.findAll();
        return context.filterFeedback(allFeedbacks, value);
    }
}
