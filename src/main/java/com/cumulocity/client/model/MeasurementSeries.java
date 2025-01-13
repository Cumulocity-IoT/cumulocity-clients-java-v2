// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MeasurementSeries {

	/**
	 * <p>Each property contained here is a date taken from the measurement and it contains an array of objects specifying <code>min</code> and <code>max</code> pair of values. Each pair corresponds to a single series object in the <code>series</code> array. If there is no aggregation used, <code>min</code> is equal to <code>max</code> in every pair.</p>
	 */
	private Values values;

	/**
	 * <p>An array containing the type of series and units.</p>
	 */
	private MeasurementFragmentSeries[] series;

	/**
	 * <p>If there were more than 5000 values, the final result was truncated.</p>
	 */
	private boolean truncated;

	public Values getValues() {
		return values;
	}
	
	public void setValues(final Values values) {
		this.values = values;
	}

	public MeasurementFragmentSeries[] getSeries() {
		return series;
	}
	
	public void setSeries(final MeasurementFragmentSeries[] series) {
		this.series = series;
	}

	public boolean getTruncated() {
		return truncated;
	}
	
	public void setTruncated(final boolean truncated) {
		this.truncated = truncated;
	}

	/**
	 * <p>Each property contained here is a date taken from the measurement and it contains an array of objects specifying <code>min</code> and <code>max</code> pair of values. Each pair corresponds to a single series object in the <code>series</code> array. If there is no aggregation used, <code>min</code> is equal to <code>max</code> in every pair.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Values {
	
		private Map<String, MeasurementSeriesValue[]> additionalProperties;
	
		public Map<String, MeasurementSeriesValue[]> getAdditionalProperties() {
			return additionalProperties;
		}
		
		public void setAdditionalProperties(final Map<String, MeasurementSeriesValue[]> additionalProperties) {
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
			if (r != null && r instanceof Values) {
				Values comparer = (Values) r;
				if (comparer.getAdditionalProperties().equals(this.getAdditionalProperties())) {
					return true;
				}
			}
			return false;
		}
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
		if (r != null && r instanceof MeasurementSeries) {
			MeasurementSeries comparer = (MeasurementSeries) r;
			if (comparer.getValues().equals(this.getValues()) && comparer.getSeries().equals(this.getSeries()) && Boolean.valueOf(comparer.getTruncated()).equals(Boolean.valueOf(this.getTruncated()))) {
				return true;
			}
		}
		return false;
	}
}
