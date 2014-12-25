package com.softserve.edu.dao;

import com.softserve.edu.entity.Feedback;

public interface FeedbackDao extends GenericDao<Feedback, Long> {
    public void createFeedback(Feedback feedback);
    
    public void deleteFeedbackById(Integer feedbackId);
}
