package com.softserve.edu.repositories.routes;

import com.softserve.edu.model.City;
import com.softserve.edu.model.routes.Route;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends PagingAndSortingRepository<Route, Integer> {
    
    public List<Route> findByNameContaining(String name);
    
    @Query("SELECT r FROM Route r INNER JOIN FETCH r.places WHERE r.id = :id")
    public Route findWithFetchPlaces(@Param("id") int id);
    
    @Query("SELECT DISTINCT r FROM Route r INNER JOIN r.places p LEFT JOIN FETCH r.places WHERE p.city = :city")
    public List<Route> findByPlaces_City(@Param("city")City city);
}
