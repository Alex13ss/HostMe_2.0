package com.softserve.edu.dto;

import com.softserve.edu.model.User;

public class ModeratorDto {
    
    private String userId;
    
    private String name;
    
    public ModeratorDto() {
	
    }

    public ModeratorDto(User user) {
	this.userId = user.getUserId().toString();
	this.name = user.getFirstName() + " " + user.getLastName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}
