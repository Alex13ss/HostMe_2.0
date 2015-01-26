package com.softserve.edu.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Oleksandr Bandurka Entity-class for group
 * 
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "groups_group_id_seq", sequenceName = "groups_group_id_seq", allocationSize = 31)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_group_id_seq")
    @Column(name = "group_id", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "?!.. You can't create group without name!")
    @Size(min = 3, message = "Group name must be at least 3 characters!")
    @Column(name = "group_name")
    private String groupName;

    @NotBlank(message = "You need some cool description for your group! ;)")
    @Size(min = 5, message = "Group description must be at least 5 characters!")
    @Column(name = "group_description")
    private String groupDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_edited_at")
    private Date lastEditedAt;

    /**
     * Contains images uploaded by this group
     */
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Image> images;

    /**
     * Contains conversations created in this group
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<Conversation> conversations;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    private User creatorUser;

    @ManyToOne
    @JoinColumn(name = "editor_user")
    private User lastEditor;

    /**
     * Contains list of users which have conversations in this group
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<User> users;

    /**
     * Contains list of tags which included by this group
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_tags", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Group() {
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

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(User lastEditor) {
        this.lastEditor = lastEditor;
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

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", groupName=" + groupName
                + ", groupDescription=" + groupDescription + ", createdAt="
                + createdAt + ", lastEditedAt=" + lastEditedAt + ", images="
                + images + ", conversations=" + conversations
                + ", creatorUser=" + creatorUser + ", lastEditor=" + lastEditor
                + ", users=" + users + ", tags=" + tags + "]";
    }

}
