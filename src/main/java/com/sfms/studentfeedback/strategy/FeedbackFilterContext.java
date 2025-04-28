package com.sfms.studentfeedback.strategy;

import com.sfms.studentfeedback.model.Feedback;
import java.util.List;

public class FeedbackFilterContext {

    private FeedbackFilterStrategy strategy;

    // Constructor (optional)
    public FeedbackFilterContext() {}

    // Set Strategy
    public void setStrategy(FeedbackFilterStrategy strategy) {
        this.strategy = strategy;
    }

    // Use Strategy
    public List<Feedback> filterFeedback(List<Feedback> feedbackList, String value) {
        if (strategy == null) {
            throw new IllegalStateException("Filter strategy not set!");
        }
        return strategy.filter(feedbackList, value);
    }
}
