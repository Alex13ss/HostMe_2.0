package com.softserve.edu.dao;

import com.softserve.edu.entity.User;

public interface UserDao extends GenericDao<User, Integer> {
	public User getUserByEmail(String email);

	public User getUserByLogin(String login);
}
