package com.softserve.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HOSTING", uniqueConstraints = { @UniqueConstraint(columnNames = "hosting_id" ) })
public class Hosting {
	@Id
	@GeneratedValue
	@Column(name = "hosting_id", unique = true, nullable = false)
	private Integer hostingId;

	@Column(name = "country", length = 50, nullable = false)
	private String country;

	@Column(name = "region", length = 50, nullable = false)
	private String region;

	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@Column(name = "address", length = 150, nullable = false)
	private String address;

	@Column(name = "min_guests")
	private Integer minNumberOfGuests;

	@Column(name = "max_guests")
	private Integer maxNumberOfGuests;

	@Column(name = "children")
	private Boolean children;

	@Column(name = "pets")
	private Boolean pets;

	@Column(name = "smoking")
	private Boolean smoking;

	@Column(name = "family")
	private Boolean family;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "notes", length = 1000)
	private String notes;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;

	@JsonIgnore
	@OneToMany(mappedBy = "hosting", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.DELETE, CascadeType.PERSIST, CascadeType.SAVE_UPDATE })
	private Set<Image> images = new HashSet<Image>();

	@JsonIgnore
	@OneToMany(mappedBy = "hosting", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Feedback> feedbacks = new HashSet<Feedback>();

	@JsonIgnore
	@OneToMany(mappedBy = "hosting", fetch = FetchType.LAZY)
	private Set<Request> requests = new HashSet<Request>();

	public Hosting() {
	}

	public Integer getHostingId() {
		return hostingId;
	}

	public void setHostingId(Integer hostingId) {
		this.hostingId = hostingId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMinNumberOfGuests() {
		return minNumberOfGuests;
	}

	public void setMinNumberOfGuests(Integer minNumberOfGuests) {
		this.minNumberOfGuests = minNumberOfGuests;
	}

	public Integer getMaxNumberOfGuests() {
		return maxNumberOfGuests;
	}

	public void setMaxNumberOfGuests(Integer maxNumberOfGuests) {
		this.maxNumberOfGuests = maxNumberOfGuests;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public Boolean getPets() {
		return pets;
	}

	public void setPets(Boolean pets) {
		this.pets = pets;
	}

	public Boolean getSmoking() {
		return smoking;
	}

	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}

	public Boolean getFamily() {
		return family;
	}

	public void setFamily(Boolean family) {
		this.family = family;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		if (this.owner != owner)
			this.owner = owner;
	}

	public Set<Image> getImages() {
		return images;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void addImage(Image image) {
		if (image != null && !images.contains(image)) {
			images.add(image);
			image.setHosting(this);
		}
	}

	public void addRequest(Request request) {
		if (request != null && !requests.contains(request)) {
			requests.add(request);
			request.setHosting(this);
		}
	}

	public void addFeedback(Feedback feedback) {
		if (feedback != null && !feedbacks.contains(feedback)) {
			feedbacks.add(feedback);
			feedback.setHosting(this);
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Hosting [hostingId=" + hostingId + ", address=" + address
				+ ", minNumberOfGuests=" + minNumberOfGuests
				+ ", maxNumberOfGuests=" + maxNumberOfGuests + ", children="
				+ children + ", pets=" + pets + ", smoking=" + smoking
				+ ", family=" + family + ", gender=" + gender + ", notes="
				+ notes + "]";
	}

}