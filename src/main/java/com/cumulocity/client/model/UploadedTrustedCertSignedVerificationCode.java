// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The signed verification code to prove the user's possession of the certificate.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UploadedTrustedCertSignedVerificationCode {

	/**
	 * <p>A signed verification code that proves the right to use the certificate.</p>
	 */
	private String proofOfPossessionSignedVerificationCode;

	public UploadedTrustedCertSignedVerificationCode() {
	}

	public UploadedTrustedCertSignedVerificationCode(final String proofOfPossessionSignedVerificationCode) {
		this.proofOfPossessionSignedVerificationCode = proofOfPossessionSignedVerificationCode;
	}

	public String getProofOfPossessionSignedVerificationCode() {
		return proofOfPossessionSignedVerificationCode;
	}
	
	public void setProofOfPossessionSignedVerificationCode(final String proofOfPossessionSignedVerificationCode) {
		this.proofOfPossessionSignedVerificationCode = proofOfPossessionSignedVerificationCode;
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
		if (r != null && r instanceof UploadedTrustedCertSignedVerificationCode) {
			UploadedTrustedCertSignedVerificationCode comparer = (UploadedTrustedCertSignedVerificationCode) r;
			if (String.valueOf(comparer.getProofOfPossessionSignedVerificationCode()).equals(String.valueOf(this.getProofOfPossessionSignedVerificationCode()))) {
				return true;
			}
		}
		return false;
	}
}
