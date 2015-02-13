package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.model.SystemProperties;

public interface SystemPropertiesService extends Settings {

	public SystemProperties getSystemProperty(Integer id);

	public String getMailUsername();

	public String getMailPass();

	public String getImageUrl();

	public String getImagePath();

	public String getBaseUrl();
	
	public List<SystemProperties> getAllSystemProperties();
	
	public void updateSystemProperties(SystemProperties systemproperties);

	void saveSystemProperties(SystemProperties systemproperties);
}
