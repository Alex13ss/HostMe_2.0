package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;
import org.springframework.data.jpa.domain.Specifications;

public interface SightseeingService {

	public boolean haveSight(int id);

	public List<Sightseeing> searchSightseeing(Specifications<Sightseeing> specifications);

	List<SightseeingDto> getAllSightseeingsPaging(Integer page, Integer size,
			String orderBy, String orderType);

	List<SightseeingDto> getSightseeingsDtoList(List<Sightseeing> sightseeings);

	List<Sightseeing> getSightseeingsLike(String search);

	Sightseeing findOne(Integer id);

	void deleteSightseeing(Sightseeing sightseeing);

	void saveSightseeing(Sightseeing sightseeing, String priceCategory,
			String city);

	void updateSightseeing(Sightseeing sightseeing, String priceCategory,
			String city);

	void saveLikerforSightseing(User user, Sightseeing sightseeing);

	List<SightseeingDto> getFavouriteSightseeings(User liker);

	List<SightseeingDto> getFavouriteSightseeingsPaging(User liker,
			Integer page, Integer size, String orderBy, String orderType);

	Long getSightseeingsPaging(Long size, String sender, User liker);

	boolean favouriteCheck(Sightseeing sightseeing, User liker);

	void unlikeSightseeing(Integer id, User liker);
	
	Integer getCurrentRating(Integer id);
}
