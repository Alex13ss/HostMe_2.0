package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.routes.RouteRepository;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.PriceCategoryService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.RoutesService;
import com.softserve.edu.utils.PlaceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RoutesServiceImp implements RoutesService{

    @Autowired
    RouteRepository routeRep;

    @Autowired
    PlaceRepository placeRep;

    @Autowired
    ProfileService profileSrv;
    
    @Autowired
    PriceCategoryService priceCatSrv;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;
    
    public void addRoute(Route route) {
        route.setUser(profileSrv.getCurrentUser());
        routeRep.save(route);
    }

    public Route findRoute(int id) {
        return routeRep.findWithFetchPlaces(id);
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
    
    @Transactional
    public void addRoute(RouteDto routeDto) {
        Route route = new Route();
        User user = profileSrv.getCurrentUser();
        route.setUser(user);
        route.setName(routeDto.getName());
        route.setDescription(routeDto.getDescription());
        route.setDistance(Long.valueOf(routeDto.getDistance()));
        List<Place> places = new ArrayList<>();
        places.add(placeRep.findOne(Integer.parseInt(routeDto.getOriginId())));
        places.add(placeRep.findOne(Integer.parseInt(routeDto.getDestinationId())));
        for (String idPlace : routeDto.getWaypointsId()) {
            places.add(placeRep.findOne(Integer.parseInt(idPlace)));
        }
        route.setPlaces(places);
        route.setPriceCategory(priceCatSrv.getPriceCategory(PlaceUtils.countAvePrice(places)));
        route.setRating(PlaceUtils.countAveRating(places));
        routeRep.save(route);
    }

    public List<Route> getRouteLike(String input) {
        return routeRep.findByNameContaining(input);
    }

    public List<Route> getCurrentUserRoutes() {
        return new ArrayList<>(profileSrv.getCurrentUser().getRoutes());
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
        List<Route> allRoutes = new ArrayList<>((List<Route>) routeRep.findAll());
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
        if (userHaveRoute()) {
            routeRep.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean likeRoute(int id) {
        final int RATING_INCREMENT = 1;
        User user = profileSrv.getCurrentUser();
        int userId = user.getUserId();
        Route route = findRoute(id);
        user = userRepository.findByUserIdAndLikedRoutes(user.getUserId(), route);
        if (user != null) {
            userService.removeLikedRoute(userId, route);
            route.setRating(route.getRating() - RATING_INCREMENT);
            routeRep.save(route);
            return false;
        } else {
            userService.addLikedRoute(userId, route);
            route.setRating(route.getRating() + RATING_INCREMENT);
            routeRep.save(route);
            return true;
        }
    }

    private boolean userHaveRoute() {
        User user = profileSrv.getCurrentUser();
        for (Route route : user.getRoutes()) {
            if (route.getUser() == user) {
                return true;
            }
        }
        return false;
    }
}
