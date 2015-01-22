package com.softserve.edu.service;

import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;

import java.util.List;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUsers();

    public List<UserDto> getUserDtoList(List<User> users);

    public List<User> getUsersLike(String search);

    public void updateUser(User user);

    public User getUser(Integer id);

    public void removeUser(Integer id);

    public void initilizeUserLanguages(User user);

    public Object findOneWithGroups(String name);

}
