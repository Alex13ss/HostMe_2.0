package com.softserve.edu.service.implementation;

import javax.transaction.Transactional;

import com.softserve.edu.model.SystemProperties;
import com.softserve.edu.repositories.SystemPropertiesRepository;
import com.softserve.edu.service.SystemPropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesServiceImpl implements SystemPropertiesService {
	public final Integer PROPERTY_ID = 1;
	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;

	@Override
	@Transactional
	public SystemProperties getSystemProperties() {
		return systemPropertiesRepository.findOne(PROPERTY_ID);
	}

	@Override
	@Transactional
	public String getMailUsername() {
		return getSystemProperties().getEmailLogin();
	}

	@Override
	@Transactional
	public String getMailPass() {
		return getSystemProperties().getEmailPass();
	}

	@Override
	@Transactional
	public String getImageUrl() {
		return getSystemProperties().getImageURL();
	}

	@Override
	@Transactional
	public String getImagePath() {
		return getSystemProperties().getImagePath();
	}

	@Override
	@Transactional
	public String getBaseUrl() {
		return getSystemProperties().getBaseURL();
	}

	@Override
	@Transactional
	public void updateSystemProperties(SystemProperties systemproperties) {
		systemproperties.setPropertyId(PROPERTY_ID);
		systemPropertiesRepository.save(systemproperties);
	}

}
