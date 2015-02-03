package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "system_properties")
public class SystemProperties {

	@Id
    @SequenceGenerator(name = "system_properties_property_id_seq", sequenceName = "system_properties_property_id_seq", allocationSize = 31)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_properties_property_id_seq")
	@Column(name = "property_id", unique = true, nullable = false)
	private Integer propertyId;

	@Column(name = "image_path", nullable = false)
	private  String imagePath;

	@Column(name = "image_url", nullable = false)
	private  String imageURL;

	@Column(name = "email_pass", nullable = false)
	private  String emailPass;

	@Column(name = "email_login", nullable = false)
	private String emailLogin;

	@Column(name = "base_url", nullable = false)
	private String baseURL;
	
	public SystemProperties() {
    }

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getEmailPass() {
		return emailPass;
	}

	public void setEmailPass(String emailPass) {
		this.emailPass = emailPass;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseURL == null) ? 0 : baseURL.hashCode());
		result = prime * result + ((emailLogin == null) ? 0 : emailLogin.hashCode());
		result = prime * result + ((emailPass == null) ? 0 : emailPass.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
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
		if (baseURL == null) {
			if (this.baseURL != null)
				return false;
		} else if (!baseURL.equals(this.baseURL))
			return false;
		if (emailLogin == null) {
			if (this.emailLogin != null)
				return false;
		} else if (!emailLogin.equals(this.emailLogin))
			return false;
		if (emailPass == null) {
			if (this.emailPass != null)
				return false;
		} else if (!emailPass.equals(this.emailPass))
			return false;
		if (imagePath == null) {
			if (this.imagePath != null)
				return false;
		} else if (!imagePath.equals(this.imagePath))
			return false;
		if (imageURL == null) {
			if (this.imageURL != null)
				return false;
		} else if (!imageURL.equals(this.imageURL))
			return false;
		if (propertyId == null) {
			if (this.propertyId != null)
				return false;
		} else if (!propertyId.equals(this.propertyId))
			return false;
		return true;
	}

}
