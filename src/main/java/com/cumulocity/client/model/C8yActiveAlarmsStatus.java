// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The number of currently active and acknowledged alarms is stored in this fragment.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yActiveAlarmsStatus {

	private int critical;

	private int major;

	private int minor;

	private int warning;

	public int getCritical() {
		return critical;
	}
	
	public void setCritical(final int critical) {
		this.critical = critical;
	}

	public int getMajor() {
		return major;
	}
	
	public void setMajor(final int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}
	
	public void setMinor(final int minor) {
		this.minor = minor;
	}

	public int getWarning() {
		return warning;
	}
	
	public void setWarning(final int warning) {
		this.warning = warning;
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
		if (r != null && r instanceof C8yActiveAlarmsStatus) {
			C8yActiveAlarmsStatus comparer = (C8yActiveAlarmsStatus) r;
			if (Integer.valueOf(comparer.getCritical()).equals(Integer.valueOf(this.getCritical())) && Integer.valueOf(comparer.getMajor()).equals(Integer.valueOf(this.getMajor())) && Integer.valueOf(comparer.getMinor()).equals(Integer.valueOf(this.getMinor())) && Integer.valueOf(comparer.getWarning()).equals(Integer.valueOf(this.getWarning()))) {
				return true;
			}
		}
		return false;
	}
}
