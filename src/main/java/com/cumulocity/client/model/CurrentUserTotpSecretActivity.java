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
public class CurrentUserTotpSecretActivity {

	/**
	 * <p>Indicates whether the two-factor authentication secret is active.</p>
	 */
	private boolean isActive;

	public CurrentUserTotpSecretActivity() {
	}

	public CurrentUserTotpSecretActivity(final boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(final boolean isActive) {
		this.isActive = isActive;
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
		if (r != null && r instanceof CurrentUserTotpSecretActivity) {
			CurrentUserTotpSecretActivity comparer = (CurrentUserTotpSecretActivity) r;
			if (Boolean.valueOf(comparer.getIsActive()).equals(Boolean.valueOf(this.getIsActive()))) {
				return true;
			}
		}
		return false;
	}
}
