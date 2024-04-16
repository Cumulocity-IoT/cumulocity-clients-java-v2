// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DeviceCredentials {

	/**
	 * <p>The external ID of the device.</p>
	 */
	private String id;

	/**
	 * <p>Password of these device credentials.</p>
	 */
	private String password;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Tenant ID for these device credentials.</p>
	 */
	private String tenantId;

	/**
	 * <p>Username of these device credentials.</p>
	 */
	private String username;

	/**
	 * <p>Security token which is required and verified against during device request acceptance.See <a href="https://cumulocity.com/docs/device-management-application/registering-devices/#security-token-policy">Security token policy</a> for more details on configuration.See <a href="/#operation/putNewDeviceRequestResource">Update specific new device request status</a> for details on submitting token upon device acceptance.</p>
	 */
	private String securityToken;

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
	}

	public String getSecurityToken() {
		return securityToken;
	}
	
	public void setSecurityToken(final String securityToken) {
		this.securityToken = securityToken;
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
		if (r != null && r instanceof DeviceCredentials) {
			DeviceCredentials comparer = (DeviceCredentials) r;
			if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && String.valueOf(comparer.getUsername()).equals(String.valueOf(this.getUsername())) && String.valueOf(comparer.getSecurityToken()).equals(String.valueOf(this.getSecurityToken()))) {
				return true;
			}
		}
		return false;
	}
}
