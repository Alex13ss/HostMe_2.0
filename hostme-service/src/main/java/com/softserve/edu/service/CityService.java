package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;

public interface CityService {
	
	public City addCity(City city);

	public void updateCity(City city);

	public void removeCity(Integer id);

	public List<City> searchCitiesByName(String name);

	public List<City> getAllCity();

	public City getCity(Integer id);
	
	public City getCityByCountry(Country country);

}
