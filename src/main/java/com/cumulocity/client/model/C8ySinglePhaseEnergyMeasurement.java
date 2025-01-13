// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Measurement of the single phase energy meter.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8ySinglePhaseEnergyMeasurement {

	private Map<String, C8yMeasurementValue> additionalProperties;

	public Map<String, C8yMeasurementValue> getAdditionalProperties() {
		return additionalProperties;
	}
	
	public void setAdditionalProperties(final Map<String, C8yMeasurementValue> additionalProperties) {
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
		if (r != null && r instanceof C8ySinglePhaseEnergyMeasurement) {
			C8ySinglePhaseEnergyMeasurement comparer = (C8ySinglePhaseEnergyMeasurement) r;
			if (comparer.getAdditionalProperties().equals(this.getAdditionalProperties())) {
				return true;
			}
		}
		return false;
	}
}
