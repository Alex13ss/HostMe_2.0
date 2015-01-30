package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User getUser(Integer id) {
		return userDaoImpl.read(id);
	}

	@Override
	public List<Place> getBookedPlaces() {
		return null;
	}

	@Override
	@Transactional
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}


	public User getCurrentUser() {
		return getUserByLogin(SecurityContextHolder.getContext().getAuthentication()
				.getName());
	}

	public int calcAge(User user) {

		Calendar now = new GregorianCalendar();
		int age = now.get(Calendar.YEAR)
				- user.getBirthday().get(Calendar.YEAR);
		if ((user.getBirthday().get(Calendar.MONTH) > now.get(Calendar.MONTH))
				|| (user.getBirthday().get(Calendar.MONTH)) == now
						.get(Calendar.MONTH)
				&& user.getBirthday().get(Calendar.DAY_OF_MONTH) > now
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}

	@Override
	public String receiveBirthday(Calendar birth) {

		return new SimpleDateFormat("MM/dd/yyyy").format(birth.getTime());
	}

	
	@Override
	public Calendar birthToDateFormat(String birth) {
		
		Calendar birthday = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				sdf.getCalendar().setTime(new SimpleDateFormat("MM/dd/yyyy").parse(birth));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			birthday = sdf.getCalendar();

		return birthday;
	}
}
