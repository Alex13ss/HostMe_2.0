package com.softserve.edu.service.routes;

import com.softserve.edu.model.routes.Route;

import java.util.List;

public interface RoutesService {

    public void addRoute(Route route);

    public List<Route> getCurrentUserRoutes();

    public List<Route> getRoutesNearToUsers();
}
