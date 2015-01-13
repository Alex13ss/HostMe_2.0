package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.model.routes.Route;
import com.softserve.edu.repositories.routes.RouteRepository;
import com.softserve.edu.service.LoginService;
import com.softserve.edu.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoutesServiceImp implements RoutesService{

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    LoginService loginService;

    public void addRoute(Route route) {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        route.setUser(loginService.getUserByLogin(login));
        routeRepository.save(route);
    }

}
