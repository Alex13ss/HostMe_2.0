package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.RequestDao;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.exceptions.RequestAlreadySentException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestDaoImpl extends AbstractGenericDao<Request, Integer> implements
        RequestDao {
    public RequestDaoImpl() {
        super(Request.class);
    }
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override
    public boolean checkRequest(Request request)
            throws RequestAlreadySentException {
    	Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Request.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    	criteria.add(Restrictions.eq("author.userId", request.getAuthor().getUserId()));
    	criteria.add(Restrictions.eq("receiver.userId", request.getReceiver().getUserId()));
    	criteria.add(Restrictions.eq("beginDate", request.getBeginDate()));
    	criteria.add(Restrictions.eq("endDate", request.getEndDate()));
        @SuppressWarnings("unchecked")
		ArrayList<Request> existingRequests = (ArrayList<Request>) criteria.list();
        //
        // Criteria criteria = getSessionFactory().getCurrentSession()
        // .createCriteria(Request.class);
        // criteria.setFetchMode("request.hosting", FetchMode.JOIN);
        // criteria.add(Restrictions.eq("endDate", request.getEndDate()));
        // criteria.add(Restrictions.eq("beginDate", request.getBeginDate()));
        // criteria.add(Restrictions.eq("hosting_id", request.getHosting()));
        // ArrayList<Request> existingRequests = (ArrayList<Request>)
        // criteria.list();
        if (!existingRequests.isEmpty()) {
            throw new RequestAlreadySentException();
        }
        return true;
    }
    
    @Override
    public List<Request> getAllApprovedRequestsByHostingId(int hostingId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Request.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        cr.add(Restrictions.eq("status", Status.APPROVED)).add(Restrictions.eq("hosting.hostingId", hostingId));
        
        return cr.list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> getMySentRequest(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("author.userId", userId));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Request> getMyReceivedRequest(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("receiver.userId", userId));
		return criteria.list();
	}
}
