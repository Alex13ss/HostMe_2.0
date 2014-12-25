package com.softserve.edu.service.implementation;

import java.util.List;

import com.softserve.edu.entity.User;
import com.softserve.edu.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDaoImpl;

	@Override
	@Transactional
	public Integer addUser(User user) {
		return userDaoImpl.create(user);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDaoImpl.update(user);
	}

	@Override
	@Transactional
	public User getUser(Integer id) {
		return userDaoImpl.read(id);
	}

	@Override
	@Transactional
	public void removeUser(Integer id) {
		User user = userDaoImpl.read(id);
		userDaoImpl.delete(user);
	}


	@Override
	@Transactional
	public void initilizeUserLanguages(User user) {
		userDaoImpl.update(user);
		Hibernate.initialize(user.getLanguages());
	}
	
}
