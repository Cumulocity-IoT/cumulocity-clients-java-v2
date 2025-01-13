// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Holds basic connectivity-related information, such as the equipment identifier of the modem (IMEI) in the device. This identifier is globally unique and often used to identify a mobile device.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yMobile {

	/**
	 * <p>The equipment identifier (IMEI) of the modem in the device.</p>
	 */
	private String imei;

	/**
	 * <p>The identifier of the cell in the mobile network that the device is currently connected with.</p>
	 */
	private String cellId;

	/**
	 * <p>The identifier of the SIM card that is currently in the device (often printed on the card).</p>
	 */
	private String iccid;

	/**
	 * <p>Other possible values are: <code>c8y_Mobile.imsi</code>, <code>c8y_Mobile.currentOperator</code>, <code>c8y_Mobile.currentBand</code>, <code>c8y_Mobile.connType</code>, <code>c8y_Mobile.rssi</code>, <code>c8y_Mobile.ecn0</code>, <code>c8y_Mobile.rcsp</code>, <code>c8y_Mobile.mnc</code>, <code>c8y_Mobile.lac</code> and <code>c8y_Mobile.msisdn</code>.</p>
	 */
	private Map<String, String> customFragments;

	public C8yMobile() {
	}

	public C8yMobile(final String imei, final String cellId, final String iccid) {
		this.imei = imei;
		this.cellId = cellId;
		this.iccid = iccid;
	}

	public String getImei() {
		return imei;
	}
	
	public void setImei(final String imei) {
		this.imei = imei;
	}

	public String getCellId() {
		return cellId;
	}
	
	public void setCellId(final String cellId) {
		this.cellId = cellId;
	}

	public String getIccid() {
		return iccid;
	}
	
	public void setIccid(final String iccid) {
		this.iccid = iccid;
	}

	public Map<String, String> getCustomFragments() {
		return customFragments;
	}
	
	public void setCustomFragments(final Map<String, String> customFragments) {
		this.customFragments = customFragments;
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
		if (r != null && r instanceof C8yMobile) {
			C8yMobile comparer = (C8yMobile) r;
			if (String.valueOf(comparer.getImei()).equals(String.valueOf(this.getImei())) && String.valueOf(comparer.getCellId()).equals(String.valueOf(this.getCellId())) && String.valueOf(comparer.getIccid()).equals(String.valueOf(this.getIccid())) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
