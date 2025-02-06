// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UploadedTrustedCertificate {

	/**
	 * <p>Indicates whether the automatic device registration is enabled or not.</p>
	 */
	private boolean autoRegistrationEnabled;

	/**
	 * <p>Trusted certificate in PEM format.</p>
	 */
	private String certInPemFormat;

	/**
	 * <p>Name of the certificate.</p>
	 */
	private String name;

	/**
	 * <p>Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity platform.</p>
	 */
	private Status status;

	public UploadedTrustedCertificate() {
	}

	public UploadedTrustedCertificate(final String certInPemFormat, final Status status) {
		this.certInPemFormat = certInPemFormat;
		this.status = status;
	}

	public boolean getAutoRegistrationEnabled() {
		return autoRegistrationEnabled;
	}
	
	public void setAutoRegistrationEnabled(final boolean autoRegistrationEnabled) {
		this.autoRegistrationEnabled = autoRegistrationEnabled;
	}

	public String getCertInPemFormat() {
		return certInPemFormat;
	}
	
	public void setCertInPemFormat(final String certInPemFormat) {
		this.certInPemFormat = certInPemFormat;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	
	/**
	 * <p>Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity platform.</p>
	 */
	public enum Status {
		@JsonProperty("ENABLED")
		ENABLED("ENABLED"),
		@JsonProperty("DISABLED")
		DISABLED("DISABLED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
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
		if (r != null && r instanceof UploadedTrustedCertificate) {
			UploadedTrustedCertificate comparer = (UploadedTrustedCertificate) r;
			if (Boolean.valueOf(comparer.getAutoRegistrationEnabled()).equals(Boolean.valueOf(this.getAutoRegistrationEnabled())) && String.valueOf(comparer.getCertInPemFormat()).equals(String.valueOf(this.getCertInPemFormat())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && comparer.getStatus().equals(this.getStatus())) {
				return true;
			}
		}
		return false;
	}
}
