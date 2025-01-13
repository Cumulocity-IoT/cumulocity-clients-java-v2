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
public class DeviceControlApiResource {

	/**
	 * <p>Collection of all operations.</p>
	 */
	private Operations operations;

	/**
	 * <p>Read-only collection of all operations with a particular status.</p>
	 */
	private String operationsByStatus;

	/**
	 * <p>Read-only collection of all operations targeting a particular agent.</p>
	 */
	private String operationsByAgentId;

	/**
	 * <p>Read-only collection of all operations targeting a particular agent and with a particular status.</p>
	 */
	private String operationsByAgentIdAndStatus;

	/**
	 * <p>Read-only collection of all operations to be executed on a particular device.</p>
	 */
	private String operationsByDeviceId;

	/**
	 * <p>Read-only collection of all operations with a particular status, that should be executed on a particular device.</p>
	 */
	private String operationsByDeviceIdAndStatus;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public Operations getOperations() {
		return operations;
	}
	
	public void setOperations(final Operations operations) {
		this.operations = operations;
	}

	public String getOperationsByStatus() {
		return operationsByStatus;
	}
	
	public void setOperationsByStatus(final String operationsByStatus) {
		this.operationsByStatus = operationsByStatus;
	}

	public String getOperationsByAgentId() {
		return operationsByAgentId;
	}
	
	public void setOperationsByAgentId(final String operationsByAgentId) {
		this.operationsByAgentId = operationsByAgentId;
	}

	public String getOperationsByAgentIdAndStatus() {
		return operationsByAgentIdAndStatus;
	}
	
	public void setOperationsByAgentIdAndStatus(final String operationsByAgentIdAndStatus) {
		this.operationsByAgentIdAndStatus = operationsByAgentIdAndStatus;
	}

	public String getOperationsByDeviceId() {
		return operationsByDeviceId;
	}
	
	public void setOperationsByDeviceId(final String operationsByDeviceId) {
		this.operationsByDeviceId = operationsByDeviceId;
	}

	public String getOperationsByDeviceIdAndStatus() {
		return operationsByDeviceIdAndStatus;
	}
	
	public void setOperationsByDeviceIdAndStatus(final String operationsByDeviceIdAndStatus) {
		this.operationsByDeviceIdAndStatus = operationsByDeviceIdAndStatus;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * <p>Collection of all operations.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Operations {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		/**
		 * <p>An array containing the registered operations.</p>
		 */
		private OperationReference[] operations;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public OperationReference[] getOperations() {
			return operations;
		}
		
		public void setOperations(final OperationReference[] operations) {
			this.operations = operations;
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
			if (r != null && r instanceof Operations) {
				Operations comparer = (Operations) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getOperations().equals(this.getOperations())) {
					return true;
				}
			}
			return false;
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
		if (r != null && r instanceof DeviceControlApiResource) {
			DeviceControlApiResource comparer = (DeviceControlApiResource) r;
			if (comparer.getOperations().equals(this.getOperations()) && String.valueOf(comparer.getOperationsByStatus()).equals(String.valueOf(this.getOperationsByStatus())) && String.valueOf(comparer.getOperationsByAgentId()).equals(String.valueOf(this.getOperationsByAgentId())) && String.valueOf(comparer.getOperationsByAgentIdAndStatus()).equals(String.valueOf(this.getOperationsByAgentIdAndStatus())) && String.valueOf(comparer.getOperationsByDeviceId()).equals(String.valueOf(this.getOperationsByDeviceId())) && String.valueOf(comparer.getOperationsByDeviceIdAndStatus()).equals(String.valueOf(this.getOperationsByDeviceIdAndStatus())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
