// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The read only fragment which contains the latest measurements series values reported by the device.</p>
 * <blockquote>
 * <p><strong>������ Feature Preview:</strong> The feature is part of the Latest Measurement feature which is still under public feature preview.</p>
 * </blockquote>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LatestMeasurementValue {

	/**
	 * <p>The unit of the measurement series.</p>
	 */
	private String unit;

	/**
	 * <p>The time of the measurement series.</p>
	 */
	private String time;

	/**
	 * <p>The value of the individual measurement.</p>
	 */
	private Number value;

	public LatestMeasurementValue() {
	}

	public LatestMeasurementValue(final Number value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}
	
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(final String time) {
		this.time = time;
	}

	public Number getValue() {
		return value;
	}
	
	public void setValue(final Number value) {
		this.value = value;
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
		if (r != null && r instanceof LatestMeasurementValue) {
			LatestMeasurementValue comparer = (LatestMeasurementValue) r;
			if (String.valueOf(comparer.getUnit()).equals(String.valueOf(this.getUnit())) && String.valueOf(comparer.getTime()).equals(String.valueOf(this.getTime())) && comparer.getValue().equals(this.getValue())) {
				return true;
			}
		}
		return false;
	}
}
