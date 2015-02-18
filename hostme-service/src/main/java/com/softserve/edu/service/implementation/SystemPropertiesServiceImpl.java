package com.softserve.edu.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import com.softserve.edu.dao.SystemPropertiesDao;
import com.softserve.edu.model.SystemProperties;
import com.softserve.edu.repositories.SystemPropertiesRepository;
import com.softserve.edu.service.SystemPropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesServiceImpl implements SystemPropertiesService {
	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;
	@Autowired
	private SystemPropertiesDao systemPropertiesDao;

	@Override
	@Transactional
	public SystemProperties getSystemProperty(Integer id) {
		return systemPropertiesRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public List<SystemProperties> getAllSystemProperties() {
		return (List<SystemProperties>) systemPropertiesRepository.findAll();
	}

	@Override
	@Transactional
	public String getMailUsername() {
		return systemPropertiesDao.getPropeties(EMAIL_LOGIN);
	}

	@Override
	@Transactional
	public String getMailPass() {
		return systemPropertiesDao.getPropeties(EMAIL_PASS_SEND);
	}

	@Override
	@Transactional
	public String getImageUrl() {
		return systemPropertiesDao.getPropeties(IMAGE_URL_PROP);
	}

	@Override
	@Transactional
	public String getImagePath() {
		return systemPropertiesDao.getPropeties(IMAGE_PATH_PROP);
	}

	@Override
	@Transactional
	public String getBaseUrl() {
		return systemPropertiesDao.getPropeties(BASE_SEND_URL);
	}
	
	@Override
	@Transactional
	public String getPropetyByName(String prop) {
		return systemPropertiesDao.getPropeties(prop);
	}

	@Override
	@Transactional
	public void updateSystemProperties(SystemProperties systemproperties) {
		SystemProperties newEvent = systemPropertiesRepository.findOne(systemproperties.getPropertyId());
		systemproperties.setPropertyId(newEvent.getPropertyId());
		systemproperties.setPropKey(newEvent.getPropKey());
		systemPropertiesRepository.save(systemproperties);
	}
	
	@Override
	@Transactional
	public void saveSystemProperties(SystemProperties systemproperties) {
		systemPropertiesRepository.save(systemproperties);
	}

}
