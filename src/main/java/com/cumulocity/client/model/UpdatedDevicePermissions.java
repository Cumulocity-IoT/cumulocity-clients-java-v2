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
public class UpdatedDevicePermissions {

	private Users[] users;

	private Groups[] groups;

	public Users[] getUsers() {
		return users;
	}
	
	public void setUsers(final Users[] users) {
		this.users = users;
	}

	public Groups[] getGroups() {
		return groups;
	}
	
	public void setGroups(final Groups[] groups) {
		this.groups = groups;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Users {
	
		private String userName;
	
		/**
		 * <p>An object with a list of the user's device permissions.</p>
		 */
		@Deprecated
		private DevicePermissions devicePermissions;
	
		public String getUserName() {
			return userName;
		}
		
		public void setUserName(final String userName) {
			this.userName = userName;
		}
	
		public DevicePermissions getDevicePermissions() {
			return devicePermissions;
		}
		
		public void setDevicePermissions(final DevicePermissions devicePermissions) {
			this.devicePermissions = devicePermissions;
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
			if (r != null && r instanceof Users) {
				Users comparer = (Users) r;
				if (String.valueOf(comparer.getUserName()).equals(String.valueOf(this.getUserName())) && comparer.getDevicePermissions().equals(this.getDevicePermissions())) {
					return true;
				}
			}
			return false;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Groups {
	
		private String id;
	
		/**
		 * <p>An object with a list of the user's device permissions.</p>
		 */
		@Deprecated
		private DevicePermissions devicePermissions;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
		}
	
		public DevicePermissions getDevicePermissions() {
			return devicePermissions;
		}
		
		public void setDevicePermissions(final DevicePermissions devicePermissions) {
			this.devicePermissions = devicePermissions;
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
			if (r != null && r instanceof Groups) {
				Groups comparer = (Groups) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && comparer.getDevicePermissions().equals(this.getDevicePermissions())) {
					return true;
				}
			}
			return false;
		}
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
		if (r != null && r instanceof UpdatedDevicePermissions) {
			UpdatedDevicePermissions comparer = (UpdatedDevicePermissions) r;
			if (comparer.getUsers().equals(this.getUsers()) && comparer.getGroups().equals(this.getGroups())) {
				return true;
			}
		}
		return false;
	}
}
