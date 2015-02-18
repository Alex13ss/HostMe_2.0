package com.softserve.edu.model;

import javax.persistence.*;

import com.softserve.edu.model.routes.Place;

@Entity
@Table(name = "Sightseeing", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@PrimaryKeyJoinColumn(name = "id")
public class Sightseeing extends Place {

	@Enumerated(EnumType.STRING)
	@Column(name = "sightseeing_type", updatable = false)
	private SightseeingType sightseeingType;

	public SightseeingType getSightseeingType() {
		return sightseeingType;
	}

	public void setSightseeingType(SightseeingType sightseeingType) {
		this.sightseeingType = sightseeingType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((sightseeingType == null) ? 0 : sightseeingType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sightseeing other = (Sightseeing) obj;
		if (sightseeingType != other.sightseeingType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sightseeing [sightseeingType=" + sightseeingType + "]";
	}

}