// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>For basic authentication case only.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class BasicAuthenticationRestrictions {

	/**
	 * <p>List of types of clients which are not allowed to use basic authentication. Currently the only supported option is WEB_BROWSERS.</p>
	 */
	private String[] forbiddenClients;

	/**
	 * <p>List of user agents, passed in <code>User-Agent</code> HTTP header, which are blocked if basic authentication is used.</p>
	 */
	private String[] forbiddenUserAgents;

	/**
	 * <p>List of user agents, passed in <code>User-Agent</code> HTTP header, which are allowed to use basic authentication.</p>
	 */
	private String[] trustedUserAgents;

	public String[] getForbiddenClients() {
		return forbiddenClients;
	}
	
	public void setForbiddenClients(final String[] forbiddenClients) {
		this.forbiddenClients = forbiddenClients;
	}

	public String[] getForbiddenUserAgents() {
		return forbiddenUserAgents;
	}
	
	public void setForbiddenUserAgents(final String[] forbiddenUserAgents) {
		this.forbiddenUserAgents = forbiddenUserAgents;
	}

	public String[] getTrustedUserAgents() {
		return trustedUserAgents;
	}
	
	public void setTrustedUserAgents(final String[] trustedUserAgents) {
		this.trustedUserAgents = trustedUserAgents;
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
		if (r != null && r instanceof BasicAuthenticationRestrictions) {
			BasicAuthenticationRestrictions comparer = (BasicAuthenticationRestrictions) r;
			if (comparer.getForbiddenClients().equals(this.getForbiddenClients()) && comparer.getForbiddenUserAgents().equals(this.getForbiddenUserAgents()) && comparer.getTrustedUserAgents().equals(this.getTrustedUserAgents())) {
				return true;
			}
		}
		return false;
	}
}
