package com.softserve.edu.dto.implementation;

import com.softserve.edu.dto.UserDtoUtil;
import com.softserve.edu.model.Gender;
import com.softserve.edu.model.Role;
import com.softserve.edu.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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
		registeredUser.setRoleId(USER);
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
