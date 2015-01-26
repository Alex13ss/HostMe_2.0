package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.Status;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.repositories.PriceCategoryRepository;
import com.softserve.edu.repositories.SightseeingRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.SightseeingService;

@Service
public class SightseeingServiceImpl implements SightseeingService {

	@Autowired
	private SightseeingRepository sightseeingRepository;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private PriceCategoryRepository priceCategoryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	public boolean haveSight(int id) {
		return sightseeingRepository.exists(id);
	}

	@Override
	@Transactional
	public List<SightseeingDto> getAllSightseeings() {
		List<SightseeingDto> list = new ArrayList<SightseeingDto>();
		for (Sightseeing sightseeing : sightseeingRepository.findAll()) {
			list.add(new SightseeingDto(sightseeing, placeRepository
					.findOne(sightseeing.getId())));
		}
		return list;
	}

	@Override
	public List<SightseeingDto> getSightseeingsDtoList(
			List<Sightseeing> sightseeings) {
		List<SightseeingDto> result = new ArrayList<>();
		for (Sightseeing sightseeing : sightseeings) {
			result.add(new SightseeingDto(sightseeing));
		}
		return result;
	}

	@Override
	public List<Sightseeing> getSightseeingsLike(String search) {
		return sightseeingRepository.findByNameContaining(search);
	}

	@Override
	@Transactional
	public Sightseeing findOne(Integer id) {
		return sightseeingRepository.findOne(id);
	}

	@Override
	public void deleteSightseeing(Sightseeing sightseeing) {
		sightseeingRepository.delete(sightseeing);
	}

	@Override
	@Transactional
	public void saveSightseeing(Sightseeing sightseeing, String priceCategory, String city) {
		sightseeing.setOwner(profileService.getCurrentUser());
		sightseeing.setStatus(Status.PENDING);
		Integer pcId = priceCategoryRepository.findByPriceCategory(priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		sightseeing.setPriceCategory(priceCategoryRepository.findOne(pcId));
		sightseeing.setCity(cityRepository.findOne(cityId));
		sightseeingRepository.save(sightseeing);
	}

	@Override
	@Transactional
	public void updateSightseeing(Sightseeing sightseeing, String priceCategory, String city) {
		sightseeing.setOwner(profileService.getCurrentUser());
		Integer pcId = priceCategoryRepository.findByPriceCategory(priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		sightseeing.setPriceCategory(priceCategoryRepository.findOne(pcId));
		sightseeing.setCity(cityRepository.findOne(cityId));
		sightseeingRepository.save(sightseeing);
	}
}
