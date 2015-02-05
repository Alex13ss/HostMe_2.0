package com.softserve.edu.dto;

import java.util.Set;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.SightseeingType;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.routes.Place;

public class SightseeingDto {
	private Integer id;
	private String name;
	private City city;
	private String address;
	private String description;
	private String comment;
	private String website;
	private Set<Image> images;
	private PriceCategory priceCategory;
	private Integer rating;
	private SightseeingType sightseeingType;
	private Status status;

	public SightseeingDto(Sightseeing sightseeing) {
		id = sightseeing.getId();
		name = sightseeing.getName();
		description = sightseeing.getDescription();
		rating = sightseeing.getRating();
		priceCategory = sightseeing.getPriceCategory();
		sightseeingType = sightseeing.getSightseeingType();
		address = sightseeing.getAddress();
	}

	public SightseeingDto(Sightseeing sightseeing, Place place) {
		this.id = place.getId();
		this.name = place.getName();
		this.city = place.getCity();
		this.address = place.getAddress();
		this.description = place.getDescription();
		this.comment = place.getComment();
		this.website = place.getWebsite();
		this.images = place.getImage();
		this.priceCategory = place.getPriceCategory();
		this.rating = place.getRating();
		this.sightseeingType = sightseeing.getSightseeingType();
		this.status=sightseeing.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public SightseeingType getSightseeingType() {
		return sightseeingType;
	}

	public void setSightseeingType(SightseeingType sightseeingType) {
		this.sightseeingType = sightseeingType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
