package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "system_properties", uniqueConstraints = { @UniqueConstraint(columnNames = "property_id") })
public class SystemProperties {

	@Id
	@SequenceGenerator(name = "property_id_seq", sequenceName = "property_id_seq", allocationSize = 7)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_id_seq")
	@Column(name = "property_id", unique = true, nullable = false)
	private Integer propertyId;

	@Column(name = "prop_key")
	private String propKey;

	@Column(name = "value")
	private String value;


	public SystemProperties() {
		super();
	}
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propKey == null) ? 0 : propKey.hashCode());
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SystemProperties other = (SystemProperties) obj;
		if (propKey == null) {
			if (other.propKey != null)
				return false;
		} else if (!propKey.equals(other.propKey))
			return false;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}

