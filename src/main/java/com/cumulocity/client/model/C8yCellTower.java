// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Detailed information about a neighbouring cell tower.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yCellTower {

	/**
	 * <p>The radio type of this cell tower. Can also be put directly in root JSON element if all cellTowers have same radioType.</p>
	 */
	private String radioType;

	/**
	 * <p>The Mobile Country Code (MCC).</p>
	 */
	private Number mobileCountryCode;

	/**
	 * <p>The Mobile Network Code (MNC) for GSM, WCDMA and LTE. The SystemID (sid) for CDMA.</p>
	 */
	private Number mobileNetworkCode;

	/**
	 * <p>The Location Area Code (LAC) for GSM, WCDMA and LTE. The Network ID for CDMA.</p>
	 */
	private Number locationAreaCode;

	/**
	 * <p>The Cell ID (CID) for GSM, WCDMA and LTE. The base station ID for CDMA.</p>
	 */
	private Number cellId;

	/**
	 * <p>The timing advance value for this cell tower when available.</p>
	 */
	private Number timingAdvance;

	/**
	 * <p>The signal strength for this cell tower in dBm.</p>
	 */
	private Number signalStrength;

	/**
	 * <p>The primary scrambling code for WCDMA and physical CellId for LTE.</p>
	 */
	private Number primaryScramblingCode;

	/**
	 * <p>Specify with 0/1 if the cell is serving or not. If not specified, the first cell is assumed to be serving.</p>
	 */
	private Number serving;

	public C8yCellTower() {
	}

	public C8yCellTower(final Number mobileCountryCode, final Number mobileNetworkCode, final Number locationAreaCode, final Number cellId) {
		this.mobileCountryCode = mobileCountryCode;
		this.mobileNetworkCode = mobileNetworkCode;
		this.locationAreaCode = locationAreaCode;
		this.cellId = cellId;
	}

	public String getRadioType() {
		return radioType;
	}
	
	public void setRadioType(final String radioType) {
		this.radioType = radioType;
	}

	public Number getMobileCountryCode() {
		return mobileCountryCode;
	}
	
	public void setMobileCountryCode(final Number mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public Number getMobileNetworkCode() {
		return mobileNetworkCode;
	}
	
	public void setMobileNetworkCode(final Number mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}

	public Number getLocationAreaCode() {
		return locationAreaCode;
	}
	
	public void setLocationAreaCode(final Number locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	public Number getCellId() {
		return cellId;
	}
	
	public void setCellId(final Number cellId) {
		this.cellId = cellId;
	}

	public Number getTimingAdvance() {
		return timingAdvance;
	}
	
	public void setTimingAdvance(final Number timingAdvance) {
		this.timingAdvance = timingAdvance;
	}

	public Number getSignalStrength() {
		return signalStrength;
	}
	
	public void setSignalStrength(final Number signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Number getPrimaryScramblingCode() {
		return primaryScramblingCode;
	}
	
	public void setPrimaryScramblingCode(final Number primaryScramblingCode) {
		this.primaryScramblingCode = primaryScramblingCode;
	}

	public Number getServing() {
		return serving;
	}
	
	public void setServing(final Number serving) {
		this.serving = serving;
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
		if (r != null && r instanceof C8yCellTower) {
			C8yCellTower comparer = (C8yCellTower) r;
			if (String.valueOf(comparer.getRadioType()).equals(String.valueOf(this.getRadioType())) && comparer.getMobileCountryCode().equals(this.getMobileCountryCode()) && comparer.getMobileNetworkCode().equals(this.getMobileNetworkCode()) && comparer.getLocationAreaCode().equals(this.getLocationAreaCode()) && comparer.getCellId().equals(this.getCellId()) && comparer.getTimingAdvance().equals(this.getTimingAdvance()) && comparer.getSignalStrength().equals(this.getSignalStrength()) && comparer.getPrimaryScramblingCode().equals(this.getPrimaryScramblingCode()) && comparer.getServing().equals(this.getServing())) {
				return true;
			}
		}
		return false;
	}
}
