package com.softserve.edu.service.implementation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.softserve.edu.dao.ImageDao;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.entity.User;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.SystemPropertiesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.entity.Image;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDao imageDao;

	@Autowired
	SystemPropertiesService systemPropertiesService;

	@Override
	@Transactional
	public void addImages(MultipartFile[] files, Hosting hosting) {
		if (filesNotEmpty(files))
			for (int i = 0; i < files.length; i++) {
				saveImage(files[i], buildPath(hosting));
				addImage(files[i], hosting);
			}
	}

	private boolean filesNotEmpty(MultipartFile[] files) {
		if (files[0].getOriginalFilename().isEmpty())
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void addImages(MultipartFile file, User user) {
		saveImage(file, buildPath(user));
		addImage(file, user);
	}

	private void addImage(MultipartFile multipartFile, User user) {
		Image image = new Image();
		image.setLink(PROFILE_PIC_PATH + "/" + user.getUserId() + "/"
				+ multipartFile.getOriginalFilename());
		image.setUser(user);

		for (Image im : user.getImages()) {
			imageDao.delete(im);
		}

		imageDao.create(image);
	}

	@Override
	@Transactional
	public String getImagePath() {
		return systemPropertiesService.getImageUrl() + "/";
	}

	private String buildPath(User user) {
		return systemPropertiesService.getImagePath() + File.separator
				+ PROFILE_PIC_PATH + File.separator + user.getUserId();
	}

	private void saveImage(MultipartFile multipartFile, String path) {
		try {
			byte[] bytes = multipartFile.getBytes();

			// Creating the directory to store file
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			// Write file to directory
			File image = new File(dir + File.separator
					+ multipartFile.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(image));
			stream.write(bytes);
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	private void addImage(MultipartFile multipartFile, Hosting hosting) {
		Image image = new Image();
		image.setLink(hosting.getHostingId() + "/"
				+ multipartFile.getOriginalFilename());
		image.setHosting(hosting);
		imageDao.create(image);

	}

	private String buildPath(Hosting hosting) {
		return systemPropertiesService.getImagePath() + File.separator
				+ hosting.getHostingId();
	}

	@Override
	public String getUserAvatar(User user) {
		Iterator<Image> imageItr = user.getImages().iterator();
		if (imageItr.hasNext())
			return systemPropertiesService.getImageUrl() + "/"
					+ imageItr.next().getLink();
		else
			return systemPropertiesService.getImageUrl() + "/"
					+ PROFILE_PIC_PATH + "/" + NO_AVATAR;
	}

	@Override
	public void deleteImagesForHosting(Hosting hosting) {
		for (Image im : hosting.getImages()) {
			imageDao.delete(im);
		}
		try {
			FileUtils.deleteDirectory(new File(buildPath(hosting)));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
