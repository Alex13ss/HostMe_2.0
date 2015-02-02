package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

	public City findByCountry(Country country);

	public City findByCity(String city);

	public City findOne(Integer id);

	public List<City> findByCityContainingIgnoreCase(String city);
}
