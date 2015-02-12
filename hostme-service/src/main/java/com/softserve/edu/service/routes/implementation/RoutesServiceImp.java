package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.routes.RouteRepository;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoutesServiceImp implements RoutesService{

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ProfileService profileService;

    public void addRoute(Route route) {
        route.setUser(profileService.getCurrentUser());
        routeRepository.save(route);
    }

    public Route findRoute(int id) {
        return routeRepository.findWithFetchPlaces(id);
    }

    @Override
    public Place getRouteOrigin(Route route) {
        final int ORIGIN = 0;
        return route.getPlaces().get(ORIGIN);
    }

    @Override
    public Place getRouteDestination(Route route) {
        final int DESTINATION = 1;
        return route.getPlaces().get(DESTINATION);
    }

    @Override
    public List<Place> getRouteWaypoints(Route route) {
        final int HAVE_WAYPOINTS = 2;
        if (route.getPlaces().size() < HAVE_WAYPOINTS) {
            return null;
        }
        List<Place> result = new ArrayList<>();
        for (int i = 2; i < route.getPlaces().size(); i++) {
            result.add(route.getPlaces().get(i));
        }
        return result;
    }

    public void addRoute(RouteDto routeDto) {
        Route route = new Route();
        User user = profileService.getCurrentUser();
        route.setUser(user);
        route.setName(routeDto.getName());
        route.setDescription(routeDto.getDescription());
        route.setDistance(Integer.valueOf(routeDto.getDistance()));
        List<Place> places = new ArrayList<>();
        places.add(placeRepository.findOne(Integer.parseInt(routeDto.getOriginId())));
        places.add(placeRepository.findOne(Integer.parseInt(routeDto.getDestinationId())));
        for (String idPlace : routeDto.getWaypointsId()) {
            places.add(placeRepository.findOne(Integer.parseInt(idPlace)));
        }
        route.setPlaces(places);
        routeRepository.save(route);
    }

    public List<Route> getRouteLike(String input) {
        return routeRepository.findByNameContaining(input);
    }

    public List<Route> getCurrentUserRoutes() {
        return new ArrayList<>(profileService.getCurrentUser().getRoutes());
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

    public List<RouteDto> getRoutesDtoList(Collection<Route> routes) {
        List<RouteDto> result = new ArrayList<>();
        for (Route route : routes) {
            result.add(new RouteDto(route));
        }
        return result;
    }

    public boolean removeRoute(int id){
        if (userHaveRoute(id)) {
            routeRepository.delete(id);
            return true;
        }
        return false;
    }

    private boolean userHaveRoute(int id) {
        User user = profileService.getCurrentUser();
        for (Route route : user.getRoutes()) {
            if (route.getUser() == user) {
                return true;
            }
        }
        return false;
    }
}
