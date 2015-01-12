package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group {
    
    @Id
    @GeneratedValue
    @Column(name = "group_id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "group_name", nullable = false)
    private String groupName;
/*    
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Image> image;
    
    @ManyToMany(mappedBy = "group")
    private Set<User> users;
    
    
    // name ???
    
    @ManyToMany(mappedBy = "group")
    private Set<Tag> tag;
    
    @OneToMany(mappedBy = "group")
    private Set<Conversation> conversations;
    
*/    

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
/*
    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Tag> getTag() {
        return tag;
    }

    public void setTag(Set<Tag> tag) {
        this.tag = tag;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }
    
*/}
