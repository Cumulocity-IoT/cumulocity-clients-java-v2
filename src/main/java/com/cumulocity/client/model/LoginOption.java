// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Login option properties.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LoginOption {

	/**
	 * <p>For basic authentication case only.</p>
	 */
	private BasicAuthenticationRestrictions authenticationRestrictions;

	/**
	 * <p>Indicates if password strength is enforced.</p>
	 */
	private boolean enforceStrength;

	/**
	 * <p>The grant type of the OAuth configuration.</p>
	 */
	private GrantType grantType;

	/**
	 * <p>Minimum length for the password when the strength validation is enforced.</p>
	 */
	private int greenMinLength;

	/**
	 * <p>Unique identifier of this login option.</p>
	 */
	private String id;

	/**
	 * <p>A URL linking to the token generating endpoint.</p>
	 */
	private String initRequest;

	/**
	 * <p>The tenant domain.</p>
	 */
	private String loginRedirectDomain;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The session configuration properties are only available for OAI-Secure. See <a href="https://www.cumulocity.com/docs/authentication/basic-settings/#oai-secure-session-configuration">Platform administration > Authentication > Basic settings > OAI Secure session configuration </a> in the Cumulocity IoT user documentation.</p>
	 */
	private OAuthSessionConfiguration sessionConfiguration;

	/**
	 * <p>Enforce password strength validation on subtenant level. <code>enforceStrength</code> enforces it on all tenants in the platform.</p>
	 */
	private boolean strengthValidity;

	/**
	 * <p>Two-factor authentication being used by this login option. TFA supported: SMS and TOTP.</p>
	 */
	private String tfaStrategy;

	/**
	 * <p>The type of authentication. See <a href="#section/Authentication">Authentication</a> for more details.</p>
	 */
	private String type;

	/**
	 * <p>Specifies if the users are managed internally by Cumulocity IoT (<code>INTERNAL</code>) or if the users data are managed by a external system (<code>REMOTE</code>).</p>
	 */
	private String userManagementSource;

	/**
	 * <p>Indicates if this login option is available in the login page (only for SSO).</p>
	 */
	private boolean visibleOnLoginPage;

	/**
	 * <p>The type of authentication.</p>
	 */
	@Deprecated
	private String pType;

	public BasicAuthenticationRestrictions getAuthenticationRestrictions() {
		return authenticationRestrictions;
	}
	
	public void setAuthenticationRestrictions(final BasicAuthenticationRestrictions authenticationRestrictions) {
		this.authenticationRestrictions = authenticationRestrictions;
	}

	public boolean getEnforceStrength() {
		return enforceStrength;
	}
	
	public void setEnforceStrength(final boolean enforceStrength) {
		this.enforceStrength = enforceStrength;
	}

	public GrantType getGrantType() {
		return grantType;
	}
	
	public void setGrantType(final GrantType grantType) {
		this.grantType = grantType;
	}

	public int getGreenMinLength() {
		return greenMinLength;
	}
	
	public void setGreenMinLength(final int greenMinLength) {
		this.greenMinLength = greenMinLength;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getInitRequest() {
		return initRequest;
	}
	
	public void setInitRequest(final String initRequest) {
		this.initRequest = initRequest;
	}

	public String getLoginRedirectDomain() {
		return loginRedirectDomain;
	}
	
	public void setLoginRedirectDomain(final String loginRedirectDomain) {
		this.loginRedirectDomain = loginRedirectDomain;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public OAuthSessionConfiguration getSessionConfiguration() {
		return sessionConfiguration;
	}
	
	public void setSessionConfiguration(final OAuthSessionConfiguration sessionConfiguration) {
		this.sessionConfiguration = sessionConfiguration;
	}

	public boolean getStrengthValidity() {
		return strengthValidity;
	}
	
	public void setStrengthValidity(final boolean strengthValidity) {
		this.strengthValidity = strengthValidity;
	}

	public String getTfaStrategy() {
		return tfaStrategy;
	}
	
	public void setTfaStrategy(final String tfaStrategy) {
		this.tfaStrategy = tfaStrategy;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	public String getUserManagementSource() {
		return userManagementSource;
	}
	
	public void setUserManagementSource(final String userManagementSource) {
		this.userManagementSource = userManagementSource;
	}

	public boolean getVisibleOnLoginPage() {
		return visibleOnLoginPage;
	}
	
	public void setVisibleOnLoginPage(final boolean visibleOnLoginPage) {
		this.visibleOnLoginPage = visibleOnLoginPage;
	}

	public String getPType() {
		return pType;
	}
	
	public void setPType(final String pType) {
		this.pType = pType;
	}

	
	/**
	 * <p>The grant type of the OAuth configuration.</p>
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
		if (r != null && r instanceof LoginOption) {
			LoginOption comparer = (LoginOption) r;
			if (comparer.getAuthenticationRestrictions().equals(this.getAuthenticationRestrictions()) && Boolean.valueOf(comparer.getEnforceStrength()).equals(Boolean.valueOf(this.getEnforceStrength())) && comparer.getGrantType().equals(this.getGrantType()) && Integer.valueOf(comparer.getGreenMinLength()).equals(Integer.valueOf(this.getGreenMinLength())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getInitRequest()).equals(String.valueOf(this.getInitRequest())) && String.valueOf(comparer.getLoginRedirectDomain()).equals(String.valueOf(this.getLoginRedirectDomain())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSessionConfiguration().equals(this.getSessionConfiguration()) && Boolean.valueOf(comparer.getStrengthValidity()).equals(Boolean.valueOf(this.getStrengthValidity())) && String.valueOf(comparer.getTfaStrategy()).equals(String.valueOf(this.getTfaStrategy())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && String.valueOf(comparer.getUserManagementSource()).equals(String.valueOf(this.getUserManagementSource())) && Boolean.valueOf(comparer.getVisibleOnLoginPage()).equals(Boolean.valueOf(this.getVisibleOnLoginPage())) && String.valueOf(comparer.getPType()).equals(String.valueOf(this.getPType()))) {
				return true;
			}
		}
		return false;
	}
}
