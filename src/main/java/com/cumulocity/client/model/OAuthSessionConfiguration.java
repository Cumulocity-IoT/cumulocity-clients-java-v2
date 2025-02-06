// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The session configuration properties are only available for OAI-Secure. See <a href="https://www.cumulocity.com/docs/authentication/basic-settings/#oai-secure-session-configuration">Platform administration > Authentication > Basic settings > OAI Secure session configuration </a> in the Cumulocity user documentation.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OAuthSessionConfiguration {

	/**
	 * <p>Maximum session duration (in milliseconds) during which a user does not have to login again.</p>
	 */
	private int absoluteTimeoutMillis;

	/**
	 * <p>Maximum number of parallel sessions for one user.</p>
	 */
	private int maximumNumberOfParallelSessions;

	/**
	 * <p>Amount of time before a token expires (in milliseconds) during which the token may be renewed.</p>
	 */
	private int renewalTimeoutMillis;

	/**
	 * <p>Switch to turn additional user agent verification on or off during the session.</p>
	 */
	private boolean userAgentValidationRequired;

	public int getAbsoluteTimeoutMillis() {
		return absoluteTimeoutMillis;
	}
	
	public void setAbsoluteTimeoutMillis(final int absoluteTimeoutMillis) {
		this.absoluteTimeoutMillis = absoluteTimeoutMillis;
	}

	public int getMaximumNumberOfParallelSessions() {
		return maximumNumberOfParallelSessions;
	}
	
	public void setMaximumNumberOfParallelSessions(final int maximumNumberOfParallelSessions) {
		this.maximumNumberOfParallelSessions = maximumNumberOfParallelSessions;
	}

	public int getRenewalTimeoutMillis() {
		return renewalTimeoutMillis;
	}
	
	public void setRenewalTimeoutMillis(final int renewalTimeoutMillis) {
		this.renewalTimeoutMillis = renewalTimeoutMillis;
	}

	public boolean getUserAgentValidationRequired() {
		return userAgentValidationRequired;
	}
	
	public void setUserAgentValidationRequired(final boolean userAgentValidationRequired) {
		this.userAgentValidationRequired = userAgentValidationRequired;
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
		if (r != null && r instanceof OAuthSessionConfiguration) {
			OAuthSessionConfiguration comparer = (OAuthSessionConfiguration) r;
			if (Integer.valueOf(comparer.getAbsoluteTimeoutMillis()).equals(Integer.valueOf(this.getAbsoluteTimeoutMillis())) && Integer.valueOf(comparer.getMaximumNumberOfParallelSessions()).equals(Integer.valueOf(this.getMaximumNumberOfParallelSessions())) && Integer.valueOf(comparer.getRenewalTimeoutMillis()).equals(Integer.valueOf(this.getRenewalTimeoutMillis())) && Boolean.valueOf(comparer.getUserAgentValidationRequired()).equals(Boolean.valueOf(this.getUserAgentValidationRequired()))) {
				return true;
			}
		}
		return false;
	}
}
