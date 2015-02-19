package com.softserve.edu.service;

import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

public interface ImageService {

    String PROFILE_PIC_PATH = "profile_pics";
    String NO_AVATAR = "noavatar/noavatar.png";

    void addImages(MultipartFile[] files, Hosting hosting);

    void addImages(MultipartFile file, User user);

    String getUserAvatar(User user);

    void deleteImagesForHosting(Hosting hosting);

    String getImagePath();

    void addImagesToSightseeing(MultipartFile[] files, Sightseeing sightseeing);

    public void addImagesToEvent(MultipartFile[] files, Event event);

    void addImageToGroup(MultipartFile file, Group group);

    void deleteImageForGroup(Group group);

}
