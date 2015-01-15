package com.softserve.edu.service.routes;

import com.softserve.edu.model.routes.Place;

import java.util.List;

public interface PlaceService {

    public List<Place> getPlacesNearToUser();

    public List<Place> getUserPlaces();
}
