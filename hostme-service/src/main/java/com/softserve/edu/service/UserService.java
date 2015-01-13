package com.softserve.edu.service;

import com.softserve.edu.model.User;

import java.util.List;

public interface UserService {

	public Integer addUser(User user);

	public List<User> getAllUsers();

	public void updateUser(User user);

	public User getUser(Integer id);

	public void removeUser(Integer id);
	
	public void initilizeUserLanguages(User user);

    public Object findOneWithGroups(String name);

}
