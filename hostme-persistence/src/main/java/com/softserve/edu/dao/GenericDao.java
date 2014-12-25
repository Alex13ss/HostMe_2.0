package com.softserve.edu.dao;

import java.util.List;

import org.hibernate.Session;

public interface GenericDao<E, I> {

	public List<E> getAll();

	Integer create(E entity);

	E read(I id);

	void update(E entity);

	void delete(E entity);

	Session getSession();
}
