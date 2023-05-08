// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The availability information computed by Cumulocity IoT is stored in fragments <code>c8y_Availability</code> and <code>c8y_Connection</code> of the device.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yConnection {

	/**
	 * <p>The current status, one of <code>AVAILABLE</code>, <code>CONNECTED</code>, <code>MAINTENANCE</code>, <code>DISCONNECTED</code>.</p>
	 */
	private C8yAvailabilityStatus status;

	public C8yAvailabilityStatus getStatus() {
		return status;
	}
	
	public void setStatus(final C8yAvailabilityStatus status) {
		this.status = status;
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
		if (r != null && r instanceof C8yConnection) {
			C8yConnection comparer = (C8yConnection) r;
			if (comparer.getStatus().equals(this.getStatus())) {
				return true;
			}
		}
		return false;
	}
}
