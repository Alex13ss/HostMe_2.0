package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;

public interface CityRepsitory extends CrudRepository<City, Integer>  {
	
	public City findByCountry(Country country);

}
