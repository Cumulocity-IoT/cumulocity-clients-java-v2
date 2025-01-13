// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SupportedMeasurements {

	/**
	 * <p>An array containing all supported measurements of the specified managed object.</p>
	 */
	@JsonProperty(value = "c8y_SupportedMeasurements")
	private String[] c8ySupportedMeasurements;

	public String[] getC8ySupportedMeasurements() {
		return c8ySupportedMeasurements;
	}
	
	public void setC8ySupportedMeasurements(final String[] c8ySupportedMeasurements) {
		this.c8ySupportedMeasurements = c8ySupportedMeasurements;
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
		if (r != null && r instanceof SupportedMeasurements) {
			SupportedMeasurements comparer = (SupportedMeasurements) r;
			if (comparer.getC8ySupportedMeasurements().equals(this.getC8ySupportedMeasurements())) {
				return true;
			}
		}
		return false;
	}
}
