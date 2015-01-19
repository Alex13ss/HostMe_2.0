package com.softserve.edu.dto;

import com.softserve.edu.model.routes.Place;
import java.io.Serializable;

public class PlaceDto implements Serializable{
    private int id;
    private String name;
    private String description;
    private String website;
    private String address;

    public PlaceDto(Place place) {
        id = place.getId();
        name = place.getName();
        description = place.getDescription();
        if (place.getWebsite() != null) {
            website = place.getWebsite();
        }
        address = place.getAddress();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
