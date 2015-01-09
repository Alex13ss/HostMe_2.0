package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CITIES", uniqueConstraints = { @UniqueConstraint(columnNames = "city_id") })
public class City {
	@Id
	@GeneratedValue
	@Column(name = "city_id", unique = true, nullable = false)
	private Integer cityId;
/*
	@Column(name = "city", length = 32)
	private String city;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Event> event;

	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Sightseeing> sightseeing;

	public City() {
		super();
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Event> getEvent() {
		return event;
	}

	public void setEvent(Set<Event> event) {
		this.event = event;
	}

	public Set<Sightseeing> getSightseeing() {
		return sightseeing;
	}

	public void setSightseeing(Set<Sightseeing> sightseeing) {
		this.sightseeing = sightseeing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
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
		City other = (City) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (sightseeing == null) {
			if (other.sightseeing != null)
				return false;
		} else if (!sightseeing.equals(other.sightseeing))
			return false;
		return true;
	}
	*/
}
