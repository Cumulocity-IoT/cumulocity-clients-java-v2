// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Statistics of a specific device (identified by an ID).</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DeviceStatistics {

	/**
	 * <p>Sum of measurements, events and alarms created and updated for the specified device.</p>
	 */
	private int count;

	/**
	 * <p>Unique identifier of the device.</p>
	 */
	private String deviceId;

	/**
	 * <p>List of unique identifiers of parents for the corresponding device. Available only with monthly data.</p>
	 */
	private String[] deviceParents;

	/**
	 * <p>Value of the <code>type</code> field from the corresponding device. Available only with monthly data.</p>
	 */
	private String deviceType;

	public int getCount() {
		return count;
	}
	
	public void setCount(final int count) {
		this.count = count;
	}

	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public String[] getDeviceParents() {
		return deviceParents;
	}
	
	public void setDeviceParents(final String[] deviceParents) {
		this.deviceParents = deviceParents;
	}

	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(final String deviceType) {
		this.deviceType = deviceType;
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
		if (r != null && r instanceof DeviceStatistics) {
			DeviceStatistics comparer = (DeviceStatistics) r;
			if (Integer.valueOf(comparer.getCount()).equals(Integer.valueOf(this.getCount())) && String.valueOf(comparer.getDeviceId()).equals(String.valueOf(this.getDeviceId())) && comparer.getDeviceParents().equals(this.getDeviceParents()) && String.valueOf(comparer.getDeviceType()).equals(String.valueOf(this.getDeviceType()))) {
				return true;
			}
		}
		return false;
	}
}
