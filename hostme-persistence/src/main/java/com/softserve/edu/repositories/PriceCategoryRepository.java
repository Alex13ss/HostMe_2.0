package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.PriceCategory;

public interface PriceCategoryRepository extends
		CrudRepository<PriceCategory, Integer> {
	public PriceCategory findByPriceCategory(String priceCategory);

	public PriceCategory findOne(Integer id);
}
