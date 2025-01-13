// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A measurement is a value with a unit.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yMeasurementValue {

	private Number value;

	private String unit;

	public Number getValue() {
		return value;
	}
	
	public void setValue(final Number value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}
	
	public void setUnit(final String unit) {
		this.unit = unit;
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
		if (r != null && r instanceof C8yMeasurementValue) {
			C8yMeasurementValue comparer = (C8yMeasurementValue) r;
			if (comparer.getValue().equals(this.getValue()) && String.valueOf(comparer.getUnit()).equals(String.valueOf(this.getUnit()))) {
				return true;
			}
		}
		return false;
	}
}
