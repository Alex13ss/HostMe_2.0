package com.softserve.edu.service.search;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;

import java.util.List;

public interface MegaSearchService {

    public List<PlaceDto> searchPlaces(String input);

    public List<RouteDto> searchRoutes(String input);
}
