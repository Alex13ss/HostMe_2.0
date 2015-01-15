package com.softserve.edu.model;

import com.softserve.edu.model.routes.Place;

import java.util.Set;

import javax.persistence.*;
import com.google.common.base.Objects;

@Entity
@Table(name = "Sightseeing", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@PrimaryKeyJoinColumn(name = "id")
public class Sightseeing extends Place {

	@Column(name = "sightseeing_name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "sightseeing_type", updatable = false)
	private SightseeingType sightseeingType;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "website")
	private String website;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "priceCategory_id")
	private PriceCategory priceCategory;

	@OneToMany(mappedBy = "sightseeing", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Image> image;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
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

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), name, sightseeingType,
				rating, website, description, priceCategory, image, status,
				comment);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Sightseeing) {
			if (!super.equals(object))
				return false;
			Sightseeing that = (Sightseeing) object;
			return Objects.equal(this.name, that.name)
					&& Objects
							.equal(this.sightseeingType, that.sightseeingType)
					&& Objects.equal(this.rating, that.rating)
					&& Objects.equal(this.website, that.website)
					&& Objects.equal(this.description, that.description)
					&& Objects.equal(this.priceCategory, that.priceCategory)
					&& Objects.equal(this.image, that.image)
					&& Objects.equal(this.status, that.status)
					&& Objects.equal(this.comment, that.comment);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Sightseeing [name=" + name + ", sightseeingType="
				+ sightseeingType + ", rating=" + rating + ", website="
				+ website + ", description=" + description + ", priceCategory="
				+ priceCategory + ", image=" + image + ", status=" + status
				+ ", comment=" + comment + "]";
	}

}