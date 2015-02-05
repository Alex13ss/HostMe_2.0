package com.softserve.edu.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.dto.ModeratorDto;
import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

import org.springframework.data.domain.Pageable;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUsers();

    public List<UserDto> getUserDtoList(List<User> users);
    
    public List<ModeratorDto> getModeratorDtoList(List<User> users);

    public List<User> getUsersLike(String search);
    
    public List<User> findUsersByNamesOrLogin(String input);

    public void updateUser(User user);

    public User getUser(Integer id);

    public void removeUser(Integer id);

    public void initilizeUserLanguages(User user);

    public Set<Place> getUserPlaces(Pageable pageable);

    public Collection<Place> getUserLikedPlaces(Pageable pageable);

    public void setBookedPlace(int placeId);

    public Collection<Place> getUserBookedPlaces(Pageable pageable);
}
