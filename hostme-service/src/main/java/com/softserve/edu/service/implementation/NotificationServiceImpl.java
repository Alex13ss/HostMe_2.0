package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.NotificationDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.NotificationRepository;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addNotification(Group group, String msg) {
        Notification notify = new Notification();
        notify.setUpdatedGroup(group);
        notify.setNotifyCreated(new Date());
        notify.setNotifyMessage(msg);
        notificationRepository.save(notify);
        List<User> interestedUsers = userRepository
                .findAllByInterestingGroups(group);
        notify.setToReceiveUsers(interestedUsers);
    }

    @Override
    @Transactional
    public List<NotificationDto> findAllNotificationsByUser(User user) {
        List<NotificationDto> list = new ArrayList<NotificationDto>();
        for (Notification notification : notificationRepository
                .findAllNotificationsByToReceiveUsers(user)) {
            list.add(new NotificationDto(notification));
        }
        return list;
    }

    @Override
    @Transactional
    public Notification findOne(Long id) {
        return notificationRepository.findOne(id);
    }

    @Override
    @Transactional
    public void removeNotifyRelationship(Integer userId, Long notifyId) {
        notificationRepository.removeNotification(userId, notifyId);
    }
}
