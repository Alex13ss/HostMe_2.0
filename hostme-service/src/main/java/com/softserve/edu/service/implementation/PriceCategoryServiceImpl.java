package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.repositories.PriceCategoryRepository;
import com.softserve.edu.service.PriceCategoryService;

@Service
public class PriceCategoryServiceImpl implements PriceCategoryService {

	@Autowired
	private PriceCategoryRepository priceCategoryRepository;

	@Override
	@Transactional
	public List<PriceCategory> getAllPriceCategory() {
		List<PriceCategory> list = (List<PriceCategory>) priceCategoryRepository
				.findAll();
		return list;
	}
}
