package com.softserve.edu.dto;

public class RoutePagingDto {

    private Integer pageIndex;
    private Integer placeSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(Integer placeSize) {
        this.placeSize = placeSize;
    }
}
