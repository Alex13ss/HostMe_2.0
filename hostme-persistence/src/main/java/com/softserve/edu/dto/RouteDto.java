package com.softserve.edu.dto;

public class RouteDto {
    private String name;
    private String description;
    private String originId;
    private String destinationId;
    private String[] waypointsId;

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
