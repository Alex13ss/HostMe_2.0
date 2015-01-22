package com.softserve.edu.service.search.implementation;

import com.softserve.edu.dto.*;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.SightseeingService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.RoutesService;
import com.softserve.edu.service.search.MegaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MegaSearchServiceImp implements MegaSearchService {

    @Autowired
    RoutesService routesService;

    @Autowired
    HostingService hostingService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    SightseeingService sightseeingService;

    @Override
    public List<PlaceDto> searchPlaces(String input) {
        return null;
    }

    @Override
    public List<RouteDto> searchRoutes(String input) {
        return routesService.getRoutesDtoList(routesService.getRouteLike(input));
    }

//    @Override


    @Override
    public List<EventDto> searchEvents(String input) {
        return eventService.getEventsDtoList(eventService.getEventsLike(input));
    }

    @Override
    public List<HostingDto> searchHosting(String input) {
        return hostingService.getHostingDtoList(hostingService.getHostingLike(input));
    }

    @Override
    public List<UserDto> searchUsers(String input) {
        return userService.getUserDtoList(userService.getUsersLike(input));
    }
}
