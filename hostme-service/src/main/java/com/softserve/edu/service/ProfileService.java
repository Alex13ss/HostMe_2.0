package com.softserve.edu.service;

import java.util.Calendar;

import com.softserve.edu.entity.User;

public interface ProfileService {

	public User getUser(Integer id);

	public User getUserByLogin(String login);

	public int calcAge(User user);

	public String receiveBirthday(Calendar birth);

	public Calendar birthToDateFormat(String birth);

}