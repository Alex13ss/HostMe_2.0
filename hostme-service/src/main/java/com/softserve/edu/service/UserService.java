package com.softserve.edu.service;

import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

import java.util.List;
import java.util.Set;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUsers();

    public List<UserDto> getUserDtoList(List<User> users);

    public List<User> getUsersLike(String search);

    public void updateUser(User user);

    public User getUser(Integer id);

    public Set<Place> getBookedPlaces(int userId);

    public void addBookedPlace(int placeId);

    public void removeUser(Integer id);

    public void initilizeUserLanguages(User user);

    public Object findOneWithGroups(String name);

}
