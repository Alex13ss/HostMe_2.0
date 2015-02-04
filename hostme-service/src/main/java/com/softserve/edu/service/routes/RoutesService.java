package com.softserve.edu.service.routes;

import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;

import java.util.Collection;
import java.util.List;

public interface RoutesService {

    public void addRoute(Route route);

    public void addRoute(RouteDto routeDto);

    public Route findRoute(int id);

    public Place getRouteOrigin(Route route);

    public Place getRouteDestination(Route route);

    public List<Place> getRouteWaypoints(Route route);

    public List<Route> getRouteLike(String name);

    public List<RouteDto> getRoutesDtoList(Collection<Route> routes);

    public List<Route> getCurrentUserRoutes();

    public List<Route> getRoutesNearToUsers();

    public boolean removeRoute(int id);
}
