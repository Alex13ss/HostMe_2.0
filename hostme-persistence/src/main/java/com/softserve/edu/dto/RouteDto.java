package com.softserve.edu.dto;

import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteDto {
    private String name;
    private String description;
    private String originId;
    private String destinationId;
    private final int MAX_WAYPOINTS = 8;
    private String[] waypointsId = new String[MAX_WAYPOINTS];

    public RouteDto() {
    }

    public RouteDto(Route route) {
        name = route.getName();
        description = route.getDescription();
        Iterator<Place> places = route.getPlaces().iterator();
        originId = String.valueOf(places.next().getId());
        destinationId = String.valueOf(places.next().getId());
        int count = 0;
        while (places.hasNext()) {
            waypointsId[count] = String.valueOf(places.next().getId());
            count++;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String[] getWaypointsId() {
        return waypointsId;
    }

    public void setWaypointsId(String[] waypointsId) {
        this.waypointsId = waypointsId;
    }
}