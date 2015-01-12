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
    private Long id;
    
    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
}
