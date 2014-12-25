package com.softserve.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE", schema = "hostme", uniqueConstraints = { @UniqueConstraint(columnNames = { "role_id" }) })
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "role_id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "role", length = 10)
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
