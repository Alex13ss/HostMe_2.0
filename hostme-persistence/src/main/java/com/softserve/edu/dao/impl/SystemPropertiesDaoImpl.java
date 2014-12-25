package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.SystemPropertiesDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.entity.SystemProperties;

@Repository
public class SystemPropertiesDaoImpl extends
		AbstractGenericDao<SystemProperties, Integer> implements
		SystemPropertiesDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public SystemPropertiesDaoImpl() {
		super(SystemProperties.class);
	}
	
	@Override
	public String getPropeties(String prop) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(SystemProperties.class);
		cr.setProjection(Projections.property("value"));
		cr.add(Restrictions.eq("propKey", prop));
		String property = (String) cr.uniqueResult();
		return property;
	}
    
}
