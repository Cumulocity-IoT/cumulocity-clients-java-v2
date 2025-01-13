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
public class ApplicationUserCollection {

	/**
	 * <p>A list of users who are subscribed to the current application.</p>
	 */
	private Users[] users;

	public Users[] getUsers() {
		return users;
	}
	
	public void setUsers(final Users[] users) {
		this.users = users;
	}

	/**
	 * <p>A user who is subscribed to the current application.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Users {
	
		/**
		 * <p>The username.</p>
		 */
		private String name;
	
		/**
		 * <p>The user password.</p>
		 */
		private String password;
	
		/**
		 * <p>The user tenant.</p>
		 */
		private String tenant;
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
		}
	
		public String getPassword() {
			return password;
		}
		
		public void setPassword(final String password) {
			this.password = password;
		}
	
		public String getTenant() {
			return tenant;
		}
		
		public void setTenant(final String tenant) {
			this.tenant = tenant;
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
				if (String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getTenant()).equals(String.valueOf(this.getTenant()))) {
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
		if (r != null && r instanceof ApplicationUserCollection) {
			ApplicationUserCollection comparer = (ApplicationUserCollection) r;
			if (comparer.getUsers().equals(this.getUsers())) {
				return true;
			}
		}
		return false;
	}
}
