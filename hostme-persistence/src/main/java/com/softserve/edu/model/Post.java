package com.softserve.edu.model;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postedAt")
    private Calendar postedAt;
    /*
     * @OneToMany//ManyToMany private Set<Attachment> attachments;
     */
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Calendar postedAt) {
        this.postedAt = postedAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
