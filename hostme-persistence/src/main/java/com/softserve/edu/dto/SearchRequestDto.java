package com.softserve.edu.dto;

public class SearchRequestDto {
    String request;
    String type;
    Long dateFrom;
    Long dateTo;
    String sightseeingType;
    boolean haveMoreData;
    
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

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }

    public String getSightseeingType() {
        return sightseeingType;
    }

    public void setSightseeingType(String sightseeingType) {
        this.sightseeingType = sightseeingType;
    }

    public boolean isHaveMoreData() {
        return haveMoreData;
    }

    public void setHaveMoreData(boolean haveMoreData) {
        this.haveMoreData = haveMoreData;
    }
}
