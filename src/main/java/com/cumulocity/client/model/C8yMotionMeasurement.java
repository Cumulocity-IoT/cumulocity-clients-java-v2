// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Measurement of the motion sensor.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yMotionMeasurement {

	/**
	 * <p>Boolean value indicating if motion has been detected (non-zero value) or not (zero value).</p>
	 */
	private MotionDetected motionDetected;

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue speed;

	public MotionDetected getMotionDetected() {
		return motionDetected;
	}
	
	public void setMotionDetected(final MotionDetected motionDetected) {
		this.motionDetected = motionDetected;
	}

	public C8yMeasurementValue getSpeed() {
		return speed;
	}
	
	public void setSpeed(final C8yMeasurementValue speed) {
		this.speed = speed;
	}

	/**
	 * <p>Boolean value indicating if motion has been detected (non-zero value) or not (zero value).</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class MotionDetected {
	
		private Number value;
	
		private String type;
	
		public Number getValue() {
			return value;
		}
		
		public void setValue(final Number value) {
			this.value = value;
		}
	
		public String getType() {
			return type;
		}
		
		public void setType(final String type) {
			this.type = type;
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
			if (r != null && r instanceof MotionDetected) {
				MotionDetected comparer = (MotionDetected) r;
				if (comparer.getValue().equals(this.getValue()) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
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
		if (r != null && r instanceof C8yMotionMeasurement) {
			C8yMotionMeasurement comparer = (C8yMotionMeasurement) r;
			if (comparer.getMotionDetected().equals(this.getMotionDetected()) && comparer.getSpeed().equals(this.getSpeed())) {
				return true;
			}
		}
		return false;
	}
}
