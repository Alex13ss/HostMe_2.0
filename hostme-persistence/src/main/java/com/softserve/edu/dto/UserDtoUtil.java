package com.softserve.edu.dto;

import com.softserve.edu.model.User;

public interface UserDtoUtil {
	public User formRegisteredUser(User user, long dateOfBirthMili, String gender);

}
