// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Contains information on a device's firmware. In the inventory, <code>c8y_Firmware</code> represents the currently installed firmware on the device. As part of an operation, <code>c8y_Firmware</code> requests the device to install the indicated firmware. To enable firmware installation through the user interface, add <code>c8y_Firmware</code> to the list of supported operations.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yFirmware {

	/**
	 * <p>Name of the firmware.</p>
	 */
	private String name;

	/**
	 * <p>A version identifier of the firmware.</p>
	 */
	private String version;

	/**
	 * <p>A URI linking to the location to download the firmware from.</p>
	 */
	private String url;

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(final String url) {
		this.url = url;
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
		if (r != null && r instanceof C8yFirmware) {
			C8yFirmware comparer = (C8yFirmware) r;
			if (String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion())) && String.valueOf(comparer.getUrl()).equals(String.valueOf(this.getUrl()))) {
				return true;
			}
		}
		return false;
	}
}
