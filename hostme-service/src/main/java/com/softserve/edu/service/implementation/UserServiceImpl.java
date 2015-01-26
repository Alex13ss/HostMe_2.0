package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.UserService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public Integer addUser(User user) {
		return userDaoImpl.create(user);
	}

	@Override
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
//		return null;
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
    public Object findOneWithGroups(String name) {
        // TODO Auto-generated method stub
        return null;
    }
	
}
