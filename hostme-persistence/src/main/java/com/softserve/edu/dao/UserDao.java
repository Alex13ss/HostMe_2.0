package com.softserve.edu.dao;

import com.softserve.edu.model.User;

public interface UserDao extends GenericDao<User, Integer> {
	public User getUserByEmail(String email);

	public User getUserByLogin(String login);
	
	public void activateAccount(Integer userId);
}
