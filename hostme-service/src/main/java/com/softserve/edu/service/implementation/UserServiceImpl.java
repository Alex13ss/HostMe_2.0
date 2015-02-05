package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.repositories.routes.PlaceRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.dto.ModeratorDto;
import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.PlaceService;

import java.util.*;

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
	
	@Autowired
	private PlaceRepository placeRepository;
	
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
	
	public List<ModeratorDto> getModeratorDtoList(List<User> users) {
	    	List<ModeratorDto> result = new ArrayList<>();
		for (User user : users) {
			result.add(new ModeratorDto(user));
		}
		return result;
	}

	@Override
	public List<User> getUsersLike(String search) {
		return userRepository.findByFirstNameContainingOrLastNameContaining(search, search);
	}
	
	@Override
	public List<User> findUsersByNamesOrLogin(String input) {
		return userRepository.findByFirstNameContainingOrLastNameContainingOrLoginContainingAllIgnoreCase(input, input, input);
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

	@Override
	@Transactional
	public Set<Place> getUserPlaces(Pageable pageable) {
		User currentUser = profileService.getCurrentUser();
		User user = userRepository.findByUserIdAndFetchPlacesEagerly(currentUser.getUserId());
		if (user == null) {
			return new HashSet<>();
		} else {
			return user.getPlaces();
		}
	}

	@Override
	public Collection<Place> getUserLikedPlaces(Pageable pageable) {
		return placeService.getLikedPlaces(pageable);
	}

	@Override
	public void setBookedPlace(int placeId) {
		int userId = profileService.getCurrentUser().getUserId();
		User user = userRepository.findByUserIdAndFetchBookedPlacesEagerly(userId);
		user.getBookedPlaces().add(placeService.getPlace(placeId));
		userRepository.save(user);
	}

	@Override
	public Collection<Place> getUserBookedPlaces(Pageable pageable) {
		return placeRepository.findByBookedBy(profileService.getCurrentUser());
	}
}