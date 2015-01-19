package com.softserve.edu.service.routes;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.model.routes.Place;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceService {

    public List<Place> getAllPlaces(Pageable pageable);

    public List<Place> getPlacesNearToUser();

    public List<Place> getUserPlaces();

    public List<Place> getAllNotUserPlaces();

    public List<PlaceDto> placeToPlaceDto(List<Place> places);
}
