package com.softserve.edu.service.search;

import com.softserve.edu.dto.*;

import java.util.List;

public interface MegaSearchService {

    public List<PlaceDto> searchPlaces(String input);

    public List<RouteDto> searchRoutes(String input);

    public List<EventDto> searchEvents(String input);

    public List<UserDto> searchUsers(String input);

    public List<SightseeingDto> searchSights(String input);
    //TODO Groups search

    public List<HostingDto> searchHosting(String input);
}
