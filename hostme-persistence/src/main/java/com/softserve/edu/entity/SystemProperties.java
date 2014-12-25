package com.softserve.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SYSTEM_PROPERTIES", uniqueConstraints = { @UniqueConstraint(columnNames = "property_id") })
public class SystemProperties {

	@Id
	@GeneratedValue
	@Column(name = "property_id", unique = true, nullable = false)
	private Integer propertyId;
	@Column(name = "prop_key", length = 200, nullable = false)
	private String propKey;
	@Column(name = "value", length = 200, nullable = false)
	private String value;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
