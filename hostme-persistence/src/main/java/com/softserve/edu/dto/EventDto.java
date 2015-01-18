package com.softserve.edu.dto;

import java.util.Date;
import java.util.Set;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

public class EventDto {

	private Integer id;
	private String title;
	private Date startDate;
	private Date endDate;
	private City city;
	private String address;
	private String comment;
	private String website;
	private Status status;
	private PriceCategory priceCategory;
	private User owner;
	private Set<Image> image;
	private Set<User> attendee;
	public EventDto(Event event, Place place) {
		
		this.id = place.getId();
		this.title = place.getDescription();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.city = place.getCity();
		this.address = place.getAddress();
		this.comment = place.getComment();
		this.website = place.getWebsite();
		this.status = place.getStatus();
		this.priceCategory = place.getPriceCategory();
		this.owner = place.getOwner();
		this.image = place.getImage();
		this.attendee = place.getAttendee();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PriceCategory getPriceCategory() {
		return priceCategory;
	}
	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<Image> getImage() {
		return image;
	}
	public void setImage(Set<Image> image) {
		this.image = image;
	}
	public Set<User> getAttendee() {
		return attendee;
	}
	public void setAttendee(Set<User> attendee) {
		this.attendee = attendee;
	}
	
	
}
