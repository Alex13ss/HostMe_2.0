package com.softserve.edu.service;

import com.softserve.edu.model.User;
import com.softserve.edu.model.UserState;

public interface RegistrationService {

	public void register(User user);

	public void register(User user, String date, String gender, UserState userState);

	public User getUserByEmail(String email);

	public User getUserByLogin(String login);
	
	public void activateAccount(Integer userId);

}
