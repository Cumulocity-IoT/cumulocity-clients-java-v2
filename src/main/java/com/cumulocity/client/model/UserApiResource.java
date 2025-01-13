// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserApiResource {

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Collection of all users belonging to a given tenant.</p>
	 */
	private String users;

	/**
	 * <p>Reference to a resource of type user.</p>
	 */
	private String userByName;

	/**
	 * <p>Reference to the resource of the logged in user.</p>
	 */
	private String currentUser;

	/**
	 * <p>Collection of all users belonging to a given tenant.</p>
	 */
	private String groups;

	/**
	 * <p>Reference to a resource of type group.</p>
	 */
	private String groupByName;

	/**
	 * <p>Collection of all roles.</p>
	 */
	private String roles;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getUsers() {
		return users;
	}
	
	public void setUsers(final String users) {
		this.users = users;
	}

	public String getUserByName() {
		return userByName;
	}
	
	public void setUserByName(final String userByName) {
		this.userByName = userByName;
	}

	public String getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(final String currentUser) {
		this.currentUser = currentUser;
	}

	public String getGroups() {
		return groups;
	}
	
	public void setGroups(final String groups) {
		this.groups = groups;
	}

	public String getGroupByName() {
		return groupByName;
	}
	
	public void setGroupByName(final String groupByName) {
		this.groupByName = groupByName;
	}

	public String getRoles() {
		return roles;
	}
	
	public void setRoles(final String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof UserApiResource) {
			UserApiResource comparer = (UserApiResource) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getUsers()).equals(String.valueOf(this.getUsers())) && String.valueOf(comparer.getUserByName()).equals(String.valueOf(this.getUserByName())) && String.valueOf(comparer.getCurrentUser()).equals(String.valueOf(this.getCurrentUser())) && String.valueOf(comparer.getGroups()).equals(String.valueOf(this.getGroups())) && String.valueOf(comparer.getGroupByName()).equals(String.valueOf(this.getGroupByName())) && String.valueOf(comparer.getRoles()).equals(String.valueOf(this.getRoles()))) {
				return true;
			}
		}
		return false;
	}
}
