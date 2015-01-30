package com.softserve.edu.controller.routes;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.routes.Route;
import com.softserve.edu.service.ProfileService;
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
public class RouteController {

    @Autowired
    private RoutesService routesService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/route")
    public String showRoute(@RequestParam(value = "routeId") int routeId, Model model) {
        Route route = routesService.findRoute(routeId);
        model.addAttribute("route", route);
        model.addAttribute("origin", routesService.getRouteOrigin(route));
        model.addAttribute("destination", routesService.getRouteDestination(route));
        model.addAttribute("waypoints", routesService.getRouteWaypoints(route));
        model.addAttribute("currentUser", profileService.getCurrentUser());
        return "showRoute";
    }

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

    @RequestMapping(value = "/getUserBookedPlaces")
    public @ResponseBody List<PlaceDto> getBookedPlaces() {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getBookedPlaces()));
        return result;
    }

    @RequestMapping(value = "/getPopularPlaces")
    public @ResponseBody List<PlaceDto> getPopularPlaces() {
        ArrayList<PlaceDto> result = new ArrayList<>();
        result.addAll(placeService.placeToPlaceDto(placeService.getAllPlaces(new PageRequest(0, 20))));
        return result;
    }

    @RequestMapping(value = "/routeEdit")
    public String editRoute(@RequestParam(value = "routeId") int routeId) {
        return "editRoute";
    }

    @RequestMapping(value = "/routeDelete")
    public String deleteRoute(@RequestParam(value = "routeId") int routeId) {
        if (routesService.removeRoute(routeId)) {
            return "redirect:/profile";
        }
        else return "redirect:/error";
    }
}
