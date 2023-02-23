// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * To carry out interactive sessions with a device, use the `c8y_Command` fragment. If this fragment is in the list of supported operations for a device, a tab `Shell` will be shown. Using the `Shell` tab, the user can send commands in an arbitrary, device-specific syntax to the device. The command is sent to the device in a property `text`.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yCommand {

	/**
	 * The command sent to the device.
	 */
	private String type;

	/**
	 * To communicate the results of a particular command, the device adds a property `result`.
	 */
	private String result;

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(final String result) {
		this.result = result;
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
		if (r != null && r instanceof C8yCommand) {
			C8yCommand comparer = (C8yCommand) r;
			if (String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && String.valueOf(comparer.getResult()).equals(String.valueOf(this.getResult()))) {
				return true;
			}
		}
		return false;
	}
}
