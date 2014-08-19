package com.softserve.edu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FEEDBACK", uniqueConstraints = { @UniqueConstraint(columnNames = "feedback_id") })
public class Feedback {
	@Id
	@GeneratedValue
	@Column(name = "feedback_id", unique = true, nullable = false)
	private Long feedbackId;
	@Column(name = "title", nullable = false, length = 80)
	private String title;
	@Column(name = "description", nullable = false, length = 1000)
	private String description;
	@ManyToOne
	@JoinColumn(name = "hosting_id", nullable = false)
	private Hosting hosting;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;
	@OneToMany(mappedBy = "feedback", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Image> images = new HashSet<Image>();

	public Feedback() {
	}

	public Feedback(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Hosting getHosting() {
		return hosting;
	}

	public void setHosting(Hosting hosting) {
		this.hosting = hosting;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User user) {
		this.author = user;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void addImage(Image image) {
		if (image != null && !images.contains(image)) {
			images.add(image);
			image.setFeedback(this);
		}
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", title=" + title + ", description=" + description + "]";
	}

}
