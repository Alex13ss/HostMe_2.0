package com.softserve.edu.service.search.implementation;

import com.softserve.edu.dto.*;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.SightseeingType;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.routes.RouteRepository;
import com.softserve.edu.service.*;
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

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    GroupService groupService;

    @Override
    public List<RouteDto> searchRoutes(SearchRequestDto searchRequestDto) {
        City city = cityRepository.findByCity(searchRequestDto.getRequest());
        List<Route> result = routeRepository.findByPlaces_City(city);
        return routesService.getRoutesDtoList(result);
    }

    @Override
    public List<SightseeingDto> searchSights(SearchRequestDto searchRequestDto) {
        City city = cityRepository.findByCity(searchRequestDto.getRequest());
        Specifications<Sightseeing> specifications = Specifications.where(sightHaveCity(city));
        if (!searchRequestDto.getSightseeingType().equals("")) {
            specifications = specifications.and(sightHaveType(
                    SightseeingType.valueOf(searchRequestDto.getSightseeingType())));
        }
        return sightseeingService.getSightseeingsDtoList(sightseeingService.searchSightseeing(specifications));
    }

    @Override
    public List<GroupDto> searchGroups(SearchRequestDto searchRequestDto) {
        return null;
    }

    @Override
    public List<EventDto> searchEvents(SearchRequestDto searchRequestDto) {
        City city = cityRepository.findByCity(searchRequestDto.getRequest());
        Specifications<Event> specifications = Specifications.where(eventHaveCity(city));
        if (searchRequestDto.getDateFrom() != null) {
            specifications = specifications.and(eventFromDate(new Date(searchRequestDto.getDateFrom())));
        }
        if (searchRequestDto.getDateTo() != null) {
            specifications = specifications.and(eventToDate(new Date(searchRequestDto.getDateTo())));
        }
        return eventService.getEventsDtoList(eventService.searchEvent(specifications));
    }

    @Override
    public List<HostingDto> searchHosting(String input) {
        return hostingService.getHostingDtoList(hostingService.getHostingLike(input));
    }

    @Override
    public List<UserDto> searchUsers(SearchRequestDto searchRequestDto) {
        return userService.getUserDtoList(userService.getUsersLike(searchRequestDto.getRequest()));
    }
}
