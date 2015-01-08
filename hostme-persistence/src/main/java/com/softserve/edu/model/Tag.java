package com.softserve.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAGS")
public class Tag {
    
    @Id
    @GeneratedValue
    @Column(name = "tag_id", unique = true, nullable = false)
    private Long tagId;
    
    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
}
