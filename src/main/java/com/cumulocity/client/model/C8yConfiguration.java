// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Text configuration fragment that allows you to configure parameters and initial settings of your device.</p>
 * <p>In the inventory, <code>c8y_Configuration</code> represents the currently active configuration on the device. As part of an operation, <code>c8y_Configuration</code> requests the device to switch the transmitted configuration to the currently active one. To enable configuration through the user interface, add <code>c8y_Configuration</code> to the list of supported operations.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yConfiguration {

	/**
	 * <p>A text in a device-specific format, representing the configuration of the device.</p>
	 */
	private String config;

	public String getConfig() {
		return config;
	}
	
	public void setConfig(final String config) {
		this.config = config;
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
		if (r != null && r instanceof C8yConfiguration) {
			C8yConfiguration comparer = (C8yConfiguration) r;
			if (String.valueOf(comparer.getConfig()).equals(String.valueOf(this.getConfig()))) {
				return true;
			}
		}
		return false;
	}
}
