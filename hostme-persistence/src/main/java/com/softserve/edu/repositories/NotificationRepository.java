package com.softserve.edu.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Notification;

public interface NotificationRepository extends
        PagingAndSortingRepository<Notification, Long> {

}
