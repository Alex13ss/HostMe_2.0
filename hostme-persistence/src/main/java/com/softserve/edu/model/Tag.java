package com.softserve.edu.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.google.common.base.Objects;

/**
 * @author Oleksandr Bandurka Entity-class for tag
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
    public int hashCode() {
        return Objects.hashCode(id, tagName, groups);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Tag) {
            Tag that = (Tag) object;
            return Objects.equal(this.id, that.id)
                    && Objects.equal(this.tagName, that.tagName)
                    && Objects.equal(this.groups, that.groups);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", tagName=" + tagName + ", groups=" + groups
                + "]";
    }

}
