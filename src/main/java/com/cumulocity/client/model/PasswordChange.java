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
public class PasswordChange {

	/**
	 * <p>The current password of the user performing the request.</p>
	 */
	private String currentUserPassword;

	/**
	 * <p>The new password to be set for the user performing the request.</p>
	 */
	private String newPassword;

	public PasswordChange() {
	}

	public PasswordChange(final String currentUserPassword, final String newPassword) {
		this.currentUserPassword = currentUserPassword;
		this.newPassword = newPassword;
	}

	public String getCurrentUserPassword() {
		return currentUserPassword;
	}
	
	public void setCurrentUserPassword(final String currentUserPassword) {
		this.currentUserPassword = currentUserPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
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
		if (r != null && r instanceof PasswordChange) {
			PasswordChange comparer = (PasswordChange) r;
			if (String.valueOf(comparer.getCurrentUserPassword()).equals(String.valueOf(this.getCurrentUserPassword())) && String.valueOf(comparer.getNewPassword()).equals(String.valueOf(this.getNewPassword()))) {
				return true;
			}
		}
		return false;
	}
}
