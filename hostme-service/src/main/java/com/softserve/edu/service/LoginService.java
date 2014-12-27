package com.softserve.edu.service;

import com.softserve.edu.model.User;

public interface LoginService {

	public User getUserByEmail(String email);

	public User getUserByLogin(String login);
}
