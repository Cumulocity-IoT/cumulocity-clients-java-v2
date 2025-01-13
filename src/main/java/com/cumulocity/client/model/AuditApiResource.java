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
public class AuditApiResource {

	/**
	 * <p>Collection of audit records</p>
	 */
	private AuditRecords auditRecords;

	/**
	 * <p>Read-only collection of audit records for a specific application. The placeholder {application} must be the name of a registered application.</p>
	 */
	private String auditRecordsForApplication;

	/**
	 * <p>Read-only collection of audit records for a specific type.</p>
	 */
	private String auditRecordsForType;

	/**
	 * <p>Read-only collection of audit records for a specific user. The placeholder {user} must be a username of a registered user.</p>
	 */
	private String auditRecordsForUser;

	/**
	 * <p>Read-only collection of audit records for specific type and application.</p>
	 */
	private String auditRecordsForTypeAndApplication;

	/**
	 * <p>Read-only collection of audit records for specific type, user and application.</p>
	 */
	private String auditRecordsForTypeAndUserAndApplication;

	/**
	 * <p>Read-only collection of audit records for specific user and application.</p>
	 */
	private String auditRecordsForUserAndApplication;

	/**
	 * <p>Read-only collection of audit records for specific user and type.</p>
	 */
	private String auditRecordsForUserAndType;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public AuditRecords getAuditRecords() {
		return auditRecords;
	}
	
	public void setAuditRecords(final AuditRecords auditRecords) {
		this.auditRecords = auditRecords;
	}

	public String getAuditRecordsForApplication() {
		return auditRecordsForApplication;
	}
	
	public void setAuditRecordsForApplication(final String auditRecordsForApplication) {
		this.auditRecordsForApplication = auditRecordsForApplication;
	}

	public String getAuditRecordsForType() {
		return auditRecordsForType;
	}
	
	public void setAuditRecordsForType(final String auditRecordsForType) {
		this.auditRecordsForType = auditRecordsForType;
	}

	public String getAuditRecordsForUser() {
		return auditRecordsForUser;
	}
	
	public void setAuditRecordsForUser(final String auditRecordsForUser) {
		this.auditRecordsForUser = auditRecordsForUser;
	}

	public String getAuditRecordsForTypeAndApplication() {
		return auditRecordsForTypeAndApplication;
	}
	
	public void setAuditRecordsForTypeAndApplication(final String auditRecordsForTypeAndApplication) {
		this.auditRecordsForTypeAndApplication = auditRecordsForTypeAndApplication;
	}

	public String getAuditRecordsForTypeAndUserAndApplication() {
		return auditRecordsForTypeAndUserAndApplication;
	}
	
	public void setAuditRecordsForTypeAndUserAndApplication(final String auditRecordsForTypeAndUserAndApplication) {
		this.auditRecordsForTypeAndUserAndApplication = auditRecordsForTypeAndUserAndApplication;
	}

	public String getAuditRecordsForUserAndApplication() {
		return auditRecordsForUserAndApplication;
	}
	
	public void setAuditRecordsForUserAndApplication(final String auditRecordsForUserAndApplication) {
		this.auditRecordsForUserAndApplication = auditRecordsForUserAndApplication;
	}

	public String getAuditRecordsForUserAndType() {
		return auditRecordsForUserAndType;
	}
	
	public void setAuditRecordsForUserAndType(final String auditRecordsForUserAndType) {
		this.auditRecordsForUserAndType = auditRecordsForUserAndType;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * <p>Collection of audit records</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class AuditRecords {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		private AuditRecord[] auditRecords;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public AuditRecord[] getAuditRecords() {
			return auditRecords;
		}
		
		public void setAuditRecords(final AuditRecord[] auditRecords) {
			this.auditRecords = auditRecords;
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
			if (r != null && r instanceof AuditRecords) {
				AuditRecords comparer = (AuditRecords) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getAuditRecords().equals(this.getAuditRecords())) {
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
		if (r != null && r instanceof AuditApiResource) {
			AuditApiResource comparer = (AuditApiResource) r;
			if (comparer.getAuditRecords().equals(this.getAuditRecords()) && String.valueOf(comparer.getAuditRecordsForApplication()).equals(String.valueOf(this.getAuditRecordsForApplication())) && String.valueOf(comparer.getAuditRecordsForType()).equals(String.valueOf(this.getAuditRecordsForType())) && String.valueOf(comparer.getAuditRecordsForUser()).equals(String.valueOf(this.getAuditRecordsForUser())) && String.valueOf(comparer.getAuditRecordsForTypeAndApplication()).equals(String.valueOf(this.getAuditRecordsForTypeAndApplication())) && String.valueOf(comparer.getAuditRecordsForTypeAndUserAndApplication()).equals(String.valueOf(this.getAuditRecordsForTypeAndUserAndApplication())) && String.valueOf(comparer.getAuditRecordsForUserAndApplication()).equals(String.valueOf(this.getAuditRecordsForUserAndApplication())) && String.valueOf(comparer.getAuditRecordsForUserAndType()).equals(String.valueOf(this.getAuditRecordsForUserAndType())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
