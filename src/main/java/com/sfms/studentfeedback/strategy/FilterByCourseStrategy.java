package com.sfms.studentfeedback.strategy;

import com.sfms.studentfeedback.model.Feedback;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByCourseStrategy implements FeedbackFilterStrategy {

    @Override
    public List<Feedback> filter(List<Feedback> feedbackList, String course) {
        return feedbackList.stream()
                .filter(f -> f.getCourse() != null && f.getCourse().equalsIgnoreCase(course))
                .collect(Collectors.toList());
    }
}
