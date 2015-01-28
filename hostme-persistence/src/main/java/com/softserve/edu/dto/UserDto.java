package com.softserve.edu.dto;

import com.softserve.edu.model.User;

public class UserDto {

    private int id;
    private String name;
    private String country;

    public UserDto(User user) {
        id = user.getUserId();
        name = user.getLastName() + " " + user.getFirstName();
        country = user.getCountry();
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
