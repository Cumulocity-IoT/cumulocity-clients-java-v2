// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>In order to send commands as text messages to devices, the devices must be put into SMS mode. To indicate that it supports SMS mode, a device needs to add the fragment <code>c8y_CommunicationMode</code> with a mode property of <code>SMS</code>.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yCommunicationMode {

	private String mode;

	public String getMode() {
		return mode;
	}
	
	public void setMode(final String mode) {
		this.mode = mode;
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
		if (r != null && r instanceof C8yCommunicationMode) {
			C8yCommunicationMode comparer = (C8yCommunicationMode) r;
			if (String.valueOf(comparer.getMode()).equals(String.valueOf(this.getMode()))) {
				return true;
			}
		}
		return false;
	}
}
