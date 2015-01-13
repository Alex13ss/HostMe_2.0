package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.routes.RouteRepo;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutesServiceImp implements RoutesService{

    @Autowired
    RouteRepo routeRepo;

    public void addRoute(Route route) {
        routeRepo.save(route);
    }

}
