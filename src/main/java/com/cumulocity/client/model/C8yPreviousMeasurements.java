// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The read only fragment which contains the previous to latest measurements reported by the device.The returned optionally only if the query parameter <code>withLatestValues=true</code> is used.</p>
 * <blockquote>
 * <p><strong>⚠️ Feature Preview:</strong> The feature is part of the Latest Measurement feature which is still under public feature preview.</p>
 * </blockquote>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yPreviousMeasurements {

	private Map<String, PreviousMeasurementFragment> additionalProperties;

	public Map<String, PreviousMeasurementFragment> getAdditionalProperties() {
		return additionalProperties;
	}
	
	public void setAdditionalProperties(final Map<String, PreviousMeasurementFragment> additionalProperties) {
		this.additionalProperties = additionalProperties;
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
		if (r != null && r instanceof C8yPreviousMeasurements) {
			C8yPreviousMeasurements comparer = (C8yPreviousMeasurements) r;
			if (comparer.getAdditionalProperties().equals(this.getAdditionalProperties())) {
				return true;
			}
		}
		return false;
	}
}
