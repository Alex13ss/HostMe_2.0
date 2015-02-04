package com.softserve.edu.dto;

public class SearchRequestDto {
    String request;
    String type;
    Long date;
    String sightseeingType;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate() {
        return date;
    }

    public String getSightseeingType() {
        return sightseeingType;
    }

    public void setSightseeingType(String sightseeingType) {
        this.sightseeingType = sightseeingType;
    }
}
