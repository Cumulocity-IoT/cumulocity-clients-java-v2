// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Light is measured with two main alternative sets of units.</p>
 * <p>Radiometry consists of measurements of light power at all wavelengths, while photometry measures light with wavelength weighted with respect to a standardized model of human brightness perception. Photometry is useful, for example, to quantify illumination (lighting) intended for human use.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yLightMeasurement {

	/**
	 * <p>A measurement is a value with a unit.</p>
	 */
	private C8yMeasurementValue e;

	public C8yMeasurementValue getE() {
		return e;
	}
	
	public void setE(final C8yMeasurementValue e) {
		this.e = e;
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
		if (r != null && r instanceof C8yLightMeasurement) {
			C8yLightMeasurement comparer = (C8yLightMeasurement) r;
			if (comparer.getE().equals(this.getE())) {
				return true;
			}
		}
		return false;
	}
}
