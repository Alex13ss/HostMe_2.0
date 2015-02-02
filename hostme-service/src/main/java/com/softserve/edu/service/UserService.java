package com.softserve.edu.service;

import java.util.List;
import java.util.Set;

import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUsers();

    public List<UserDto> getUserDtoList(List<User> users);

    public List<User> getUsersLike(String search);
    
    public List<User> findUsersByNamesOrLogin(String input);

    public void updateUser(User user);

    public User getUser(Integer id);

    public void addBookedPlace(int placeId);

    public Set<Place> getBookedPlaces();

    public Set<Place> getBookedPlaces(int userId);

    public void removeUser(Integer id);

    public void initilizeUserLanguages(User user);

    public Object findOneWithGroups(String name);

}