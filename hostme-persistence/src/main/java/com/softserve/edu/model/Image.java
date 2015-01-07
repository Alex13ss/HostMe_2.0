package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "IMAGES", uniqueConstraints = { @UniqueConstraint(columnNames =  "image_id") })
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
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((hosting == null) ? 0 : hosting.hashCode());
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Image other = (Image) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (hosting == null) {
			if (other.hosting != null)
				return false;
		} else if (!hosting.equals(other.hosting))
			return false;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
