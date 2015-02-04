package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;

public interface NotificationService {

    public void addNotification(Group group, String msg);

    public List<Notification> findAllNotificationsByUser(User user);

}
