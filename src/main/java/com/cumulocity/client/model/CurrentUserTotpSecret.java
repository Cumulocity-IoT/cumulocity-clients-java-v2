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
public class CurrentUserTotpSecret {

	/**
	 * <p>Secret used by two-factor authentication applications to generate the TFA codes.</p>
	 */
	private String rawSecret;

	/**
	 * <p>URL used to set the two-factor authentication secret for the TFA application.</p>
	 */
	private String secretQrUrl;

	public String getRawSecret() {
		return rawSecret;
	}
	
	public void setRawSecret(final String rawSecret) {
		this.rawSecret = rawSecret;
	}

	public String getSecretQrUrl() {
		return secretQrUrl;
	}
	
	public void setSecretQrUrl(final String secretQrUrl) {
		this.secretQrUrl = secretQrUrl;
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
		if (r != null && r instanceof CurrentUserTotpSecret) {
			CurrentUserTotpSecret comparer = (CurrentUserTotpSecret) r;
			if (String.valueOf(comparer.getRawSecret()).equals(String.valueOf(this.getRawSecret())) && String.valueOf(comparer.getSecretQrUrl()).equals(String.valueOf(this.getSecretQrUrl()))) {
				return true;
			}
		}
		return false;
	}
}
