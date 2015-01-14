package com.softserve.edu.service;

import java.util.Set;

import com.softserve.edu.model.Sightseeing;

public interface SightseeingService {
	Set<Sightseeing> findAll();

	Sightseeing findOne(Integer id);

	void delete(Sightseeing sightseeing);

	void saveSightseeing(Sightseeing sightseeing);
}
