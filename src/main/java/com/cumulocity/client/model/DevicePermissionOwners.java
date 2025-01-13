// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A list of device permissions.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DevicePermissionOwners {

	private User[] users;

	private Group[] groups;

	public User[] getUsers() {
		return users;
	}
	
	public void setUsers(final User[] users) {
		this.users = users;
	}

	public Group[] getGroups() {
		return groups;
	}
	
	public void setGroups(final Group[] groups) {
		this.groups = groups;
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
		if (r != null && r instanceof DevicePermissionOwners) {
			DevicePermissionOwners comparer = (DevicePermissionOwners) r;
			if (comparer.getUsers().equals(this.getUsers()) && comparer.getGroups().equals(this.getGroups())) {
				return true;
			}
		}
		return false;
	}
}
