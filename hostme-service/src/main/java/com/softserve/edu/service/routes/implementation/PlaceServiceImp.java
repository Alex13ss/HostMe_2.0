package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.service.LoginService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaceServiceImp implements PlaceService{

    @Autowired
    LoginService loginService;

    @Autowired
    PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return (ArrayList<Place>) placeRepository.findAll();
    }

    public List<Place> getPlacesNearToUser() {
        ArrayList<Place> result = new ArrayList<>();
        User user = getCurrentUser();
        for (Place place : placeRepository.findAll()) {
            if (user.getCity() == place.getCity()) {
                result.add(place);
            }
        }
        return result;
    }

    public List<Place> getUserPlaces() {
        return new ArrayList<>(getCurrentUser().getPlaces());
    }

    private User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        return loginService.getUserByLogin(login);
    }
}
