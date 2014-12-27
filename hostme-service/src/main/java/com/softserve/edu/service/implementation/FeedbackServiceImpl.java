package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.FeedbackDao;
import com.softserve.edu.model.Feedback;
import com.softserve.edu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    
    @Autowired
    FeedbackDao feedbackDao;
    
    @Transactional
    public void createFeedback(Feedback feedback) {
        feedbackDao.createFeedback(feedback);
    }
    
    @Override
    @Transactional
    public void deleteFeedbackById(Integer feedbackId) {
        feedbackDao.deleteFeedbackById(feedbackId);
    }
}
