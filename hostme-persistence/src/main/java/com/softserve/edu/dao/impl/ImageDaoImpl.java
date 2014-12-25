package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.ImageDao;
import org.springframework.stereotype.Repository;

import com.softserve.edu.entity.Image;

@Repository
public class ImageDaoImpl extends AbstractGenericDao<Image, Long> implements ImageDao {
	public ImageDaoImpl() {
		super(Image.class);
	}
}
