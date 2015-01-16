package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "PRICECATEGORIES", uniqueConstraints = { @UniqueConstraint(columnNames = "priceCategory_id") })
public class PriceCategory {

	@Id
	@SequenceGenerator(name = "pricecategories_pricecategory_id_seq", sequenceName = "pricecategories_pricecategory_id_seq", allocationSize = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricecategories_pricecategory_id_seq")
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
