package com.softserve.edu.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.google.common.base.Objects;

@Entity
@Table(name = "COUNTRIES", uniqueConstraints = { @UniqueConstraint(columnNames = "country_id") })
public class Country {
	@Id
	@GeneratedValue
	@Column(name = "country_id", unique = true, nullable = false)
	private Integer countryId;

	@Column(name = "country", length = 64)
	private String country;
		
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, orphanRemoval = true)
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

	@Override
	public int hashCode() {
		return Objects.hashCode(countryId, country, city);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Country) {
			Country that = (Country) object;
			return Objects.equal(this.countryId, that.countryId)
					&& Objects.equal(this.country, that.country)
					&& Objects.equal(this.city, that.city);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", country=" + country
				+ ", city=" + city + "]";
	}

	
	
}
