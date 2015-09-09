package com.spring.rest.dto;

import java.util.Set;

import com.spring.domain.Role;

public class UserDetailDTO {

	public UserDetailDTO() {
		
	}
	
	public UserDetailDTO(Long userId, String username, String password, Boolean active, long deleted, Set<Role> roles) {
		this.userId = userId; 
		this.username = username;
		this.password = password;
		this.active = active;
		this.deleted = deleted;
		this.roles = roles;
	}
	
	private Long userId;
	
	private String username;
	
	private String password;
	
	private Boolean active;
	
	private long deleted;
	
	private Set<Role> roles;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public long getDeleted() {
		return deleted;
	}

	public void setDeleted(long deleted) {
		this.deleted = deleted;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
