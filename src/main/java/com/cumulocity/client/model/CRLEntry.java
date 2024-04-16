// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CRLEntry {

	/**
	 * <p>Revoked certificate serial number in hexadecimal.</p>
	 */
	private String serialNumberInHex;

	/**
	 * <p>Date and time when the certificate is revoked.</p>
	 */
	private String revocationDate;

	public CRLEntry() {
	}

	public CRLEntry(final String serialNumberInHex) {
		this.serialNumberInHex = serialNumberInHex;
	}

	public String getSerialNumberInHex() {
		return serialNumberInHex;
	}
	
	public void setSerialNumberInHex(final String serialNumberInHex) {
		this.serialNumberInHex = serialNumberInHex;
	}

	public String getRevocationDate() {
		return revocationDate;
	}
	
	public void setRevocationDate(final String revocationDate) {
		this.revocationDate = revocationDate;
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
		if (r != null && r instanceof CRLEntry) {
			CRLEntry comparer = (CRLEntry) r;
			if (String.valueOf(comparer.getSerialNumberInHex()).equals(String.valueOf(this.getSerialNumberInHex())) && String.valueOf(comparer.getRevocationDate()).equals(String.valueOf(this.getRevocationDate()))) {
				return true;
			}
		}
		return false;
	}
}
