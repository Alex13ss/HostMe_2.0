package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.service.CityService;


@Service
public class CityServiceImpl implements CityService{

	
	@Autowired
	CityRepository cityRepository;

	@Override
	@Transactional
	public City addCity(City city) {

		return cityRepository.save(city);
	}

	@Override
	@Transactional
	public void updateCity(City city) {
		
		City update = cityRepository.findOne(city.getCityId());
		
		if(update != null) {
			update.setCity(city.getCity());
			update.setCountry(city.getCountry());
			
		}

	}

	@Override
	@Transactional
	public void removeCity(Integer id) {

		cityRepository.delete(id);
	}

	@Override
	@Transactional
	public List<City> getAllCity() {
		List<City> list = (List<City>) cityRepository.findAll();
		return list;
	}

	@Override
	@Transactional
	public City getCity(Integer id) {
		return cityRepository.findOne(id);
	}

	@Override
	public City getCityByCountry(Country country) {
		
		return cityRepository.findByCountry(country);
	}


}
