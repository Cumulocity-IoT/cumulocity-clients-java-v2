// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
public class LoginForm {

	/**
	 * <p>Used in case of SSO login. A code received from the external authentication server is exchanged to an internal access token.</p>
	 */
	private String code;

	/**
	 * <p>Dependent on the authentication type. PASSWORD is used for OAI-Secure.</p>
	 */
	@JsonProperty(value = "grant_type")
	private GrantType grantType;

	/**
	 * <p>Used in case of OAI-Secure authentication.</p>
	 */
	private String password;

	/**
	 * <p>Current TFA code, sent by the user, if a TFA code is required to log in. Used in case of OAI-Secure authentication.</p>
	 */
	@JsonProperty(value = "tfa_code")
	private String tfaCode;

	/**
	 * <p>Used in case of OAI-Secure authentication.</p>
	 */
	private String username;

	public String getCode() {
		return code;
	}
	
	public void setCode(final String code) {
		this.code = code;
	}

	public GrantType getGrantType() {
		return grantType;
	}
	
	public void setGrantType(final GrantType grantType) {
		this.grantType = grantType;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public String getTfaCode() {
		return tfaCode;
	}
	
	public void setTfaCode(final String tfaCode) {
		this.tfaCode = tfaCode;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
	}

	
	/**
	 * <p>Dependent on the authentication type. PASSWORD is used for OAI-Secure.</p>
	 */
	public enum GrantType {
		@JsonProperty("PASSWORD")
		PASSWORD("PASSWORD"),
		@JsonProperty("AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE");
	
		private String value;
	
		GrantType(final String value) {
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
		if (r != null && r instanceof LoginForm) {
			LoginForm comparer = (LoginForm) r;
			if (String.valueOf(comparer.getCode()).equals(String.valueOf(this.getCode())) && comparer.getGrantType().equals(this.getGrantType()) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getTfaCode()).equals(String.valueOf(this.getTfaCode())) && String.valueOf(comparer.getUsername()).equals(String.valueOf(this.getUsername()))) {
				return true;
			}
		}
		return false;
	}
}
