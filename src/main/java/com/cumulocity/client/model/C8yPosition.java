// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Reports the geographical location of an asset in terms of latitude, longitude and altitude.</p>
 * <p>Altitude is given in meters. To report the current location of an asset or a device, <code>c8y_Position</code> is added to the managed object representing the asset or device. To trace the position of an asset or a device, <code>c8y_Position</code> is sent as part of an event of type <code>c8y_LocationUpdate</code>.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yPosition {

	/**
	 * <p>In meters.</p>
	 */
	private Number alt;

	private Number lng;

	private Number lat;

	/**
	 * <p>Describes in which protocol the tracking context of a positioning report was sent.</p>
	 */
	private String trackingProtocol;

	/**
	 * <p>Describes why the tracking context of a positioning report was sent.</p>
	 */
	private String reportReason;

	public Number getAlt() {
		return alt;
	}
	
	public void setAlt(final Number alt) {
		this.alt = alt;
	}

	public Number getLng() {
		return lng;
	}
	
	public void setLng(final Number lng) {
		this.lng = lng;
	}

	public Number getLat() {
		return lat;
	}
	
	public void setLat(final Number lat) {
		this.lat = lat;
	}

	public String getTrackingProtocol() {
		return trackingProtocol;
	}
	
	public void setTrackingProtocol(final String trackingProtocol) {
		this.trackingProtocol = trackingProtocol;
	}

	public String getReportReason() {
		return reportReason;
	}
	
	public void setReportReason(final String reportReason) {
		this.reportReason = reportReason;
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
		if (r != null && r instanceof C8yPosition) {
			C8yPosition comparer = (C8yPosition) r;
			if (comparer.getAlt().equals(this.getAlt()) && comparer.getLng().equals(this.getLng()) && comparer.getLat().equals(this.getLat()) && String.valueOf(comparer.getTrackingProtocol()).equals(String.valueOf(this.getTrackingProtocol())) && String.valueOf(comparer.getReportReason()).equals(String.valueOf(this.getReportReason()))) {
				return true;
			}
		}
		return false;
	}
}
