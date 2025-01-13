// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MeasurementSeriesValue {

	private Number min;

	private Number max;

	public Number getMin() {
		return min;
	}
	
	public void setMin(final Number min) {
		this.min = min;
	}

	public Number getMax() {
		return max;
	}
	
	public void setMax(final Number max) {
		this.max = max;
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
		if (r != null && r instanceof MeasurementSeriesValue) {
			MeasurementSeriesValue comparer = (MeasurementSeriesValue) r;
			if (comparer.getMin().equals(this.getMin()) && comparer.getMax().equals(this.getMax())) {
				return true;
			}
		}
		return false;
	}
}
