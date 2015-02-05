package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;
import com.softserve.edu.model.SystemProperties;

public interface SystemPropertiesRepository extends CrudRepository<SystemProperties, Integer> {
};
