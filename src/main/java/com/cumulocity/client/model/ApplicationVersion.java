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
public class ApplicationVersion {

	/**
	 * <p>Unique identifier of the version.</p>
	 */
	private String version;

	/**
	 * <p>Unique identifier of the binary file assigned to the version.</p>
	 */
	private String binaryId;

	/**
	 * <p>Tag assigned to the version. Version tags must be unique across all versions and version fields of application versions.</p>
	 */
	private String[] tag;

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	public String getBinaryId() {
		return binaryId;
	}
	
	public void setBinaryId(final String binaryId) {
		this.binaryId = binaryId;
	}

	public String[] getTag() {
		return tag;
	}
	
	public void setTag(final String[] tag) {
		this.tag = tag;
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
		if (r != null && r instanceof ApplicationVersion) {
			ApplicationVersion comparer = (ApplicationVersion) r;
			if (String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion())) && String.valueOf(comparer.getBinaryId()).equals(String.valueOf(this.getBinaryId())) && comparer.getTag().equals(this.getTag())) {
				return true;
			}
		}
		return false;
	}
}
