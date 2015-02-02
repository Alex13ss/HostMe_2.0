package com.softserve.edu.service.implementation;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<User> interestedUsers = (List<User>) userRepository
                .findAllByInterestingGroups(group);
        notify.setToReceiveUsers(interestedUsers);
    }

}
