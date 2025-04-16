// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FeatureToggleValue {

	/**
	 * <p>Current value of the feature toggle marking whether the feature is active or not.</p>
	 */
	private boolean active;

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
		if (r != null && r instanceof FeatureToggleValue) {
			FeatureToggleValue comparer = (FeatureToggleValue) r;
			if (Boolean.valueOf(comparer.getActive()).equals(Boolean.valueOf(this.getActive()))) {
				return true;
			}
		}
		return false;
	}
}
