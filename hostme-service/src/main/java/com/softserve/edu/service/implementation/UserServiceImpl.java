package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.PlaceService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private PlaceService placeService;

	@Override
	@Transactional
	public Integer addUser(User user) {
		return userDaoImpl.create(user);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return (List<User>)userRepository.findAll();
	}

	public List<UserDto> getUserDtoList(List<User> users) {
		List<UserDto> result = new ArrayList<>();
		for (User user : users) {
			result.add(new UserDto(user));
		}
		return result;
	}

	@Override
	public List<User> getUsersLike(String search) {
		return userRepository.findByFirstNameContainingOrLastNameContaining(search, search);
	}
	
	@Override
	public List<User> findUsersByNamesOrLogin(String input) {
		return (List<User>) userRepository.findByFirstNameContainingOrLastNameContainingOrLoginContainingAllIgnoreCase(input, input, input);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDaoImpl.update(user);
	}

	@Override
	@Transactional
	public User getUser(Integer id) {
		return userDaoImpl.read(id);
	}

	@Override
	@Transactional
	public Set<Place> getBookedPlaces() {
		User currentUser = profileService.getCurrentUser();
		User user = userRepository.findByUserIdAndFetchBookedPlacesEagerly(currentUser.getUserId());
		if (user == null) {
			return new HashSet<>();
		} else {
			return user.getBookedPlaces();
		}
	}

	@Override
	public Set<Place> getBookedPlaces(int userId) {
		return userRepository.findOne(userId).getBookedPlaces();
	}

	@Override
	@Transactional
	public void addBookedPlace(int placeId) {
		User user = profileService.getCurrentUser();
		Set<Place> places = getBookedPlaces(user.getUserId());
		Place selectedPlace = placeService.getPlace(placeId);
		places.add(selectedPlace);
		System.out.println("Done");
	}

	@Override
	@Transactional
	public void removeUser(Integer id) {
		User user = userDaoImpl.read(id);
		userDaoImpl.delete(user);
	}


	@Override
	@Transactional
	public void initilizeUserLanguages(User user) {
		userDaoImpl.update(user);
		Hibernate.initialize(user.getLanguages());
	}

}
