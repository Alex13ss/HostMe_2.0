package com.softserve.edu.dto;

public class RouteDto {
    private String name;
    private String description;
    private String originPoint;
    private String destinationPoint;
    private String[] waypoints;

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

    public String getOriginPoint() {
        return originPoint;
    }

    public void setOriginPoint(String originPoint) {
        this.originPoint = originPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String[] waypoints) {
        this.waypoints = waypoints;
    }
}
