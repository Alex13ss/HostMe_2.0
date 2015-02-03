package com.softserve.edu.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.SystemProperties;

public interface SystemPropertiesRepository extends CrudRepository<SystemProperties, Integer> {
};
