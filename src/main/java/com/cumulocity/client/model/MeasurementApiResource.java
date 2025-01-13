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
public class MeasurementApiResource {

	/**
	 * <p>Collection of all measurements</p>
	 */
	private Measurements measurements;

	/**
	 * <p>Read-only collection of all measurements for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.</p>
	 */
	private String measurementsForSource;

	/**
	 * <p>Read-only collection of all measurements of a particular type and a specific source object.</p>
	 */
	private String measurementsForSourceAndType;

	/**
	 * <p>Read-only collection of all measurements of a particular type.</p>
	 */
	private String measurementsForType;

	/**
	 * <p>Read-only collection of all measurements containing a particular fragment type.</p>
	 */
	private String measurementsForValueFragmentType;

	/**
	 * <p>Read-only collection of all measurements for a particular time range.</p>
	 */
	private String measurementsForDate;

	/**
	 * <p>Read-only collection of all measurements for a specific source object in a particular time range.</p>
	 */
	private String measurementsForSourceAndDate;

	/**
	 * <p>Read-only collection of all measurements for a specific fragment type and a particular time range.</p>
	 */
	private String measurementsForDateAndFragmentType;

	/**
	 * <p>Read-only collection of all measurements for a specific source object, particular fragment type and series, and an event type.</p>
	 */
	private String measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public Measurements getMeasurements() {
		return measurements;
	}
	
	public void setMeasurements(final Measurements measurements) {
		this.measurements = measurements;
	}

	public String getMeasurementsForSource() {
		return measurementsForSource;
	}
	
	public void setMeasurementsForSource(final String measurementsForSource) {
		this.measurementsForSource = measurementsForSource;
	}

	public String getMeasurementsForSourceAndType() {
		return measurementsForSourceAndType;
	}
	
	public void setMeasurementsForSourceAndType(final String measurementsForSourceAndType) {
		this.measurementsForSourceAndType = measurementsForSourceAndType;
	}

	public String getMeasurementsForType() {
		return measurementsForType;
	}
	
	public void setMeasurementsForType(final String measurementsForType) {
		this.measurementsForType = measurementsForType;
	}

	public String getMeasurementsForValueFragmentType() {
		return measurementsForValueFragmentType;
	}
	
	public void setMeasurementsForValueFragmentType(final String measurementsForValueFragmentType) {
		this.measurementsForValueFragmentType = measurementsForValueFragmentType;
	}

	public String getMeasurementsForDate() {
		return measurementsForDate;
	}
	
	public void setMeasurementsForDate(final String measurementsForDate) {
		this.measurementsForDate = measurementsForDate;
	}

	public String getMeasurementsForSourceAndDate() {
		return measurementsForSourceAndDate;
	}
	
	public void setMeasurementsForSourceAndDate(final String measurementsForSourceAndDate) {
		this.measurementsForSourceAndDate = measurementsForSourceAndDate;
	}

	public String getMeasurementsForDateAndFragmentType() {
		return measurementsForDateAndFragmentType;
	}
	
	public void setMeasurementsForDateAndFragmentType(final String measurementsForDateAndFragmentType) {
		this.measurementsForDateAndFragmentType = measurementsForDateAndFragmentType;
	}

	public String getMeasurementsForSourceAndValueFragmentTypeAndValueFragmentSeries() {
		return measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries;
	}
	
	public void setMeasurementsForSourceAndValueFragmentTypeAndValueFragmentSeries(final String measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries) {
		this.measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries = measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * <p>Collection of all measurements</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Measurements {
	
		private Measurement[] measurements;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public Measurement[] getMeasurements() {
			return measurements;
		}
		
		public void setMeasurements(final Measurement[] measurements) {
			this.measurements = measurements;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof Measurements) {
				Measurements comparer = (Measurements) r;
				if (comparer.getMeasurements().equals(this.getMeasurements()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
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
		if (r != null && r instanceof MeasurementApiResource) {
			MeasurementApiResource comparer = (MeasurementApiResource) r;
			if (comparer.getMeasurements().equals(this.getMeasurements()) && String.valueOf(comparer.getMeasurementsForSource()).equals(String.valueOf(this.getMeasurementsForSource())) && String.valueOf(comparer.getMeasurementsForSourceAndType()).equals(String.valueOf(this.getMeasurementsForSourceAndType())) && String.valueOf(comparer.getMeasurementsForType()).equals(String.valueOf(this.getMeasurementsForType())) && String.valueOf(comparer.getMeasurementsForValueFragmentType()).equals(String.valueOf(this.getMeasurementsForValueFragmentType())) && String.valueOf(comparer.getMeasurementsForDate()).equals(String.valueOf(this.getMeasurementsForDate())) && String.valueOf(comparer.getMeasurementsForSourceAndDate()).equals(String.valueOf(this.getMeasurementsForSourceAndDate())) && String.valueOf(comparer.getMeasurementsForDateAndFragmentType()).equals(String.valueOf(this.getMeasurementsForDateAndFragmentType())) && String.valueOf(comparer.getMeasurementsForSourceAndValueFragmentTypeAndValueFragmentSeries()).equals(String.valueOf(this.getMeasurementsForSourceAndValueFragmentTypeAndValueFragmentSeries())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
