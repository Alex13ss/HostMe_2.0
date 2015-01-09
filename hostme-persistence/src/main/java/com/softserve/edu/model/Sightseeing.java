package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Sightseeing", uniqueConstraints = { @UniqueConstraint(columnNames = "sightseeing_id") })
public class Sightseeing {

	@Id
	@GeneratedValue
	@Column(name = "sightseeing_id")
	private Integer sightseeingId;

//	@ManyToOne
//	@JoinColumn(name = "city_id", nullable = false)
//	private City city;
//
//	@Column(name = "address", nullable = false)
//	private String address;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "sightseeingType", nullable = false, insertable = false, updatable = false)
//	private SightseeingType sightseeingType;
//
//	@Column(name = "rating", nullable = false)
//	private Integer rating;
//
//	@Column(name = "website", nullable = false)
//	private String website;
//
//	@Column(name = "description", nullable = false)
//	private String description;
//
//	@ManyToOne
//	@JoinColumn(name = "priceCategory_id", nullable = false)
//	private PriceCategory priceCategory;
//
//	@OneToMany(mappedBy = "sightseeing", fetch = FetchType.EAGER, orphanRemoval = true)
//	private Set<Image> image;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "sightseeingType", nullable = false)
//	private Status status;
//
//	@Column(name = "comment")
//	private String comment;
//
//	public Integer getSightseeingId() {
//		return sightseeingId;
//	}
//
//	public void setSightseeingId(Integer sightseeingId) {
//		this.sightseeingId = sightseeingId;
//	}
//
//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public SightseeingType getSightseeingType() {
//		return sightseeingType;
//	}
//
//	public void setSightseeingType(SightseeingType sightseeingType) {
//		this.sightseeingType = sightseeingType;
//	}
//
//	public Integer getRating() {
//		return rating;
//	}
//
//	public void setRating(Integer rating) {
//		this.rating = rating;
//	}
//
//	public String getWebsite() {
//		return website;
//	}
//
//	public void setWebsite(String website) {
//		this.website = website;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public PriceCategory getPriceCategory() {
//		return priceCategory;
//	}
//
//	public void setPriceCategory(PriceCategory priceCategory) {
//		this.priceCategory = priceCategory;
//	}
//
////	public Set<Image> getImage() {
////		return image;
////	}
////
////	public void setImage(Set<Image> image) {
////		this.image = image;
////	}
//
//	public Status getStatus() {
//		return status;
//	}
//
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
//		result = prime * result
//				+ ((description == null) ? 0 : description.hashCode());
////		result = prime * result + ((image == null) ? 0 : image.hashCode());
//		result = prime * result
//				+ ((priceCategory == null) ? 0 : priceCategory.hashCode());
//		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
//		result = prime * result
//				+ ((sightseeingId == null) ? 0 : sightseeingId.hashCode());
//		result = prime * result
//				+ ((sightseeingType == null) ? 0 : sightseeingType.hashCode());
//		result = prime * result + ((status == null) ? 0 : status.hashCode());
//		result = prime * result + ((website == null) ? 0 : website.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Sightseeing other = (Sightseeing) obj;
//		if (address == null) {
//			if (other.address != null)
//				return false;
//		} else if (!address.equals(other.address))
//			return false;
//		if (city == null) {
//			if (other.city != null)
//				return false;
//		} else if (!city.equals(other.city))
//			return false;
//		if (comment == null) {
//			if (other.comment != null)
//				return false;
//		} else if (!comment.equals(other.comment))
//			return false;
//		if (description == null) {
//			if (other.description != null)
//				return false;
//		} else if (!description.equals(other.description))
//			return false;
//		if (image == null) {
//			if (other.image != null)
//				return false;
//		} else if (!image.equals(other.image))
//			return false;
//		if (priceCategory == null) {
//			if (other.priceCategory != null)
//				return false;
//		} else if (!priceCategory.equals(other.priceCategory))
//			return false;
//		if (rating == null) {
//			if (other.rating != null)
//				return false;
//		} else if (!rating.equals(other.rating))
//			return false;
//		if (sightseeingId == null) {
//			if (other.sightseeingId != null)
//				return false;
//		} else if (!sightseeingId.equals(other.sightseeingId))
//			return false;
//		if (sightseeingType != other.sightseeingType)
//			return false;
//		if (status != other.status)
//			return false;
//		if (website == null) {
//			if (other.website != null)
//				return false;
//		} else if (!website.equals(other.website))
//			return false;
//		return true;
//	}
//	
}
