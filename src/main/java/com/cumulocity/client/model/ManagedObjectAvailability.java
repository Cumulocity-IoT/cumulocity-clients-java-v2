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
public class ManagedObjectAvailability {

	/**
	 * <p>Identifier of the target device.</p>
	 */
	private String deviceId;

	/**
	 * <p>The identifier used in the external system that Cumulocity IoT interfaces with.</p>
	 */
	private String externalId;

	/**
	 * <p>The time when the device sent the last message to Cumulocity IoT.</p>
	 */
	private String lastMessage;

	/**
	 * <p>Required interval of monitored device</p>
	 */
	private int interval;

	/**
	 * <p>The current status of availability, one of <code>AVAILABLE</code>, <code>UNAVAILABLE</code>, <code>MAINTENANCE</code>.</p>
	 */
	private C8yAvailabilityDataStatus dataStatus;

	/**
	 * <p>The current status of connection, one of <code>CONNECTED</code>, <code>DISCONNECTED</code>, <code>MAINTENANCE</code>.</p>
	 */
	private C8yAvailabilityConnectionStatus connectionStatus;

	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public String getLastMessage() {
		return lastMessage;
	}
	
	public void setLastMessage(final String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public int getInterval() {
		return interval;
	}
	
	public void setInterval(final int interval) {
		this.interval = interval;
	}

	public C8yAvailabilityDataStatus getDataStatus() {
		return dataStatus;
	}
	
	public void setDataStatus(final C8yAvailabilityDataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}

	public C8yAvailabilityConnectionStatus getConnectionStatus() {
		return connectionStatus;
	}
	
	public void setConnectionStatus(final C8yAvailabilityConnectionStatus connectionStatus) {
		this.connectionStatus = connectionStatus;
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
		if (r != null && r instanceof ManagedObjectAvailability) {
			ManagedObjectAvailability comparer = (ManagedObjectAvailability) r;
			if (String.valueOf(comparer.getDeviceId()).equals(String.valueOf(this.getDeviceId())) && String.valueOf(comparer.getExternalId()).equals(String.valueOf(this.getExternalId())) && String.valueOf(comparer.getLastMessage()).equals(String.valueOf(this.getLastMessage())) && Integer.valueOf(comparer.getInterval()).equals(Integer.valueOf(this.getInterval())) && comparer.getDataStatus().equals(this.getDataStatus()) && comparer.getConnectionStatus().equals(this.getConnectionStatus())) {
				return true;
			}
		}
		return false;
	}
}
