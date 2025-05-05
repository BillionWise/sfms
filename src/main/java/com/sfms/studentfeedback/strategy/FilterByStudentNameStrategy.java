package com.sfms.studentfeedback.strategy;

import com.sfms.studentfeedback.model.Feedback;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByStudentNameStrategy implements FeedbackFilterStrategy {

    @Override
    public List<Feedback> filter(List<Feedback> feedbackList, String studentName) {
        return feedbackList.stream()
                .filter(f -> f.getStudentName() != null && f.getStudentName().equalsIgnoreCase(studentName))
                .collect(Collectors.toList());
    }
}
