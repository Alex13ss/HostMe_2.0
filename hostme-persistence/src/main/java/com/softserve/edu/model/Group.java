package com.softserve.edu.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    /**
     * Contains images uploaded by this group
     */
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Image> images;

    /*
     * @ManyToMany(mappedBy = "group") 
     * private Set<User> users;
     * 
     * @ManyToMany(mappedBy = "group") 
     * private Set<Tag> tag;
     */

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Conversation> conversations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
