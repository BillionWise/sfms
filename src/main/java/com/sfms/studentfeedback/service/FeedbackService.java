package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.observer.AdminObserver;
import com.sfms.studentfeedback.observer.Observer;
import com.sfms.studentfeedback.observer.Subject;
import com.sfms.studentfeedback.repository.FeedbackRepository;
import com.sfms.studentfeedback.strategy.FeedbackFilterContext;
import com.sfms.studentfeedback.strategy.FilterByCourseStrategy;
import com.sfms.studentfeedback.strategy.FilterByStudentNameStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService implements Subject {

    private final FeedbackRepository feedbackRepository;
    private final List<Observer> observers = new ArrayList<>();

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;

        // Register one admin observer (you can add more later)
        registerObserver(new AdminObserver("Head Admin"));
    }

    public Feedback submitFeedback(Feedback feedback) {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        notifyObservers("New feedback submitted by: " + feedback.getStudentName());
        return savedFeedback;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // ✅ NEW METHOD: Filtering Feedbacks Using Strategy Pattern
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

    // Observer methods
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
