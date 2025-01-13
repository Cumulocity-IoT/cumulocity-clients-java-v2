// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>There are three main measurements of moisture; absolute, relative and specific.</p>
 * <p>Absolute moisture is the absolute water content of a substance. Relative moisture, expressed as a percentage, measures the current absolute moisture relative to the maximum for that temperature. Specific humidity is a ratio of the water vapour content of the mixture to the total substance content on a mass basis.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yMoistureMeasurement {

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue moisture;

	public C8yMeasurementValue getMoisture() {
		return moisture;
	}
	
	public void setMoisture(final C8yMeasurementValue moisture) {
		this.moisture = moisture;
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
		if (r != null && r instanceof C8yMoistureMeasurement) {
			C8yMoistureMeasurement comparer = (C8yMoistureMeasurement) r;
			if (comparer.getMoisture().equals(this.getMoisture())) {
				return true;
			}
		}
		return false;
	}
}
