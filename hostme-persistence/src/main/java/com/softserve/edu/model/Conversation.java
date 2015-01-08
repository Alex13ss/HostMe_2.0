package com.softserve.edu.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createdAt")
    private Date createdAt;

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User ownerUser;

    @OneToMany()
    private Set<User> moderators;

    @OneToMany(mappedBy = "conversation")
    private Set<Post> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public Set<User> getModerators() {
        return moderators;
    }

    public void setModerators(Set<User> moderators) {
        this.moderators = moderators;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
    
}
