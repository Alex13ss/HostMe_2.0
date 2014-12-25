package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.entity.User;

public interface UserService {

	public Integer addUser(User user);

	public List<User> getAllUsers();

	public void updateUser(User user);

	public User getUser(Integer id);

	public void removeUser(Integer id);
	
	public void initilizeUserLanguages(User user);

}
