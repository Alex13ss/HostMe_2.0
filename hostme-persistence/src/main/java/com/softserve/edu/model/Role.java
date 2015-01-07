package com.softserve.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "ROLES", uniqueConstraints = { @UniqueConstraint(columnNames = { "role_id" }) })
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "role_id", unique = true, nullable = false)
	private Integer roleId;

	@Column(name = "role", length = 10)
	private String role;

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	

}
