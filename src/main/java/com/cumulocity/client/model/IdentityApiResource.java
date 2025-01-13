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
public class IdentityApiResource {

	/**
	 * <p>Single external ID, represented by the type and the value of the external ID.</p>
	 */
	private String externalId;

	/**
	 * <p>Represents a collection of external IDs for a specified global ID.</p>
	 */
	private String externalIdsOfGlobalId;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public String getExternalIdsOfGlobalId() {
		return externalIdsOfGlobalId;
	}
	
	public void setExternalIdsOfGlobalId(final String externalIdsOfGlobalId) {
		this.externalIdsOfGlobalId = externalIdsOfGlobalId;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
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
		if (r != null && r instanceof IdentityApiResource) {
			IdentityApiResource comparer = (IdentityApiResource) r;
			if (String.valueOf(comparer.getExternalId()).equals(String.valueOf(this.getExternalId())) && String.valueOf(comparer.getExternalIdsOfGlobalId()).equals(String.valueOf(this.getExternalIdsOfGlobalId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
