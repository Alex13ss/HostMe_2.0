package com.softserve.edu.controller.routes;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.service.routes.PlaceService;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createRoute(Model model) {
        RouteDto route = new RouteDto();
        model.addAttribute("route", route);
        model.addAttribute("allPlaces", placeService.getAllPlaces(new PageRequest(0, 10)));
        model.addAttribute("userPlaces", placeService.getUserPlaces());
        return "createRoute";
    }

    @RequestMapping(value = "/createRoute-getPlaces", method = RequestMethod.GET,
                        produces = "application/json")
    public @ResponseBody List<Place> createRoute() {
        List<Place> places = new ArrayList<>();
        places.addAll(placeService.getUserPlaces());
        places.addAll(placeService.getAllPlaces(new PageRequest(0, 20)));
        return places;
    }

    @RequestMapping(value = "/getUserPlaces", method = RequestMethod.POST)
    public @ResponseBody List<PlaceDto> getUserPlaces(@RequestBody List<Place> places) {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getUserPlaces()));
        return result;
    }

    @RequestMapping(value = "/getPopularPlaces")
    public @ResponseBody List<PlaceDto> getPopularPlaces(@RequestBody List<Place> places) {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getAllPlaces(new PageRequest(0, 20))));
        return result;
    }

    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
    public String addRoute(@ModelAttribute("route") RouteDto routeArrt) {
        Route route = new Route();
        route.setName(routeArrt.getName());
        route.setDescription(routeArrt.getDescription());
        routesService.addRoute(route);
        return "redirect:/profile";
    }
}
