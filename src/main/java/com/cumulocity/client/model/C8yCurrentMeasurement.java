// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Current measurement.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yCurrentMeasurement {

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue current;

	public C8yMeasurementValue getCurrent() {
		return current;
	}
	
	public void setCurrent(final C8yMeasurementValue current) {
		this.current = current;
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
		if (r != null && r instanceof C8yCurrentMeasurement) {
			C8yCurrentMeasurement comparer = (C8yCurrentMeasurement) r;
			if (comparer.getCurrent().equals(this.getCurrent())) {
				return true;
			}
		}
		return false;
	}
}
