package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Country;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Override
	@Transactional
	public Country addCountry(Country country) {

		return countryRepository.save(country);
	}

	@Override
	@Transactional
	public void updateCountry(Country country) {

		Country update = countryRepository.findOne(country.getCountryId());

		if (update != null) {
			update.setCountry(country.getCountry());
		}

	}

	@Override
	@Transactional
	public void removeCountry(Integer id) {

		countryRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Country> getAllCountry() {
		List<Country> list = (List<Country>) countryRepository.findAll();
		return list;
	}

	@Override
	@Transactional
	public Country getCountry(Integer id) {
		return countryRepository.findOne(id);
	}

}
