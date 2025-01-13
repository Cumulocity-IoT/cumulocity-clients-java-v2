// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>There are three main measurements of humidity; absolute, relative and specific.</p>
 * <p>Absolute humidity is the water content of air. Relative humidity, expressed as a percentage, measures the current absolute humidity relative to the maximum for that temperature. Specific humidity is a ratio of the water vapour content of the mixture to the total air content on a mass basis.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yHumidityMeasurement {

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue h;

	public C8yMeasurementValue getH() {
		return h;
	}
	
	public void setH(final C8yMeasurementValue h) {
		this.h = h;
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
		if (r != null && r instanceof C8yHumidityMeasurement) {
			C8yHumidityMeasurement comparer = (C8yHumidityMeasurement) r;
			if (comparer.getH().equals(this.getH())) {
				return true;
			}
		}
		return false;
	}
}
