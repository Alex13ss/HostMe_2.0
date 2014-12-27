package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "IMAGE", uniqueConstraints = { @UniqueConstraint(columnNames =  "image_id") })
public class Image {
	@Id
	@GeneratedValue
	@Column(name = "image_id", unique = true, nullable = false)
	private Integer imageId;
	@Column(name = "link", nullable = false)
	private String link;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "hosting_id")
	private Hosting hosting;
	@ManyToOne
	@JoinColumn(name = "feedback_id")
	private Feedback feedback;

	public Image() {
	}

	public Image(String link) {
		super();
		this.link = link;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {		
		this.user = user;
	}

	public Hosting getHosting() {
		return hosting;
	}

	public void setHosting(Hosting hosting) {
		this.hosting = hosting;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", link=" + link + "]";
	}

}
