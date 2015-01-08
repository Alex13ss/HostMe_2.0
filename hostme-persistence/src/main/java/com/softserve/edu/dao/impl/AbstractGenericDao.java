package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractGenericDao<E, I extends Serializable> implements
	GenericDao<E, I> {

    private Class<E> entityClass;

    protected AbstractGenericDao() {
    }

    protected AbstractGenericDao(Class<E> entityClass) {
	super();
	this.entityClass = entityClass;
    }

    @PersistenceContext()
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() {
	Session session = (Session) entityManager.getDelegate();
	Criteria criteria = session.createCriteria(entityClass)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	return criteria.list();
    }

    @Override
    public Integer create(E entity) {
	Session session = (Session) entityManager.getDelegate();
	Integer id = (Integer) session.save(entity);
	return id;
    }

    @Override
    public E read(I id) {
	Session session = (Session) entityManager.getDelegate();
	@SuppressWarnings("unchecked")
	E fetchedEntity = (E) session.get(entityClass, id);
	return fetchedEntity;
    }

    @Override
    public void update(E entity) {
	Session session = (Session) entityManager.getDelegate();
	session.update(entity);
    }

    @Override
    public void delete(E entity) {
	Session session = (Session) entityManager.getDelegate();
	session.delete(entity);
    }

    @Override
    public Session getSession() {
	return (Session) entityManager.getDelegate();
    }

}