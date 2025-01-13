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
public class FeatureToggle {

	/**
	 * <p>A unique key of the feature toggle.</p>
	 */
	private String key;

	/**
	 * <p>Current phase of feature toggle rollout.</p>
	 */
	private Phase phase;

	/**
	 * <p>Current value of the feature toggle marking whether the feature is active or not.</p>
	 */
	private boolean active;

	/**
	 * <p>The source of the feature toggle value - either it's feature toggle definition provided default, or per tenant provided override.</p>
	 */
	private Strategy strategy;

	public String getKey() {
		return key;
	}
	
	public void setKey(final String key) {
		this.key = key;
	}

	public Phase getPhase() {
		return phase;
	}
	
	public void setPhase(final Phase phase) {
		this.phase = phase;
	}

	public boolean getActive() {
		return active;
	}
	
	public void setActive(final boolean active) {
		this.active = active;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(final Strategy strategy) {
		this.strategy = strategy;
	}

	
	/**
	 * <p>Current phase of feature toggle rollout.</p>
	 */
	public enum Phase {
		@JsonProperty("IN_DEVELOPMENT")
		INDEVELOPMENT("IN_DEVELOPMENT"),
		@JsonProperty("PRIVATE_PREVIEW")
		PRIVATEPREVIEW("PRIVATE_PREVIEW"),
		@JsonProperty("PUBLIC_PREVIEW")
		PUBLICPREVIEW("PUBLIC_PREVIEW"),
		@JsonProperty("GENERALLY_AVAILABLE")
		GENERALLYAVAILABLE("GENERALLY_AVAILABLE");
	
		private String value;
	
		Phase(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>The source of the feature toggle value - either it's feature toggle definition provided default, or per tenant provided override.</p>
	 */
	public enum Strategy {
		@JsonProperty("DEFAULT")
		DEFAULT("DEFAULT"),
		@JsonProperty("TENANT")
		TENANT("TENANT");
	
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
		if (r != null && r instanceof FeatureToggle) {
			FeatureToggle comparer = (FeatureToggle) r;
			if (String.valueOf(comparer.getKey()).equals(String.valueOf(this.getKey())) && comparer.getPhase().equals(this.getPhase()) && Boolean.valueOf(comparer.getActive()).equals(Boolean.valueOf(this.getActive())) && comparer.getStrategy().equals(this.getStrategy())) {
				return true;
			}
		}
		return false;
	}
}
