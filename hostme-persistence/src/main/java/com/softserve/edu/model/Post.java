package com.softserve.edu.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "postedAt")
    private Date postedAt;
    /*
     * @OneToMany//ManyToMany private Set<Attachment> attachments;
     */
    @ManyToOne
    private User author;

    @ManyToOne()
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

}
