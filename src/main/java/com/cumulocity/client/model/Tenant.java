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
public class Tenant {

	/**
	 * <p>Email address of the tenant's administrator.</p>
	 */
	private String adminEmail;

	/**
	 * <p>Username of the tenant's administrator.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> When it is provided in the request body, also <code>adminEmail</code> and <code>adminPass</code> must be provided.</p>
	 * </blockquote>
	 */
	private String adminName;

	/**
	 * <p>Password of the tenant's administrator.</p>
	 */
	private String adminPass;

	/**
	 * <p>Indicates if this tenant can create subtenants.</p>
	 */
	private boolean allowCreateTenants;

	/**
	 * <p>Collection of the subscribed applications.</p>
	 */
	private Applications applications;

	/**
	 * <p>Tenant's company name.</p>
	 */
	private String company;

	/**
	 * <p>Name of the contact person.</p>
	 */
	private String contactName;

	/**
	 * <p>Phone number of the contact person, provided in the international format, for example, +48 123 456 7890.</p>
	 */
	private String contactPhone;

	/**
	 * <p>The date and time when the tenant was created.</p>
	 */
	private String creationTime;

	/**
	 * <p>An object with a list of custom properties.</p>
	 */
	private CustomProperties customProperties;

	/**
	 * <p>URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots <code>.</code> and hyphens <code>-</code>.</p>
	 */
	private String domain;

	/**
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	private String id;

	/**
	 * <p>Collection of the owned applications.</p>
	 */
	private OwnedApplications ownedApplications;

	/**
	 * <p>ID of the parent tenant.</p>
	 */
	private String parent;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Current status of the tenant.</p>
	 */
	private Status status;

	public String getAdminEmail() {
		return adminEmail;
	}
	
	public void setAdminEmail(final String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(final String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPass() {
		return adminPass;
	}
	
	public void setAdminPass(final String adminPass) {
		this.adminPass = adminPass;
	}

	public boolean getAllowCreateTenants() {
		return allowCreateTenants;
	}
	
	public void setAllowCreateTenants(final boolean allowCreateTenants) {
		this.allowCreateTenants = allowCreateTenants;
	}

	public Applications getApplications() {
		return applications;
	}
	
	public void setApplications(final Applications applications) {
		this.applications = applications;
	}

	public String getCompany() {
		return company;
	}
	
	public void setCompany(final String company) {
		this.company = company;
	}

	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}
	
	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public CustomProperties getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final CustomProperties customProperties) {
		this.customProperties = customProperties;
	}

	public String getDomain() {
		return domain;
	}
	
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public OwnedApplications getOwnedApplications() {
		return ownedApplications;
	}
	
	public void setOwnedApplications(final OwnedApplications ownedApplications) {
		this.ownedApplications = ownedApplications;
	}

	public String getParent() {
		return parent;
	}
	
	public void setParent(final String parent) {
		this.parent = parent;
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

	
	/**
	 * <p>Current status of the tenant.</p>
	 */
	public enum Status {
		@JsonProperty("ACTIVE")
		ACTIVE("ACTIVE"),
		@JsonProperty("SUSPENDED")
		SUSPENDED("SUSPENDED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * <p>Collection of the subscribed applications.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Applications {
	
		/**
		 * <p>An array containing all subscribed applications.</p>
		 */
		private Application[] references;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public Application[] getReferences() {
			return references;
		}
		
		public void setReferences(final Application[] references) {
			this.references = references;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof Applications) {
				Applications comparer = (Applications) r;
				if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>Collection of the owned applications.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class OwnedApplications {
	
		/**
		 * <p>An array containing all owned applications (only applications with availability MARKET).</p>
		 */
		private Application[] references;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public Application[] getReferences() {
			return references;
		}
		
		public void setReferences(final Application[] references) {
			this.references = references;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof OwnedApplications) {
				OwnedApplications comparer = (OwnedApplications) r;
				if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
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
		if (r != null && r instanceof Tenant) {
			Tenant comparer = (Tenant) r;
			if (String.valueOf(comparer.getAdminEmail()).equals(String.valueOf(this.getAdminEmail())) && String.valueOf(comparer.getAdminName()).equals(String.valueOf(this.getAdminName())) && String.valueOf(comparer.getAdminPass()).equals(String.valueOf(this.getAdminPass())) && Boolean.valueOf(comparer.getAllowCreateTenants()).equals(Boolean.valueOf(this.getAllowCreateTenants())) && comparer.getApplications().equals(this.getApplications()) && String.valueOf(comparer.getCompany()).equals(String.valueOf(this.getCompany())) && String.valueOf(comparer.getContactName()).equals(String.valueOf(this.getContactName())) && String.valueOf(comparer.getContactPhone()).equals(String.valueOf(this.getContactPhone())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDomain()).equals(String.valueOf(this.getDomain())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && comparer.getOwnedApplications().equals(this.getOwnedApplications()) && String.valueOf(comparer.getParent()).equals(String.valueOf(this.getParent())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getStatus().equals(this.getStatus())) {
				return true;
			}
		}
		return false;
	}
}
