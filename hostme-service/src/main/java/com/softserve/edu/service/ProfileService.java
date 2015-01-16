package com.softserve.edu.service;

import com.softserve.edu.model.User;

import java.util.Calendar;

public interface ProfileService {

	public User getUser(Integer id);

	public User getUserByLogin(String login);

	public User getCurrentUser();

	public int calcAge(User user);

	public String receiveBirthday(Calendar birth);

	public Calendar birthToDateFormat(String birth);

}