package com.softserve.edu.service.implementation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.ImageRepository;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.SystemPropertiesService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    SystemPropertiesService systemPropertiesService;

    private UUID ImageUUId;

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
                + ImageUUId);
        image.setUser(user);
        for (Image im : user.getImages()) {
            imageRepository.delete(im);
        }
        imageRepository.save(image);
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
            ImageUUId = UUID.randomUUID();
            File image = new File(dir + File.separator + ImageUUId);
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
        image.setLink(hosting.getHostingId() + "/" + ImageUUId);
        image.setHosting(hosting);
        imageRepository.save(image);
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
            imageRepository.delete(im);
        }
        try {
            FileUtils.deleteDirectory(new File(buildPath(hosting)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void addImagesToSightseeing(MultipartFile[] files,
            Sightseeing sightseeing) {
        if (filesNotEmpty(files))
            for (int i = 0; i < files.length; i++) {
                saveImage(files[i], buildPath(sightseeing));
                addImageToSight(files[i], sightseeing);
            }
    }

    @Override
    @Transactional
    public void addImagesToEvent(MultipartFile[] files, Event event) {
        if (filesNotEmpty(files))
            for (int i = 0; i < files.length; i++) {
                saveImage(files[i], buildPathEvent(event));
                addImageToEvent(files[i], event);
            }
    }

    @Override
    @Transactional
    public void addImageToGroup(MultipartFile file, Group group) {
        saveImage(file, buildPathGroup(group));
        addGroupImg(file, group);
    }

    private String buildPath(Sightseeing sightseeing) {
        return systemPropertiesService.getImagePath() + File.separator
                + "Sightseeings" + File.separator + sightseeing.getId();
    }

    private String buildPathEvent(Event event) {
        return systemPropertiesService.getImagePath() + File.separator
                + "Event" + File.separator + event.getId();
    }

    private String buildPathGroup(Group group) {
        return systemPropertiesService.getImagePath() + File.separator
                + "Group" + File.separator + group.getId();
    }

    @Transactional
    private void addImageToSight(MultipartFile multipartFile,
            Sightseeing sightseeing) {
        Image image = new Image();
        image.setLink("Sightseeings/" + sightseeing.getId() + "/" + ImageUUId);
        image.setPlace(sightseeing);
        imageRepository.save(image);
    }

    @Transactional
    private void addImageToEvent(MultipartFile multipartFile, Event event) {
        Image image = new Image();
        image.setLink("Event/" + event.getId() + "/" + ImageUUId);
        image.setPlace(event);
        imageRepository.save(image);
    }

    private void addGroupImg(MultipartFile file, Group group) {
        Image image = new Image();
        image.setLink("Group/" + group.getId() + "/" + ImageUUId);
        image.setGroup(group);
        imageRepository.save(image);
    }
}
