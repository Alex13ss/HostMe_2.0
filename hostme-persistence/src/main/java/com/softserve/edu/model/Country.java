package com.softserve.edu.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.common.base.Objects;

@Entity
@Table(name = "COUNTRIES", uniqueConstraints = { @UniqueConstraint(columnNames = "country_id") })
public class Country {
	@Id
	@SequenceGenerator(name = "countries_country_id_seq", sequenceName = "countries_country_id_seq", allocationSize = 111)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countries_country_id_seq")
	@Column(name = "country_id", unique = true, nullable = false)
	private Integer countryId;

	@Column(name = "country", length = 64)
	private String country;
		
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<City> city;

	public Country() {
		super();
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<City> getCity() {
		return city;
	}

	public void setCity(Set<City> city) {
		this.city = city;
	}

	

	
	
}
