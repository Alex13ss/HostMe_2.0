package com.softserve.edu.service;

import com.softserve.edu.entity.User;
import com.softserve.edu.entity.UserState;

public interface RegistrationService {

	public void register(User user);

	public void register(User user, String date, String gender, UserState userState);

	public User getUserByEmail(String email);

	public User getUserByLogin(String login);
	
	public void activateAccount(Integer userId);

}
