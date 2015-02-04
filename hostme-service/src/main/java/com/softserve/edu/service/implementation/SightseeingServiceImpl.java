package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.repositories.ImageRepository;
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
	@Autowired
	private ImageRepository imageRepository;
	private Long favouriteSize;

	public boolean haveSight(int id) {
		return sightseeingRepository.exists(id);
	}

	@Override
	public List<Sightseeing> searchSightseeing(Specifications<Sightseeing> specifications) {
		return sightseeingRepository.findAll(specifications);
	}

	@Override
	@Transactional
	public List<SightseeingDto> getAllSightseeingsPaging(Integer page,
			Integer size, String orderBy, String orderType) {
		List<SightseeingDto> list = new ArrayList<SightseeingDto>();
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}
		for (Sightseeing sightseeing : sightseeingRepository
				.findAll(pageRequsetObj)) {
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

	public List<SightseeingDto> getFavouriteSightseeingsPaging(User liker,
			Integer page, Integer size, String orderBy, String orderType) {
		List<SightseeingDto> list = new ArrayList<SightseeingDto>();
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}
		for (Place place : placeRepository.findByLikers(liker, pageRequsetObj)) {
			list.add(new SightseeingDto(sightseeingRepository.findOne(place
					.getId()), place));
		}
		favouriteSize = (long) list.size();
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
		sightseeing.setImage(imageRepository.findAllByPlace(sightseeing));
		sightseeing.setPriceCategory(priceCategoryRepository.findOne(pcId));
		sightseeing.setCity(cityRepository.findOne(cityId));
		Integer rating = userRepository.findByFavouriveSights(sightseeing).size();
		sightseeing.setRating(rating);
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
		userRepository.save(user);
		Integer rating = userRepository.findByFavouriveSights(sightseeing).size();
		sightseeing.setRating(rating);
		sightseeingRepository.save(sightseeing);
	}

	@Override
	@Transactional
	public boolean favouriteCheck(Sightseeing sightseeing, User liker) {
		Set<User> likers = userRepository.findByFavouriveSights(sightseeing);
		if (likers.contains(liker)) {
			return true;
		}
		return false;
	}

	public void unlikeSightseeing(Integer id, User liker){
		Integer likerId = liker.getUserId();
		Sightseeing sightseeing = sightseeingRepository.findOne(id);
		sightseeingRepository.unlike(id, likerId);
		Integer rating = userRepository.findByFavouriveSights(sightseeing).size();
		sightseeing.setRating(rating);
		sightseeingRepository.save(sightseeing);
	}
	
	public Long getSightseeingsPaging(Long size, String sender, User liker) {
		Long amount;
		if (sender.equals("all-sightseeings")) {
			Long dataBaseSize = sightseeingRepository.count();
			if (dataBaseSize % size == 0) {
				amount = dataBaseSize / size;
			} else {
				amount = dataBaseSize / size + 1;
			}
		} else if (sender.equals("favourite-sightseeings")) {
			Long dataFavouriteSize = (long) getFavouriteSightseeings(liker)
					.size();
			if (dataFavouriteSize % size == 0l) {
				amount = dataFavouriteSize / size;
			} else {
				amount = dataFavouriteSize / size + 1;
			}
		} else {
			amount = 3L;
		}
		return amount;
	}
}
