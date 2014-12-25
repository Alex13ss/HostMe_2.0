package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.ImageDao;
import com.softserve.edu.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends AbstractGenericDao<Image, Long> implements ImageDao {
	public ImageDaoImpl() {
		super(Image.class);
	}
}
