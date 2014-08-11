package com.softserve.edu.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.entity.Gender;
import com.softserve.edu.entity.Role;
import com.softserve.edu.entity.User;
import com.softserve.edu.entity.UserRole;
import com.softserve.edu.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	private final int USER = 1;

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void register(User user) {
		userDao.create(user);

	}

	private Calendar toDateFormat(String date) {
		Calendar birthday = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.getCalendar().setTime(sdf.parse(date));
			birthday = sdf.getCalendar();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return birthday;
	}

	public Gender getGender(String gender) {
		for (Gender temporaryGender : Gender.values()) {
			if (gender.equalsIgnoreCase(temporaryGender.toString())) {
				return temporaryGender;
			}
		}
		return Gender.UNSPECIFIED;

	}

	@Override
	@Transactional
	public void register(User user, String date, String gender) {
		user.setGender(getGender(gender));
		user.setBirthday(toDateFormat(date));
		Role registeredUser = new Role();
		registeredUser.setId(USER);
		user.setRole(registeredUser);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		register(user);

	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		User user = null;
		try {
			user = userDao.getUserByEmail(email);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Transactional
	public User getUserByLogin(String login) {
		User user = null;
		try {
			user = userDao.getUserByLogin(login);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
