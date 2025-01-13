// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A type of measurement fragment.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8ySteam {

	@JsonProperty(value = "Temperature")
	private Temperature temperature;

	public Temperature getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final Temperature temperature) {
		this.temperature = temperature;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Temperature {
	
		/**
		 * <p>The unit of the measurement.</p>
		 */
		private String unit;
	
		/**
		 * <p>The value of the individual measurement.</p>
		 */
		private Number value;
	
		public Temperature() {
		}
	
		public Temperature(final Number value) {
			this.value = value;
		}
	
		public String getUnit() {
			return unit;
		}
		
		public void setUnit(final String unit) {
			this.unit = unit;
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
			if (r != null && r instanceof Temperature) {
				Temperature comparer = (Temperature) r;
				if (String.valueOf(comparer.getUnit()).equals(String.valueOf(this.getUnit())) && comparer.getValue().equals(this.getValue())) {
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
		if (r != null && r instanceof C8ySteam) {
			C8ySteam comparer = (C8ySteam) r;
			if (comparer.getTemperature().equals(this.getTemperature())) {
				return true;
			}
		}
		return false;
	}
}
