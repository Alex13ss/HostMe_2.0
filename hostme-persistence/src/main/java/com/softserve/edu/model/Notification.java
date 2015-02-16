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
import com.google.common.base.Objects;

/**
 * @author Oleksandr Bandurka Entity-class for notification
 */
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

    @Override
    public int hashCode() {
        return Objects.hashCode(notifyId, notifyCreated, notifyMessage,
                updatedGroup, toReceiveUsers);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Notification) {
            Notification that = (Notification) object;
            return Objects.equal(this.notifyId, that.notifyId)
                    && Objects.equal(this.notifyCreated, that.notifyCreated)
                    && Objects.equal(this.notifyMessage, that.notifyMessage)
                    && Objects.equal(this.updatedGroup, that.updatedGroup)
                    && Objects.equal(this.toReceiveUsers, that.toReceiveUsers);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Notification [notifyId=" + notifyId + ", notifyCreated="
                + notifyCreated + ", notifyMessage=" + notifyMessage
                + ", updatedGroup=" + updatedGroup + ", toReceiveUsers="
                + toReceiveUsers + "]";
    }

}
