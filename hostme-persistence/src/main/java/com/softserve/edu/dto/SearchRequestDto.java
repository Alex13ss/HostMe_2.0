package com.softserve.edu.dto;

public class SearchRequestDto {
    String request;
    String type;
    boolean haveMoreData;
    Long dateFrom;
    Long dateTo;
    String sightseeingType;
    int guestsNumb;
    boolean familyAllow;
    boolean childrenAllow;
    boolean smokingAllow;
    boolean petsAllow;
    
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

    public int getGuestsNumb() {
        return guestsNumb;
    }

    public void setGuestsNumb(int guestsNumb) {
        this.guestsNumb = guestsNumb;
    }

    public boolean isFamilyAllow() {
        return familyAllow;
    }

    public void setFamilyAllow(boolean familyAllow) {
        this.familyAllow = familyAllow;
    }

    public boolean isChildrenAllow() {
        return childrenAllow;
    }

    public void setChildrenAllow(boolean childrenAllow) {
        this.childrenAllow = childrenAllow;
    }

    public boolean isSmokingAllow() {
        return smokingAllow;
    }

    public void setSmokingAllow(boolean smokingAllow) {
        this.smokingAllow = smokingAllow;
    }

    public boolean isPetsAllow() {
        return petsAllow;
    }

    public void setPetsAllow(boolean petsAllow) {
        this.petsAllow = petsAllow;
    }
}
