package com.softserve.edu.service;

import com.softserve.edu.model.Feedback;

public interface FeedbackService {
    
    public void createFeedback(Feedback feedback);
    
    public void deleteFeedbackById(Integer feedbackId);
    
}
