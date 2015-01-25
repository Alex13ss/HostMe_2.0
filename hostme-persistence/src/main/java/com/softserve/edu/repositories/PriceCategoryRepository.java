package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.PriceCategory;

public interface PriceCategoryRepository extends CrudRepository<PriceCategory, Integer> {

}
