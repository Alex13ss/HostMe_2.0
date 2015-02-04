package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.model.Notification;
import com.softserve.edu.model.User;
import com.softserve.edu.service.NotificationService;
import com.softserve.edu.service.ProfileService;

@Controller
public class NotificationController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public String notificationsCreationShow() {
        return "notifications";
    }
    
    @RequestMapping(value = "/notifications", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Notification> getUpdatedInterestingGroups() {
        User interestedUser = profileService
                .getUserByLogin(SecurityContextHolder.getContext()
                        .getAuthentication().getName());
        List<Notification> notifications = notificationService
                .findAllNotificationsByUser(interestedUser);
        return notifications;
    }

}
