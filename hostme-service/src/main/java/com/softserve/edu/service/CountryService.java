package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.model.Country;

public interface CountryService {

	public Country addCountry(Country country);

	public void updateCountry(Country country);

	public void removeCountry(Integer id);
	
	public List<Country> getAllCountry();

	public Country getCountry(Integer id);
}
