package com.sfms.studentfeedback.service;

import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitFeedback() {
        Feedback feedback = new Feedback("student1", "Software Engineering", "Satisfactory");
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback result = feedbackService.submitFeedback(feedback);
        assertNotNull(result);
        assertEquals("student1", result.getStudentName());
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testGetAllFeedbacks() {
        List<Feedback> mockList = List.of(
                new Feedback("student1", "Advance Software", "Nice"),
                new Feedback("student2", "DevOps", "Average")
        );
        when(feedbackRepository.findAll()).thenReturn(mockList);

        List<Feedback> result = feedbackService.getAllFeedbacks();
        assertEquals(2, result.size());
    }
}
