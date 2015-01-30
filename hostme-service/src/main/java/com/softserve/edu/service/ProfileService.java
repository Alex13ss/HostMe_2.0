package com.softserve.edu.service;

import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

import java.util.Calendar;
import java.util.List;

public interface ProfileService {

	public User getUser(Integer id);

	public List<Place> getBookedPlaces();

	public User getUserByLogin(String login);

	public User getCurrentUser();

	public int calcAge(User user);

	public String receiveBirthday(Calendar birth);

	public Calendar birthToDateFormat(String birth);

}