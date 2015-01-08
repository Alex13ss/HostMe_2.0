package com.softserve.edu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.softserve.edu.dao.FeedbackDao;
import com.softserve.edu.model.Feedback;

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
    
    @PersistenceContext()
    private EntityManager entityManager;
    
    @Override
    public void createFeedback(Feedback feedback) {
	Session session = (Session) entityManager.getDelegate();
        session.save(feedback);
    }
    
    @Override
    public void deleteFeedbackById(Integer feedbackId) {
	Session session = (Session) entityManager.getDelegate();
        Feedback feedback = (Feedback) session.get(Feedback.class, feedbackId);
        if (feedback != null) {
            session.delete(feedback);
        }
    }
}
