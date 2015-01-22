package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.Sightseeing;

import java.util.List;

public interface SightseeingRepository extends CrudRepository<Sightseeing, Integer> {
    public List<Sightseeing> findByNameContaining(String name);
}
