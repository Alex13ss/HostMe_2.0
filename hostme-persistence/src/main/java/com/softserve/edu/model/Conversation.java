package com.softserve.edu.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Conversation {
	@Id
	@GeneratedValue
	private Long id;
	/*
	@Column(name = "name")
	private String name;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User ownerUser;
	
	@OneToMany()
	private Set<User> moderators;
	
	@OneToMany(mappedBy = "conversation_id")
	private Set<Post> posts;
	*/
}
