// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>The current status of connection, one of <code>CONNECTED</code>, <code>DISCONNECTED</code>, <code>MAINTENANCE</code>.</p>
 */
public enum C8yAvailabilityConnectionStatus {
	@JsonProperty("CONNECTED")
	CONNECTED("CONNECTED"),
	@JsonProperty("DISCONNECTED")
	DISCONNECTED("DISCONNECTED"),
	@JsonProperty("MAINTENANCE")
	MAINTENANCE("MAINTENANCE");

	private String value;

	C8yAvailabilityConnectionStatus(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
