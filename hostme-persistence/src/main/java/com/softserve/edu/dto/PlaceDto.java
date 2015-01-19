package com.softserve.edu.dto;

import com.softserve.edu.model.routes.Place;
import java.io.Serializable;

public class PlaceDto implements Serializable{
    private String name;
    private String description;
    private String website;

    public PlaceDto(Place place) {
        name = place.getName();
        description = place.getDescription();
        if (place.getWebsite() != null) {
            website = place.getWebsite();
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
