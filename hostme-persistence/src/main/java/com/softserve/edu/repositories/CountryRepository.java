package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {
	public Country findByCountry(String country);
}
