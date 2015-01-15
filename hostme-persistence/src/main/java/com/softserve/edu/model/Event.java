package com.softserve.edu.model;


import com.softserve.edu.model.routes.Place;

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

}