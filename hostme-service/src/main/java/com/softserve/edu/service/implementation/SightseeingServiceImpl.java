package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.repositories.PriceCategoryRepository;
import com.softserve.edu.repositories.SightseeingRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.user.UserRepository;
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
	@Autowired
	private UserRepository userRepository;

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
	@Transactional
	public List<SightseeingDto> getFavouriteSightseeings(User liker) {
		List<SightseeingDto> list = new ArrayList<SightseeingDto>();
		for (Place place : placeRepository.findByLikers(liker)) {
			list.add(new SightseeingDto(sightseeingRepository.findOne(place
					.getId()), place));
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
		sightseeingRepository.deleteLikefromSightseeing(sightseeing.getId());
		sightseeingRepository.delete(sightseeing);
	}

	@Override
	@Transactional
	public void saveSightseeing(Sightseeing sightseeing, String priceCategory,
			String city) {
		sightseeing.setOwner(profileService.getCurrentUser());
		sightseeing.setStatus(Status.PENDING);
		Integer pcId = priceCategoryRepository.findByPriceCategory(
				priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		sightseeing.setPriceCategory(priceCategoryRepository.findOne(pcId));
		sightseeing.setCity(cityRepository.findOne(cityId));
		sightseeing.setRating(0);
		sightseeingRepository.save(sightseeing);
	}

	@Override
	@Transactional
	public void updateSightseeing(Sightseeing sightseeing,
			String priceCategory, String city) {
		sightseeing.setOwner(profileService.getCurrentUser());
		Integer pcId = priceCategoryRepository.findByPriceCategory(
				priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		sightseeing.setPriceCategory(priceCategoryRepository.findOne(pcId));
		sightseeing.setCity(cityRepository.findOne(cityId));
		sightseeingRepository.save(sightseeing);
	}

	@Override
	public void saveLikerforSightseing(User user, Sightseeing sightseeing) {
		Set<User> likers = userRepository.findByFavouriveSights(sightseeing);
		likers.add(user);
		Set<Sightseeing> favouriveSights = sightseeingRepository
				.findByLikers(user);
		sightseeing.setLikers(likers);
		favouriveSights.add(sightseeing);
		user.setFavouriveSights(favouriveSights);
		sightseeing.setRating(sightseeing.getLikers().size());
		sightseeingRepository.save(sightseeing);
		userRepository.save(user);
	}

}
