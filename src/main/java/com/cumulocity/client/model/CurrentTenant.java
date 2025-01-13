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
public class CurrentTenant {

	/**
	 * <p>Indicates if this tenant can create subtenants.</p>
	 */
	private boolean allowCreateTenants;

	/**
	 * <p>Collection of the subscribed applications.</p>
	 */
	private Applications applications;

	/**
	 * <p>An object with a list of custom properties.</p>
	 */
	private CustomProperties customProperties;

	/**
	 * <p>URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots <code>.</code>, hyphens <code>-</code> and underscores <code>_</code>.</p>
	 */
	private String domainName;

	/**
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	private String name;

	/**
	 * <p>ID of the parent tenant.</p>
	 */
	private String parent;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

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

	public CustomProperties getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final CustomProperties customProperties) {
		this.customProperties = customProperties;
	}

	public String getDomainName() {
		return domainName;
	}
	
	public void setDomainName(final String domainName) {
		this.domainName = domainName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
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
	
		public Application[] getReferences() {
			return references;
		}
		
		public void setReferences(final Application[] references) {
			this.references = references;
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
				if (comparer.getReferences().equals(this.getReferences())) {
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
		if (r != null && r instanceof CurrentTenant) {
			CurrentTenant comparer = (CurrentTenant) r;
			if (Boolean.valueOf(comparer.getAllowCreateTenants()).equals(Boolean.valueOf(this.getAllowCreateTenants())) && comparer.getApplications().equals(this.getApplications()) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDomainName()).equals(String.valueOf(this.getDomainName())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getParent()).equals(String.valueOf(this.getParent())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
