package com.softserve.edu.controller.routes;

import com.softserve.edu.model.routes.Route;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
    public String addRoute(@ModelAttribute("route") Route route,
                           @RequestParam(value = "from") String begin,
                           @RequestParam(value = "to") String end,
                           @RequestParam(value = "waypoint") String waypoint) {

        routesService.addRoute(route);
        return "redirect:/profile";
    }

}
