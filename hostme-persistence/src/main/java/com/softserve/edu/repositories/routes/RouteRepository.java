package com.softserve.edu.repositories.routes;

import com.softserve.edu.model.routes.Route;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RouteRepository extends PagingAndSortingRepository<Route, Integer> {
    public List<Route> findByNameContaining(String name);
}
