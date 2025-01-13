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
public class BulkNewDeviceRequest {

	/**
	 * <p>Number of lines processed from the CSV file, without the first line (column headers).</p>
	 */
	private int numberOfAll;

	/**
	 * <p>Number of created device credentials.</p>
	 */
	private int numberOfCreated;

	/**
	 * <p>Number of failed creations of device credentials.</p>
	 */
	private int numberOfFailed;

	/**
	 * <p>Number of successful creations of device credentials. This counts both create and update operations.</p>
	 */
	private int numberOfSuccessful;

	/**
	 * <p>An array with the updated device credentials.</p>
	 */
	private CredentialUpdatedList[] credentialUpdatedList;

	/**
	 * <p>An array with details of the failed device credentials.</p>
	 */
	private FailedCreationList[] failedCreationList;

	public int getNumberOfAll() {
		return numberOfAll;
	}
	
	public void setNumberOfAll(final int numberOfAll) {
		this.numberOfAll = numberOfAll;
	}

	public int getNumberOfCreated() {
		return numberOfCreated;
	}
	
	public void setNumberOfCreated(final int numberOfCreated) {
		this.numberOfCreated = numberOfCreated;
	}

	public int getNumberOfFailed() {
		return numberOfFailed;
	}
	
	public void setNumberOfFailed(final int numberOfFailed) {
		this.numberOfFailed = numberOfFailed;
	}

	public int getNumberOfSuccessful() {
		return numberOfSuccessful;
	}
	
	public void setNumberOfSuccessful(final int numberOfSuccessful) {
		this.numberOfSuccessful = numberOfSuccessful;
	}

	public CredentialUpdatedList[] getCredentialUpdatedList() {
		return credentialUpdatedList;
	}
	
	public void setCredentialUpdatedList(final CredentialUpdatedList[] credentialUpdatedList) {
		this.credentialUpdatedList = credentialUpdatedList;
	}

	public FailedCreationList[] getFailedCreationList() {
		return failedCreationList;
	}
	
	public void setFailedCreationList(final FailedCreationList[] failedCreationList) {
		this.failedCreationList = failedCreationList;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class CredentialUpdatedList {
	
		/**
		 * <p>The device credentials creation status.</p>
		 */
		private NewDeviceStatus bulkNewDeviceStatus;
	
		/**
		 * <p>Unique identifier of the device.</p>
		 */
		private String deviceId;
	
		public NewDeviceStatus getBulkNewDeviceStatus() {
			return bulkNewDeviceStatus;
		}
		
		public void setBulkNewDeviceStatus(final NewDeviceStatus bulkNewDeviceStatus) {
			this.bulkNewDeviceStatus = bulkNewDeviceStatus;
		}
	
		public String getDeviceId() {
			return deviceId;
		}
		
		public void setDeviceId(final String deviceId) {
			this.deviceId = deviceId;
		}
	
		
		/**
		 * <p>The device credentials creation status.</p>
		 */
		public enum NewDeviceStatus {
			@JsonProperty("CREATED")
			CREATED("CREATED"),
			@JsonProperty("FAILED")
			FAILED("FAILED"),
			@JsonProperty("CREDENTIAL_UPDATED")
			CREDENTIALUPDATED("CREDENTIAL_UPDATED");
		
			private String value;
		
			NewDeviceStatus(final String value) {
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
			if (r != null && r instanceof CredentialUpdatedList) {
				CredentialUpdatedList comparer = (CredentialUpdatedList) r;
				if (comparer.getBulkNewDeviceStatus().equals(this.getBulkNewDeviceStatus()) && String.valueOf(comparer.getDeviceId()).equals(String.valueOf(this.getDeviceId()))) {
					return true;
				}
			}
			return false;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class FailedCreationList {
	
		/**
		 * <p>The device credentials creation status.</p>
		 */
		private NewDeviceStatus bulkNewDeviceStatus;
	
		/**
		 * <p>Unique identifier of the device.</p>
		 */
		private String deviceId;
	
		/**
		 * <p>Reason for the failure.</p>
		 */
		private String failureReason;
	
		/**
		 * <p>Line where the failure occurred.</p>
		 */
		private String line;
	
		public NewDeviceStatus getBulkNewDeviceStatus() {
			return bulkNewDeviceStatus;
		}
		
		public void setBulkNewDeviceStatus(final NewDeviceStatus bulkNewDeviceStatus) {
			this.bulkNewDeviceStatus = bulkNewDeviceStatus;
		}
	
		public String getDeviceId() {
			return deviceId;
		}
		
		public void setDeviceId(final String deviceId) {
			this.deviceId = deviceId;
		}
	
		public String getFailureReason() {
			return failureReason;
		}
		
		public void setFailureReason(final String failureReason) {
			this.failureReason = failureReason;
		}
	
		public String getLine() {
			return line;
		}
		
		public void setLine(final String line) {
			this.line = line;
		}
	
		
		/**
		 * <p>The device credentials creation status.</p>
		 */
		public enum NewDeviceStatus {
			@JsonProperty("CREATED")
			CREATED("CREATED"),
			@JsonProperty("FAILED")
			FAILED("FAILED"),
			@JsonProperty("CREDENTIAL_UPDATED")
			CREDENTIALUPDATED("CREDENTIAL_UPDATED");
		
			private String value;
		
			NewDeviceStatus(final String value) {
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
			if (r != null && r instanceof FailedCreationList) {
				FailedCreationList comparer = (FailedCreationList) r;
				if (comparer.getBulkNewDeviceStatus().equals(this.getBulkNewDeviceStatus()) && String.valueOf(comparer.getDeviceId()).equals(String.valueOf(this.getDeviceId())) && String.valueOf(comparer.getFailureReason()).equals(String.valueOf(this.getFailureReason())) && String.valueOf(comparer.getLine()).equals(String.valueOf(this.getLine()))) {
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
		if (r != null && r instanceof BulkNewDeviceRequest) {
			BulkNewDeviceRequest comparer = (BulkNewDeviceRequest) r;
			if (Integer.valueOf(comparer.getNumberOfAll()).equals(Integer.valueOf(this.getNumberOfAll())) && Integer.valueOf(comparer.getNumberOfCreated()).equals(Integer.valueOf(this.getNumberOfCreated())) && Integer.valueOf(comparer.getNumberOfFailed()).equals(Integer.valueOf(this.getNumberOfFailed())) && Integer.valueOf(comparer.getNumberOfSuccessful()).equals(Integer.valueOf(this.getNumberOfSuccessful())) && comparer.getCredentialUpdatedList().equals(this.getCredentialUpdatedList()) && comparer.getFailedCreationList().equals(this.getFailedCreationList())) {
				return true;
			}
		}
		return false;
	}
}
