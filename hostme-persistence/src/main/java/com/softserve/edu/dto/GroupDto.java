package com.softserve.edu.dto;

import java.util.Date;
import java.util.Set;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.Tag;
import com.softserve.edu.model.User;

public class GroupDto {

    private Long id;
    private String groupName;
    private String groupDescription;
    private Date createdAt;
    private Date lastEditedAt;
    private Set<Image> images;
    private Set<Conversation> conversations;
    private String creatorFNandLN;
    private Set<User> users;
    private Set<Tag> tags;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.groupDescription = group.getGroupDescription();
        this.createdAt = group.getCreatedAt();
        this.lastEditedAt = group.getLastEditedAt();
        this.images = group.getImages();
        this.conversations = group.getConversations();
        this.creatorFNandLN = group.getCreatorUser().getFirstName() + " "
                + group.getCreatorUser().getLastName();
        this.users = group.getUsers();
        this.tags = group.getTags();
    }

    public GroupDto(Group group, User user) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.groupDescription = group.getGroupDescription();
        this.createdAt = group.getCreatedAt();
        this.lastEditedAt = group.getLastEditedAt();
        this.images = group.getImages();
        this.conversations = group.getConversations();
        this.creatorFNandLN = user.getFirstName() + " " + user.getLastName();
        this.users = group.getUsers();
        this.tags = group.getTags();
    }

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

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
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

    public String getCreatorFNandLN() {
        return creatorFNandLN;
    }

    public void setCreatorFNandLN(String creatorFNandLN) {
        this.creatorFNandLN = creatorFNandLN;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}