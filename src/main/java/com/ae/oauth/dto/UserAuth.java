package com.ae.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class UserAuth implements Serializable {

	private Long id;

	private String username;

	private String password;

	private Boolean enabled;
	private String name;

	@JsonProperty("last_name")
	private String lastName;

	private String email;

	private List<Role> roles;

	@JsonProperty("intents_counts")
	private Integer intentsCounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getIntentsCounts() {
		return intentsCounts;
	}

	public void setIntentsCounts(Integer intentsCounts) {
		this.intentsCounts = intentsCounts;
	}
}
