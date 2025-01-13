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
public class VerifyCertificateChain {

	/**
	 * <p>The result of validating the certificate chain.</p>
	 */
	private boolean successfullyValidated;

	/**
	 * <p>The tenant ID used for validation.</p>
	 */
	private String tenantId;

	/**
	 * <p>The name of the organization which signed the certificate.</p>
	 */
	private String issuer;

	/**
	 * <p>The name of the organization to which the certificate belongs.</p>
	 */
	private String subject;

	public boolean getSuccessfullyValidated() {
		return successfullyValidated;
	}
	
	public void setSuccessfullyValidated(final boolean successfullyValidated) {
		this.successfullyValidated = successfullyValidated;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(final String issuer) {
		this.issuer = issuer;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(final String subject) {
		this.subject = subject;
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
		if (r != null && r instanceof VerifyCertificateChain) {
			VerifyCertificateChain comparer = (VerifyCertificateChain) r;
			if (Boolean.valueOf(comparer.getSuccessfullyValidated()).equals(Boolean.valueOf(this.getSuccessfullyValidated())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && String.valueOf(comparer.getIssuer()).equals(String.valueOf(this.getIssuer())) && String.valueOf(comparer.getSubject()).equals(String.valueOf(this.getSubject()))) {
				return true;
			}
		}
		return false;
	}
}
