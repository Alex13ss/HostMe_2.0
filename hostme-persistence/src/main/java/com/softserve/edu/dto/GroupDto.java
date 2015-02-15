package com.softserve.edu.dto;

import java.util.Date;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Status;

public class GroupDto {

    private Long id;
    private String groupName;
    private String groupDescription;
    private Date createdAt;
    private String imgLink;
    private Status status;

    public GroupDto() {
    }

    public GroupDto(Group group, String imgUrl) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.groupDescription = group.getGroupDescription();
        this.createdAt = group.getCreatedAt();
        this.status = group.getStatus();
        if (group.getImages().isEmpty()) {
            setImgLink("resources/images/group-default.jpg");
        } else {
            setImgLink(imgUrl + group.getImages().iterator().next().getLink());
        }
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

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
