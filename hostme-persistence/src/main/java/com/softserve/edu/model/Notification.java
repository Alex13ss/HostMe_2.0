package com.softserve.edu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notification_id", unique = true, nullable = false)
    private Long notifyId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "notify_created")
    private Date notifyCreated;

    @Column(name = "notify_message")
    private String notifyMessage;

    @ManyToOne
    @JoinColumn(name = "udated_group")
    private Group updatedGroup;

    /**
     * Contains list of users who must receive notifications of group which is
     * interesting for them
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_notifications", joinColumns = @JoinColumn(name = "notification_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<User> toReceiveUsers;

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

    public Group getUpdatedGroup() {
        return updatedGroup;
    }

    public void setUpdatedGroup(Group updatedGroup) {
        this.updatedGroup = updatedGroup;
    }

    public List<User> getToReceiveUsers() {
        return toReceiveUsers;
    }

    public void setToReceiveUsers(List<User> toReceiveUsers) {
        this.toReceiveUsers = toReceiveUsers;
    }

}
