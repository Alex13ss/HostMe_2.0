package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "PRICECATEGORIES", uniqueConstraints = { @UniqueConstraint(columnNames = "priceCategory_id") })
public class PriceCategory {

	@Id
	@GeneratedValue
	@Column(name = "priceCategory_id", unique = true, nullable = false)
	private Integer priceCategoryId;

	@Column(name = "description")
	private String priceCategory;

	public PriceCategory() {
		super();
	}

	public Integer getPriceCategoryId() {
		return priceCategoryId;
	}

	public void setPriceCategoryId(Integer priceCategoryId) {
		this.priceCategoryId = priceCategoryId;
	}

	public String getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(String priceCategory) {
		this.priceCategory = priceCategory;
	}

}
