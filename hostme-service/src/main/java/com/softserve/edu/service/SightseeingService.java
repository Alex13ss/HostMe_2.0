package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

public interface SightseeingService {

	public boolean haveSight(int id);

	List<SightseeingDto> getAllSightseeings();

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
	
}
