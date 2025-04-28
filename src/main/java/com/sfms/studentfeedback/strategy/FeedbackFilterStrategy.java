package com.sfms.studentfeedback.strategy;

import com.sfms.studentfeedback.model.Feedback;
import java.util.List;

public interface FeedbackFilterStrategy {
    List<Feedback> filter(List<Feedback> feedbackList, String value);
}
