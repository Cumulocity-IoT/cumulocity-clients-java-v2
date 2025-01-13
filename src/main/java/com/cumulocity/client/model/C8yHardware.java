// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Contains basic hardware information for a device, such as make and serial number. Often, the hardware serial number is printed on the board of the device or on an asset tag on the device to uniquely identify the device within all devices of the same make.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yHardware {

	/**
	 * <p>A text identifier of the device's hardware model.</p>
	 */
	private String model;

	/**
	 * <p>A text identifier of the hardware revision.</p>
	 */
	private String revision;

	/**
	 * <p>The hardware serial number of the device.</p>
	 */
	private String serialNumber;

	public String getModel() {
		return model;
	}
	
	public void setModel(final String model) {
		this.model = model;
	}

	public String getRevision() {
		return revision;
	}
	
	public void setRevision(final String revision) {
		this.revision = revision;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
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
		if (r != null && r instanceof C8yHardware) {
			C8yHardware comparer = (C8yHardware) r;
			if (String.valueOf(comparer.getModel()).equals(String.valueOf(this.getModel())) && String.valueOf(comparer.getRevision()).equals(String.valueOf(this.getRevision())) && String.valueOf(comparer.getSerialNumber()).equals(String.valueOf(this.getSerialNumber()))) {
				return true;
			}
		}
		return false;
	}
}
