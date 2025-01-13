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
public class ManagedObjectUser {

	/**
	 * <p>Specifies if the device's owner is enabled or not.</p>
	 */
	private boolean enabled;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The username of the device's owner.</p>
	 */
	private String userName;

	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(final String userName) {
		this.userName = userName;
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
		if (r != null && r instanceof ManagedObjectUser) {
			ManagedObjectUser comparer = (ManagedObjectUser) r;
			if (Boolean.valueOf(comparer.getEnabled()).equals(Boolean.valueOf(this.getEnabled())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getUserName()).equals(String.valueOf(this.getUserName()))) {
				return true;
			}
		}
		return false;
	}
}
