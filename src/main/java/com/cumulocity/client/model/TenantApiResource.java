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
public class TenantApiResource {

	/**
	 * <p>Collection of tenant options</p>
	 */
	private Options options;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Collection of subtenants</p>
	 */
	private Tenants tenants;

	/**
	 * <p>Retrieves subscribed applications.</p>
	 */
	private String tenantApplications;

	/**
	 * <p>Represents an individual application reference that can be viewed.</p>
	 */
	private String tenantApplicationForId;

	/**
	 * <p>Represents an individual tenant that can be viewed.</p>
	 */
	private String tenantForId;

	/**
	 * <p>Represents a category of tenant options.</p>
	 */
	private String tenantOptionsForCategory;

	/**
	 * <p>Retrieves a key of the category of tenant options.</p>
	 */
	private String tenantOptionForCategoryAndKey;

	/**
	 * <p>Retrieves the tenant system options.</p>
	 */
	private String tenantSystemOptions;

	/**
	 * <p>Retrieves the tenant system options based on category and key.</p>
	 */
	private String tenantSystemOptionsForCategoryAndKey;

	public Options getOptions() {
		return options;
	}
	
	public void setOptions(final Options options) {
		this.options = options;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Tenants getTenants() {
		return tenants;
	}
	
	public void setTenants(final Tenants tenants) {
		this.tenants = tenants;
	}

	public String getTenantApplications() {
		return tenantApplications;
	}
	
	public void setTenantApplications(final String tenantApplications) {
		this.tenantApplications = tenantApplications;
	}

	public String getTenantApplicationForId() {
		return tenantApplicationForId;
	}
	
	public void setTenantApplicationForId(final String tenantApplicationForId) {
		this.tenantApplicationForId = tenantApplicationForId;
	}

	public String getTenantForId() {
		return tenantForId;
	}
	
	public void setTenantForId(final String tenantForId) {
		this.tenantForId = tenantForId;
	}

	public String getTenantOptionsForCategory() {
		return tenantOptionsForCategory;
	}
	
	public void setTenantOptionsForCategory(final String tenantOptionsForCategory) {
		this.tenantOptionsForCategory = tenantOptionsForCategory;
	}

	public String getTenantOptionForCategoryAndKey() {
		return tenantOptionForCategoryAndKey;
	}
	
	public void setTenantOptionForCategoryAndKey(final String tenantOptionForCategoryAndKey) {
		this.tenantOptionForCategoryAndKey = tenantOptionForCategoryAndKey;
	}

	public String getTenantSystemOptions() {
		return tenantSystemOptions;
	}
	
	public void setTenantSystemOptions(final String tenantSystemOptions) {
		this.tenantSystemOptions = tenantSystemOptions;
	}

	public String getTenantSystemOptionsForCategoryAndKey() {
		return tenantSystemOptionsForCategoryAndKey;
	}
	
	public void setTenantSystemOptionsForCategoryAndKey(final String tenantSystemOptionsForCategoryAndKey) {
		this.tenantSystemOptionsForCategoryAndKey = tenantSystemOptionsForCategoryAndKey;
	}

	/**
	 * <p>Collection of tenant options</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Options {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		private Option[] options;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public Option[] getOptions() {
			return options;
		}
		
		public void setOptions(final Option[] options) {
			this.options = options;
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
			if (r != null && r instanceof Options) {
				Options comparer = (Options) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getOptions().equals(this.getOptions())) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>Collection of subtenants</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Tenants {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		private Tenant[] tenants;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public Tenant[] getTenants() {
			return tenants;
		}
		
		public void setTenants(final Tenant[] tenants) {
			this.tenants = tenants;
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
			if (r != null && r instanceof Tenants) {
				Tenants comparer = (Tenants) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getTenants().equals(this.getTenants())) {
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
		if (r != null && r instanceof TenantApiResource) {
			TenantApiResource comparer = (TenantApiResource) r;
			if (comparer.getOptions().equals(this.getOptions()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getTenants().equals(this.getTenants()) && String.valueOf(comparer.getTenantApplications()).equals(String.valueOf(this.getTenantApplications())) && String.valueOf(comparer.getTenantApplicationForId()).equals(String.valueOf(this.getTenantApplicationForId())) && String.valueOf(comparer.getTenantForId()).equals(String.valueOf(this.getTenantForId())) && String.valueOf(comparer.getTenantOptionsForCategory()).equals(String.valueOf(this.getTenantOptionsForCategory())) && String.valueOf(comparer.getTenantOptionForCategoryAndKey()).equals(String.valueOf(this.getTenantOptionForCategoryAndKey())) && String.valueOf(comparer.getTenantSystemOptions()).equals(String.valueOf(this.getTenantSystemOptions())) && String.valueOf(comparer.getTenantSystemOptionsForCategoryAndKey()).equals(String.valueOf(this.getTenantSystemOptionsForCategoryAndKey()))) {
				return true;
			}
		}
		return false;
	}
}
