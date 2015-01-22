package com.softserve.edu.dto;

import com.softserve.edu.model.User;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String country;

    public UserDto(User user) {
        id = user.getUserId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        country = user.getCountry();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
