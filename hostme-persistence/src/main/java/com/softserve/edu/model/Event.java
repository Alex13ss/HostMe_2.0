package com.softserve.edu.model;


import com.softserve.edu.model.routes.Place;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
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
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), description, city, address,
				startDate, endDate, priceCategory, status, comment, website,
				image, owner, attendee);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Event) {
			if (!super.equals(object))
				return false;
			Event that = (Event) object;
			return Objects.equal(this.description, that.description)
					&& Objects.equal(this.city, that.city)
					&& Objects.equal(this.address, that.address)
					&& Objects.equal(this.startDate, that.startDate)
					&& Objects.equal(this.endDate, that.endDate)
					&& Objects.equal(this.priceCategory, that.priceCategory)
					&& Objects.equal(this.status, that.status)
					&& Objects.equal(this.comment, that.comment)
					&& Objects.equal(this.website, that.website)
					&& Objects.equal(this.image, that.image)
					&& Objects.equal(this.owner, that.owner)
					&& Objects.equal(this.attendee, that.attendee);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Event [description=" + description + ", city=" + city
				+ ", address=" + address + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priceCategory=" + priceCategory
				+ ", status=" + status + ", comment=" + comment + ", website="
				+ website + ", image=" + image + ", owner=" + owner
				+ ", attendee=" + attendee + "]";
	}

	


}