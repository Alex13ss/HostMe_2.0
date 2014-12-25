package com.softserve.edu.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.edu.dao.GenericDao;

public abstract class AbstractGenericDao<E, I extends Serializable> implements
		GenericDao<E, I> {



	private Class<E> entityClass;

	protected AbstractGenericDao() {
	}

	protected AbstractGenericDao(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entityClass)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return criteria.list();
	}


	@Override
	public Integer create(E entity) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(entity);
		return id;
	}

	@Override
	public E read(I id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		E fetchedEntity = (E) session.get(entityClass, id);
		return fetchedEntity;
	}

	@Override
	public void update(E entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public void delete(E entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}