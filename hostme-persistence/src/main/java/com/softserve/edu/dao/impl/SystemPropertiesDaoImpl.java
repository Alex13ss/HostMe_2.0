package com.softserve.edu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.softserve.edu.dao.SystemPropertiesDao;
import com.softserve.edu.model.SystemProperties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemPropertiesDaoImpl extends
	AbstractGenericDao<SystemProperties, Integer> implements
	SystemPropertiesDao {

    @PersistenceContext()
    private EntityManager entityManager;

    public SystemPropertiesDaoImpl() {
	super(SystemProperties.class);
    }

    @Override
    public String getPropeties(String prop) {
	Session session = (Session) entityManager.getDelegate();
	Criteria cr = session.createCriteria(SystemProperties.class);
	cr.setProjection(Projections.property("value"));
	cr.add(Restrictions.eq("propKey", prop));
	String property = (String) cr.uniqueResult();
	return property;
    }

}
