// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Device capability to manage device profiles. Device profiles represent a combination of a firmware version, one or multiple software packages and one or multiple configuration files which can be deployed on a device.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yProfile {

	/**
	 * <p>The name of the profile.</p>
	 */
	private String profileName;

	/**
	 * <p>The ID of the profile.</p>
	 */
	private String profileId;

	/**
	 * <p>Indicates whether the profile has been executed.</p>
	 */
	private boolean profileExecuted;

	public String getProfileName() {
		return profileName;
	}
	
	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public String getProfileId() {
		return profileId;
	}
	
	public void setProfileId(final String profileId) {
		this.profileId = profileId;
	}

	public boolean getProfileExecuted() {
		return profileExecuted;
	}
	
	public void setProfileExecuted(final boolean profileExecuted) {
		this.profileExecuted = profileExecuted;
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
		if (r != null && r instanceof C8yProfile) {
			C8yProfile comparer = (C8yProfile) r;
			if (String.valueOf(comparer.getProfileName()).equals(String.valueOf(this.getProfileName())) && String.valueOf(comparer.getProfileId()).equals(String.valueOf(this.getProfileId())) && Boolean.valueOf(comparer.getProfileExecuted()).equals(Boolean.valueOf(this.getProfileExecuted()))) {
				return true;
			}
		}
		return false;
	}
}
