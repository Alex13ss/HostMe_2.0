package com.softserve.edu.service.routes;

import com.softserve.edu.dto.RouteDto;
import com.softserve.edu.model.routes.Route;

import java.util.List;

public interface RoutesService {

    public void addRoute(Route route);

    public List<Route> getRouteLike(String name);

    public List<RouteDto> getRoutesDto(List<Route> routes);

    public void addRoute(RouteDto routeDto);

    public List<Route> getCurrentUserRoutes();

    public List<Route> getRoutesNearToUsers();

    public boolean removeRoute(int id);
}
