package com.softserve.edu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LANGUAGE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"language_id", "language" }) })
public class Language {
	@Id
	@GeneratedValue
	@Column(name = "language_Id", unique = true, nullable = false)
	private Short languageId;
	@Column(name = "language", length = 50, nullable = false, unique = true)
	private String language;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "languages")
	private List<User> users = new ArrayList<User>();

	public Language() {
	}

	public Language(String language) {
		this.language = language;
	}

	public Short getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Short languageId) {
		this.languageId = languageId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		if (user != null && !users.contains(user)) {
			users.add(user);
			user.addLanguage(this);
		}
	}

	@Override
	public String toString() {
		return language;
	}
	
	
}