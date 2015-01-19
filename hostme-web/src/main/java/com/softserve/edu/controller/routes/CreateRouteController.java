package com.softserve.edu.controller.routes;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.service.routes.PlaceService;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateRouteController {

    @Autowired
    private RoutesService routesService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/createRoute", method = RequestMethod.GET)
    public String createRoute() {
        return "createRoute";
    }

    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
    public @ResponseBody boolean saveRoute(@RequestBody RouteDto route) {
        routesService.addRoute(route);
        return true;
    }

    @RequestMapping(value = "/getUserPlaces")
    public @ResponseBody List<PlaceDto> getUserPlaces() {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getUserPlaces()));
        return result;
    }

    @RequestMapping(value = "/getPopularPlaces")
    public @ResponseBody List<PlaceDto> getPopularPlaces() {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getAllPlaces(new PageRequest(0, 20))));
        return result;
    }
}
