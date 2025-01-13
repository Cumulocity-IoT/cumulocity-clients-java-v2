// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Reference to the tenant owning this application. The default value is a reference to the current tenant.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ApplicationOwner {

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	private Tenant tenant;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Tenant getTenant() {
		return tenant;
	}
	
	public void setTenant(final Tenant tenant) {
		this.tenant = tenant;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Tenant {
	
		/**
		 * <p>The tenant ID.</p>
		 */
		private String id;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
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
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId()))) {
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
		if (r != null && r instanceof ApplicationOwner) {
			ApplicationOwner comparer = (ApplicationOwner) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getTenant().equals(this.getTenant())) {
				return true;
			}
		}
		return false;
	}
}
