package com.softserve.edu.service;

import com.softserve.edu.model.SystemProperties;

public interface SystemPropertiesService {

	public SystemProperties getSystemProperties();

	public String getMailUsername();

	public String getMailPass();

	public String getImageUrl();

	public String getImagePath();

	public String getBaseUrl();
	
	public void updateSystemProperties(SystemProperties systemproperties);
}
