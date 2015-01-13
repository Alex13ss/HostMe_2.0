package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Oleksandr Bandurka Entity-class for tag
 * 
 */
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private Set<Group> groups;

    public Tag() {
    }

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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Tag: [ ID: " + id + ", Tag Name: " + tagName + " ]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tag other = (Tag) obj;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tagName == null) {
            if (other.tagName != null)
                return false;
        } else if (!tagName.equals(other.tagName))
            return false;
        return true;
    }

}
