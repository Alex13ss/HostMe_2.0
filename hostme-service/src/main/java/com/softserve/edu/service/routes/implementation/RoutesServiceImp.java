package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.model.City;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.routes.RouteRepository;
import com.softserve.edu.service.LoginService;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoutesServiceImp implements RoutesService{

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    LoginService loginService;

    public void addRoute(Route route) {
        route.setUser(getCurrentUser());
        routeRepository.save(route);
    }

    public List<Route> getCurrentUserRoutes() {
        return new ArrayList<>(getCurrentUser().getRoutes());
    }

    public List<Route> getRoutesNearToUsers() {
        List<Route> userRoutes = getCurrentUserRoutes();
        Set<City> userRoutesCities = new HashSet<>();
        for (Route route : userRoutes) {
            for (Place place : route.getPlaces()) {
                userRoutesCities.add(place.getCity());
            }
        }
        List<Route> result = new ArrayList<>();
        List<Route> allRoutes = new ArrayList<>((List<Route>)routeRepository.findAll());
        for (Route route : allRoutes) {
            for (Place place : route.getPlaces()) {
                if (userRoutesCities.contains(place.getCity())) {
                    result.add(route);
                }
            }
        }
        return result;
    }

    private User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        return loginService.getUserByLogin(login);
    }
}
