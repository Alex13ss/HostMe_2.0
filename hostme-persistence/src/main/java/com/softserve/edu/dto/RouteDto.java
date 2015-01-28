package com.softserve.edu.dto;

import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Route;

import java.util.Iterator;

public class RouteDto {
    private String id;
    private String name;
    private String description;
    private String originId;
    private String destinationId;
    private final int MAX_WAYPOINTS = 8;
    private String[] waypointsId = new String[MAX_WAYPOINTS];

    public RouteDto() {
    }

    public RouteDto(Route route) {
        id = String.valueOf(route.getId());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
//
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