package com.softserve.edu.dto;

import java.util.Date;
import java.util.Set;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.Status;

public class GroupDto {

    private Long id;
    private String groupName;
    private String groupDescription;
    private Date createdAt;
    private Set<Image> images;
    private String creatorFNandLN;
    private Status status;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.groupDescription = group.getGroupDescription();
        this.createdAt = group.getCreatedAt();
        this.images = group.getImages();
        this.creatorFNandLN = group.getCreatorUser().getFirstName() + " "
                + group.getCreatorUser().getLastName();
        this.status = group.getStatus();
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getCreatorFNandLN() {
        return creatorFNandLN;
    }

    public void setCreatorFNandLN(String creatorFNandLN) {
        this.creatorFNandLN = creatorFNandLN;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
