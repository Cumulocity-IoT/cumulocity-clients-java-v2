// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A voltage sensor measures the voltage difference between two points in an electric circuit.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yVoltageMeasurement {

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue voltage;

	public C8yMeasurementValue getVoltage() {
		return voltage;
	}
	
	public void setVoltage(final C8yMeasurementValue voltage) {
		this.voltage = voltage;
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
		if (r != null && r instanceof C8yVoltageMeasurement) {
			C8yVoltageMeasurement comparer = (C8yVoltageMeasurement) r;
			if (comparer.getVoltage().equals(this.getVoltage())) {
				return true;
			}
		}
		return false;
	}
}
