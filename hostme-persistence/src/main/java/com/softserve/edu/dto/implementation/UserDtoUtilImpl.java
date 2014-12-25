package com.softserve.edu.dto.implementation;

import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.UserDtoUtil;
import com.softserve.edu.entity.Gender;
import com.softserve.edu.entity.Role;
import com.softserve.edu.entity.User;

@Service
public class UserDtoUtilImpl implements UserDtoUtil {

	private final int USER = 1;

	@Override
	public User formRegisteredUser(User user, long dateOfBirthMili, String gender) {
		user.setGender(getGender(gender));
		Calendar birthday= Calendar.getInstance();
		birthday.setTimeInMillis(dateOfBirthMili);
		user.setBirthday(birthday);
		Role registeredUser = new Role();
		registeredUser.setId(USER);
		user.setRole(registeredUser);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return user;
	}
	private Gender getGender(String gender) {
		for (Gender temporaryGender : Gender.values()) {
			if (gender.equalsIgnoreCase(temporaryGender.toString())) {
				return temporaryGender;
			}
		}
		return Gender.UNSPECIFIED;

	}

}
