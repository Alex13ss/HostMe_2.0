package com.softserve.edu.model;

import com.softserve.edu.model.routes.Place;

import java.util.Set;

import javax.persistence.*;
import com.google.common.base.Objects;

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

}