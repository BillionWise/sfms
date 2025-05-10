package com.sfms.studentfeedback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfms.studentfeedback.model.Feedback;
import com.sfms.studentfeedback.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FeedbackControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

    @Test
    void testSubmitFeedback() throws Exception {
        Feedback feedback = new Feedback("student1", "Software Engineering", "Excellent");
        when(feedbackService.submitFeedback(any(Feedback.class))).thenReturn(feedback);

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("student1"))
                .andExpect(jsonPath("$.courseName").value("Software Engineering"))
                .andExpect(jsonPath("$.comment").value("Excellent"));
    }

    @Test
    void testGetAllFeedbacks() throws Exception {
        List<Feedback> feedbackList = List.of(
                new Feedback("student1", "Course A", "Great"),
                new Feedback("student2", "Course B", "Good")
        );
        when(feedbackService.getAllFeedbacks()).thenReturn(feedbackList);

        mockMvc.perform(get("/api/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].studentName").value("student1"));
    }
}
