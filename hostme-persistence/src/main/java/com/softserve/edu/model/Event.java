package com.softserve.edu.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * 
 * @author Ronan Class for events, which contain all fields related to
 *         information about event
 *
 */
@Entity
@Table(name = "EVENTS", uniqueConstraints = { @UniqueConstraint(columnNames = "event_id") })
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "event_id", unique = true, nullable = false)
	private Integer eventId;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
				
	@Column(name = "address")
	private String address;
	
			
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "priceCategory_id", nullable = false)
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


	public Integer getEventId() {
		return eventId;
	}


	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((attendee == null) ? 0 : attendee.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ ((priceCategory == null) ? 0 : priceCategory.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (attendee == null) {
			if (other.attendee != null)
				return false;
		} else if (!attendee.equals(other.attendee))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (priceCategory == null) {
			if (other.priceCategory != null)
				return false;
		} else if (!priceCategory.equals(other.priceCategory))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
	
	
}