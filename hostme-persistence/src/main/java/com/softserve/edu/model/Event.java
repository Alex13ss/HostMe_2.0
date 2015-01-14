package com.softserve.edu.model;


import com.softserve.edu.model.routes.Place;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * 
 * @author Ronan Class for events, which contain all fields related to
 *         information about event
 *
 */
@Entity
@Table(name = "EVENTS", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@PrimaryKeyJoinColumn(name = "id")
public class Event extends Place{

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "priceCategory_id")
	private PriceCategory priceCategory;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "website")
	private String website;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Image> image;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "attendee")	
	private Set<User> attendee;
		

	public Event() {
		super();
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public PriceCategory getPriceCategory() {
		return priceCategory;
	}


	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
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


	public Set<Image> getImage() {
		return image;
	}


	public void setImage(Set<Image> image) {
		this.image = image;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public Set<User> getAttendee() {
		return attendee;
	}


	public void setAttendee(Set<User> attendee) {
		this.attendee = attendee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Event event = (Event) o;

		if (attendee != null ? !attendee.equals(event.attendee) : event.attendee != null) return false;
		if (!description.equals(event.description)) return false;
		if (endDate != null ? !endDate.equals(event.endDate) : event.endDate != null) return false;
		if (!owner.equals(event.owner)) return false;
		if (!priceCategory.equals(event.priceCategory)) return false;
		if (startDate != null ? !startDate.equals(event.startDate) : event.startDate != null) return false;
		if (status != event.status) return false;
		if (website != null ? !website.equals(event.website) : event.website != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + priceCategory.hashCode();
		result = 31 * result + status.hashCode();
		result = 31 * result + (website != null ? website.hashCode() : 0);
		result = 31 * result + owner.hashCode();
		result = 31 * result + (attendee != null ? attendee.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Event [description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priceCategory=" + priceCategory
				+ ", status=" + status + ", comment=" + comment + ", website="
				+ website + ", image=" + image + ", owner=" + owner
				+ ", attendee=" + attendee + "]";
	}
	
	
}