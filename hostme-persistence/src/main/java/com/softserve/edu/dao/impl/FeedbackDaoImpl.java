package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.FeedbackDao;
import com.softserve.edu.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends AbstractGenericDao<Feedback, Long>
        implements FeedbackDao {
    
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void createFeedback(Feedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }
    
    @Override
    public void deleteFeedbackById(Integer feedbackId) {
        Session session = sessionFactory.getCurrentSession();
        Feedback feedback = (Feedback) session.get(Feedback.class, feedbackId);
        if (feedback != null) {
            sessionFactory.getCurrentSession().delete(feedback);
        }
    }
}
