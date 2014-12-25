package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.SystemPropertiesDao;
import com.softserve.edu.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	@Autowired
	SystemPropertiesDao systemPropertiesDao;

	@Override
	@Transactional
	public String getImagePath() {
		return systemPropertiesDao.getPropeties(IMAGE_PATH_PROP);
	}

	@Override
	@Transactional
	public String getImageUrl() {
		return systemPropertiesDao.getPropeties(IMAGE_URL_PROP);
	}
	
	@Override
    	@Transactional
    	public String getMailPass() {
        	return systemPropertiesDao.getPropeties(EMAIL_PASS_SEND);
    	}

    	@Override
    	@Transactional
    	public String getMailUsername() {
        	return systemPropertiesDao.getPropeties(EMAIL_LOGIN);
    	}
    	
    	@Override
    	@Transactional
    	public String getBaseUrl() {
        	return systemPropertiesDao.getPropeties(BASE_SEND_URL);
    	}
}
