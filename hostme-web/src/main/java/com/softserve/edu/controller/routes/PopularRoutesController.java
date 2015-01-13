package com.softserve.edu.controller.routes;

import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PopularRoutesController {

    @Autowired
    RoutesService routesService;

    @RequestMapping(value = "/popularRoutes", method = RequestMethod.GET)
    public static String showRoutes() {
        return "popularRoutes";
    }
}
