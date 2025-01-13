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
public class TenantTfaData {

	/**
	 * <p>Indicates whether two-factor authentication is enabled on system level or not.</p>
	 */
	private boolean enabledOnSystemLevel;

	/**
	 * <p>Indicates whether two-factor authentication is enabled on tenant level or not.</p>
	 */
	private boolean enabledOnTenantLevel;

	/**
	 * <p>Indicates whether two-factor authentication is enforced on system level or not.</p>
	 */
	private boolean enforcedOnSystemLevel;

	/**
	 * <p>Two-factor authentication is enforced for the specified group.</p>
	 */
	private String enforcedUsersGroup;

	/**
	 * <p>Two-factor authentication strategy.</p>
	 */
	private Strategy strategy;

	/**
	 * <p>Indicates whether two-factor authentication is enforced on tenant level or not.</p>
	 */
	private boolean totpEnforcedOnTenantLevel;

	public boolean getEnabledOnSystemLevel() {
		return enabledOnSystemLevel;
	}
	
	public void setEnabledOnSystemLevel(final boolean enabledOnSystemLevel) {
		this.enabledOnSystemLevel = enabledOnSystemLevel;
	}

	public boolean getEnabledOnTenantLevel() {
		return enabledOnTenantLevel;
	}
	
	public void setEnabledOnTenantLevel(final boolean enabledOnTenantLevel) {
		this.enabledOnTenantLevel = enabledOnTenantLevel;
	}

	public boolean getEnforcedOnSystemLevel() {
		return enforcedOnSystemLevel;
	}
	
	public void setEnforcedOnSystemLevel(final boolean enforcedOnSystemLevel) {
		this.enforcedOnSystemLevel = enforcedOnSystemLevel;
	}

	public String getEnforcedUsersGroup() {
		return enforcedUsersGroup;
	}
	
	public void setEnforcedUsersGroup(final String enforcedUsersGroup) {
		this.enforcedUsersGroup = enforcedUsersGroup;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(final Strategy strategy) {
		this.strategy = strategy;
	}

	public boolean getTotpEnforcedOnTenantLevel() {
		return totpEnforcedOnTenantLevel;
	}
	
	public void setTotpEnforcedOnTenantLevel(final boolean totpEnforcedOnTenantLevel) {
		this.totpEnforcedOnTenantLevel = totpEnforcedOnTenantLevel;
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
		if (r != null && r instanceof TenantTfaData) {
			TenantTfaData comparer = (TenantTfaData) r;
			if (Boolean.valueOf(comparer.getEnabledOnSystemLevel()).equals(Boolean.valueOf(this.getEnabledOnSystemLevel())) && Boolean.valueOf(comparer.getEnabledOnTenantLevel()).equals(Boolean.valueOf(this.getEnabledOnTenantLevel())) && Boolean.valueOf(comparer.getEnforcedOnSystemLevel()).equals(Boolean.valueOf(this.getEnforcedOnSystemLevel())) && String.valueOf(comparer.getEnforcedUsersGroup()).equals(String.valueOf(this.getEnforcedUsersGroup())) && comparer.getStrategy().equals(this.getStrategy()) && Boolean.valueOf(comparer.getTotpEnforcedOnTenantLevel()).equals(Boolean.valueOf(this.getTotpEnforcedOnTenantLevel()))) {
				return true;
			}
		}
		return false;
	}
}
