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
public class NewDeviceRequest {

	/**
	 * <p>External ID of the device.</p>
	 */
	private String id;

	/**
	 * <p>ID of the group to which the device will be assigned.</p>
	 */
	private String groupId;

	/**
	 * <p>Type of the device.</p>
	 */
	private String type;

	/**
	 * <p>Tenant who owns the device.</p>
	 */
	private String tenantId;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Status of this new device request.</p>
	 */
	private Status status;

	/**
	 * <p>Owner of the device.</p>
	 */
	private String owner;

	/**
	 * <p>Date and time when the device was created in the database.</p>
	 */
	private String creationTime;

	/**
	 * <p>When accepting a device request, the security token is verified against the token submitted by the device when requesting credentials.See <a href="https://www.cumulocity.com/docs/device-management-application/registering-devices/#security-token-policy">Security token policy</a> for details on configuration.See <a href="/#operation/postDeviceCredentialsCollectionResource">Create device credentials</a> for details on creating token for device registration.<code>securityToken</code> parameter can be added only when submitting <code>ACCEPTED</code> status.</p>
	 */
	private String securityToken;

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(final String owner) {
		this.owner = owner;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public String getSecurityToken() {
		return securityToken;
	}
	
	public void setSecurityToken(final String securityToken) {
		this.securityToken = securityToken;
	}

	
	/**
	 * <p>Status of this new device request.</p>
	 */
	public enum Status {
		@JsonProperty("WAITING_FOR_CONNECTION")
		WAITINGFORCONNECTION("WAITING_FOR_CONNECTION"),
		@JsonProperty("PENDING_ACCEPTANCE")
		PENDINGACCEPTANCE("PENDING_ACCEPTANCE"),
		@JsonProperty("ACCEPTED")
		ACCEPTED("ACCEPTED");
	
		private String value;
	
		Status(final String value) {
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
		if (r != null && r instanceof NewDeviceRequest) {
			NewDeviceRequest comparer = (NewDeviceRequest) r;
			if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getGroupId()).equals(String.valueOf(this.getGroupId())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getStatus().equals(this.getStatus()) && String.valueOf(comparer.getOwner()).equals(String.valueOf(this.getOwner())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getSecurityToken()).equals(String.valueOf(this.getSecurityToken()))) {
				return true;
			}
		}
		return false;
	}
}
