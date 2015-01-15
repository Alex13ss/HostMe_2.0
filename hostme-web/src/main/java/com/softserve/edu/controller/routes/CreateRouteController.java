package com.softserve.edu.controller.routes;

import com.softserve.edu.model.routes.Route;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateRouteController {

    @Autowired
    RoutesService routesService;

    @RequestMapping(value = "/createRoute", method = RequestMethod.GET)
    public String createRoute(Model model) {
        Route route = new Route();
        model.addAttribute("route", route);
        return "createRoute";
    }

    @RequestMapping(value = "/createRoute-getRoutes", method = RequestMethod.GET,
                        produces = "application/json")
    public @ResponseBody List<Route> createRoute() {
        List<Route> routes = new ArrayList<>();
        routes.addAll(routesService.getCurrentUserRoutes());
        routes.addAll(routesService.getRoutesNearToUsers());
        return routes;
    }

    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
    public String addRoute(@ModelAttribute("route") Route route) {
        routesService.addRoute(route);
        return "redirect:/profile";
    }

}
