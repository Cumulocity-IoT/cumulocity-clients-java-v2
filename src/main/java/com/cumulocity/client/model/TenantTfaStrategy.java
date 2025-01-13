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
public class TenantTfaStrategy {

	/**
	 * <p>Two-factor authentication strategy.</p>
	 */
	private Strategy strategy;

	public TenantTfaStrategy() {
	}

	public TenantTfaStrategy(final Strategy strategy) {
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(final Strategy strategy) {
		this.strategy = strategy;
	}

	
	/**
	 * <p>Two-factor authentication strategy.</p>
	 */
	public enum Strategy {
		@JsonProperty("SMS")
		SMS("SMS"),
		@JsonProperty("TOTP")
		TOTP("TOTP");
	
		private String value;
	
		Strategy(final String value) {
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
		if (r != null && r instanceof TenantTfaStrategy) {
			TenantTfaStrategy comparer = (TenantTfaStrategy) r;
			if (comparer.getStrategy().equals(this.getStrategy())) {
				return true;
			}
		}
		return false;
	}
}
