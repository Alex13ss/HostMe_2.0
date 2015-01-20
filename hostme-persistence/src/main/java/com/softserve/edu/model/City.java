package com.softserve.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.routes.Place;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CITIES", uniqueConstraints = { @UniqueConstraint(columnNames = "city_id") })
public class City {
	@Id
	@SequenceGenerator(name = "cities_country_id_seq", sequenceName = "cities_country_id_seq", allocationSize = 111)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_country_id_seq")
	@Column(name = "city_id", unique = true, nullable = false)
	private Integer cityId;

	@Column(name = "city", length = 64)
	private String city;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnore
	private Set<Place> places;

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

	@JsonIgnore
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		City city1 = (City) o;

		if (!city.equals(city1.city))
			return false;
		if (!cityId.equals(city1.cityId))
			return false;
		if (!country.equals(city1.country))
			return false;
		if (places != null ? !places.equals(city1.places)
				: city1.places != null)
			return false;

		return true;
	}

	// @Override
	// public int hashCode() {
	// int result = cityId.hashCode();
	// result = 31 * result + city.hashCode();
	// result = 31 * result + country.hashCode();
	// result = 31 * result + (places != null ? places.hashCode() : 0);
	// return result;
	// }
}
