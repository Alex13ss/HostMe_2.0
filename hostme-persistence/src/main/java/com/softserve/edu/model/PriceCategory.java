package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PRICECATEGORIES", uniqueConstraints = { @UniqueConstraint(columnNames = "priceCategory_id") })
public class PriceCategory {

	@Id
	@GeneratedValue
	@Column(name = "priceCategory_id", unique = true, nullable = false)
	private Integer priceCategoryId;

	@Column(name = "description")
	private String priceCategory;

	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Event> event;

//	@OneToMany(mappedBy = "priceCategory", fetch = FetchType.EAGER, orphanRemoval = true)
//	private Set<Sightseeing> sightseeing;

	public PriceCategory() {
		super();
	}

	public Integer getPriceCategoryId() {
		return priceCategoryId;
	}

	public void setPriceCategoryId(Integer priceCategoryId) {
		this.priceCategoryId = priceCategoryId;
	}

	public String getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(String priceCategory) {
		this.priceCategory = priceCategory;
	}

	public Set<Event> getEvent() {
		return event;
	}

	public void setEvent(Set<Event> event) {
		this.event = event;
	}

/*	public Set<Sightseeing> getSightseeing() {
		return sightseeing;
	}

	public void setSightseeing(Set<Sightseeing> sightseeing) {
		this.sightseeing = sightseeing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result
				+ ((priceCategory == null) ? 0 : priceCategory.hashCode());
		result = prime * result
				+ ((priceCategoryId == null) ? 0 : priceCategoryId.hashCode());
		result = prime * result
				+ ((sightseeing == null) ? 0 : sightseeing.hashCode());
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
		PriceCategory other = (PriceCategory) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (priceCategory == null) {
			if (other.priceCategory != null)
				return false;
		} else if (!priceCategory.equals(other.priceCategory))
			return false;
		if (priceCategoryId == null) {
			if (other.priceCategoryId != null)
				return false;
		} else if (!priceCategoryId.equals(other.priceCategoryId))
			return false;
		if (sightseeing == null) {
			if (other.sightseeing != null)
				return false;
		} else if (!sightseeing.equals(other.sightseeing))
			return false;
		return true;
	}*/


	
}
