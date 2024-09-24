// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TenantFeatureToggleValue {

	/**
	 * <p>Identifier of a tenant this feature toggle value is for.</p>
	 */
	private String tenantId;

	/**
	 * <p>Current value of the feature toggle marking whether the feature is active or not.</p>
	 */
	private boolean active;

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public boolean getActive() {
		return active;
	}
	
	public void setActive(final boolean active) {
		this.active = active;
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
		if (r != null && r instanceof TenantFeatureToggleValue) {
			TenantFeatureToggleValue comparer = (TenantFeatureToggleValue) r;
			if (String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && Boolean.valueOf(comparer.getActive()).equals(Boolean.valueOf(this.getActive()))) {
				return true;
			}
		}
		return false;
	}
}
