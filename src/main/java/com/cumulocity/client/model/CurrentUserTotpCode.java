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
public class CurrentUserTotpCode {

	/**
	 * <p>Two-factor authentication code entered by the user to log in to the platform.</p>
	 */
	private String code;

	public CurrentUserTotpCode() {
	}

	public CurrentUserTotpCode(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(final String code) {
		this.code = code;
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
		if (r != null && r instanceof CurrentUserTotpCode) {
			CurrentUserTotpCode comparer = (CurrentUserTotpCode) r;
			if (String.valueOf(comparer.getCode()).equals(String.valueOf(this.getCode()))) {
				return true;
			}
		}
		return false;
	}
}
