package com.softserve.edu.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.softserve.edu.model.Notification;

public class NotificationDto {

    private Long notifyId;
    private Date notifyCreated;
    private String notifyMessage;
    private Long groupId;

    public NotificationDto() {
    }

    public NotificationDto(Notification notification) {
        this.notifyId = notification.getNotifyId();
        this.notifyCreated = notification.getNotifyCreated();
        this.notifyMessage = notification.getNotifyMessage();
        this.groupId = notification.getUpdatedGroup().getId();
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public Date getNotifyCreated() {
        return notifyCreated;
    }

    public void setNotifyCreated(Date notifyCreated) {
        this.notifyCreated = notifyCreated;
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }
    public String getNotificationDate() {
        if (notifyCreated != null ) {
            return new SimpleDateFormat("yyyy-MM-dd").format(notifyCreated.getTime());
        }
        return null;
        }

    public String getNotificationTime() {
        if (notifyCreated != null ) {
            return new SimpleDateFormat("HH:mm:ss").format(notifyCreated.getTime());
        }
        return null;
        }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
