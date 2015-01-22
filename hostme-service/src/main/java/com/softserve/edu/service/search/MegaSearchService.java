package com.softserve.edu.service.search;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.dto.HostingDto;
import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;

import java.util.List;

public interface MegaSearchService {

    public List<PlaceDto> searchPlaces(String input);

    public List<RouteDto> searchRoutes(String input);

    public List<EventDto> searchEvents(String input);

    //TODO Sightseeing search
    //TODO Groups search

    public List<HostingDto> searchHosting(String input);
}
