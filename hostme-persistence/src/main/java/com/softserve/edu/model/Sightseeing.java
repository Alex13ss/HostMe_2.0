package com.softserve.edu.model;

import com.softserve.edu.model.routes.Place;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Sightseeing", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@PrimaryKeyJoinColumn(name = "id")
public class Sightseeing extends Place{

	@Column(name = "sightseeing_name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "sightseeing_type", nullable = false, insertable = false, updatable = false)
	private SightseeingType sightseeingType;

	@Column(name = "rating", nullable = false)
	private Integer rating;

	@Column(name = "website", nullable = false)
	private String website;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "priceCategory_id", nullable = false)
	private PriceCategory priceCategory;

	@OneToMany(mappedBy = "sightseeing", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Image> image;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "comment")
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SightseeingType getSightseeingType() {
		return sightseeingType;
	}

	public void setSightseeingType(SightseeingType sightseeingType) {
		this.sightseeingType = sightseeingType;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public Set<Image> getImage() {
		return image;
	}

	public void setImage(Set<Image> image) {
		this.image = image;
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

}