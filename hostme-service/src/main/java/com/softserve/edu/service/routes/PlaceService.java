package com.softserve.edu.service.routes;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.model.routes.Place;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PlaceService {

    public Place getPlace(int placeId);

    public List<Place> getAllPlaces(Pageable pageable);

    public Set<Place> getBookedPlaces();

    public List<Place> getPlacesNearToUser();

    public List<Place> getUserPlaces();

    public List<Place> getAllNotUserPlaces();

    public List<PlaceDto> placeToPlaceDto(Collection<Place> places);
}
