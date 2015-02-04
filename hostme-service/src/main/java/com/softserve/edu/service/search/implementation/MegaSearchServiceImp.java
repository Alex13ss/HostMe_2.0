package com.softserve.edu.service.search.implementation;

import com.softserve.edu.dto.*;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.SightseeingService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.RoutesService;
import com.softserve.edu.service.search.MegaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import static com.softserve.edu.repositories.specifications.EventSpecification.*;
import static com.softserve.edu.repositories.specifications.SightseeingSpecification.*;

import java.util.Date;
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

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<PlaceDto> searchPlaces(String input) {
        return null;
    }

    @Override
    public List<RouteDto> searchRoutes(String input) {
        return routesService.getRoutesDtoList(routesService.getRouteLike(input));
    }

    @Override
    public List<SightseeingDto> searchSights(SearchRequestDto searchRequestDto) {
        City city = cityRepository.findByCity(searchRequestDto.getRequest());
        Specifications<Sightseeing> specifications = Specifications.where(sightHaveCity(city));
        List<Sightseeing> sightseeings = sightseeingService.searchSightseeing(specifications);
        return sightseeingService.getSightseeingsDtoList(sightseeings);
    }

    @Override
    public List<EventDto> searchEvents(String input) {
        return eventService.getEventsDtoList(eventService.getEventsLike(input));
    }

    @Override
    public List<EventDto> searchEvents(SearchRequestDto searchRequestDto) {
        City city = cityRepository.findByCity(searchRequestDto.getRequest());
        Specifications<Event> specifications = Specifications.where(eventHaveCity(city));
        if (searchRequestDto.getDate() != null) {
            specifications.and(eventBeforeDate(new Date(searchRequestDto.getDate())));
        }
        return eventService.getEventsDtoList(eventService.searchEvent(specifications));
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
