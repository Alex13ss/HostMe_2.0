package com.softserve.edu.controller.routes;

import com.softserve.edu.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateRouteController {

    @Autowired
    RoutesService routesService;

    @RequestMapping(value = "/createRoute", method = RequestMethod.GET)
    public static String createRoute() {
        return "createRoute";
    }

}
