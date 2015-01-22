package com.softserve.edu.service.search.implementation;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.service.routes.RoutesService;
import com.softserve.edu.service.search.MegaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MegaSearchServiceImp implements MegaSearchService {

    @Autowired
    RoutesService routesService;

    @Override
    public List<PlaceDto> searchPlaces(String input) {
        return null;
    }

    @Override
    public List<RouteDto> searchRoutes(String input) {
        return routesService.getRoutesDto(routesService.getRouteLike(input));
    }
}
