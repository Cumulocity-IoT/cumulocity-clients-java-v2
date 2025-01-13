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
public class SupportedSeries {

	/**
	 * <p>An array containing all supported measurement series of the specified device.</p>
	 */
	@JsonProperty(value = "c8y_SupportedSeries")
	private String[] c8ySupportedSeries;

	public String[] getC8ySupportedSeries() {
		return c8ySupportedSeries;
	}
	
	public void setC8ySupportedSeries(final String[] c8ySupportedSeries) {
		this.c8ySupportedSeries = c8ySupportedSeries;
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
		if (r != null && r instanceof SupportedSeries) {
			SupportedSeries comparer = (SupportedSeries) r;
			if (comparer.getC8ySupportedSeries().equals(this.getC8ySupportedSeries())) {
				return true;
			}
		}
		return false;
	}
}
