package com.softserve.edu.dao;

import com.softserve.edu.entity.SystemProperties;

public interface SystemPropertiesDao extends
		GenericDao<SystemProperties, Integer> {

	String getPropeties(String prop);

}
