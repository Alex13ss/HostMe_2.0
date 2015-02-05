package com.softserve.edu.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;

public interface NotificationRepository extends
        PagingAndSortingRepository<Notification, Long> {

    public Iterable<Notification> findAllNotificationsByToReceiveUsers(User user);

    @Modifying
    @Transactional("transactionManager")
    @Query(value = "DELETE FROM user_notifications WHERE notification_id = ?2 AND user_id = ?1", nativeQuery = true)
    public void removeNotification(Integer userId, Long notyfyId);

}
