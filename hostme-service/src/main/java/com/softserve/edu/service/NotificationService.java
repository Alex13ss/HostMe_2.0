package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.dto.NotificationDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;

public interface NotificationService {

    void addNotification(Group group, String msg);

    List<NotificationDto> findAllNotificationsByUser(User user);

    Notification findOne(Long id);

    void removeNotifyRelationship(Integer userId, Long notifyId);

}
