package com.softserve.edu.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.entity.User;
import com.softserve.edu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
//	@Autowired
//	private UserDao userDaoImpl;
//
//	@Override
//	@Transactional
//	public Integer addUser(User user) {
//		return userDaoImpl.create(user);
//	}
//
//	@Override
//	@Transactional
//	public List<User> getAllUsers() {
//		return null;
//	}

//	@Override
//	@Transactional
//	public void updateUser(User user) {
//		userDaoImpl.update(user);
//	}
//
//	@Override
//	@Transactional
//	public User getUser(Integer id) {
//		return userDaoImpl.read(id);
//	}
//
//	@Override
//	@Transactional
//	public void removeUser(Integer id) {
//		User user = userDaoImpl.read(id);
//		userDaoImpl.delete(user);
//	}
//
//
//	@Override
//	@Transactional
//	public void initilizeUserLanguages(User user) {
//		userDaoImpl.update(user);
//		Hibernate.initialize(user.getLanguages());
//	}
//
//	@Override
//	public Integer addUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initilizeUserLanguages(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
