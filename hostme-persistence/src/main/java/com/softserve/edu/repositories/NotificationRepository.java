package com.softserve.edu.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;

public interface NotificationRepository extends
        PagingAndSortingRepository<Notification, Long> {

    public Iterable<Notification> findAllNotificationsByToReceiveUsers(User user);

}
