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
public class ExternalId {

	/**
	 * <p>The identifier used in the external system that Cumulocity interfaces with.</p>
	 */
	private String externalId;

	/**
	 * <p>The managed object linked to the external ID.</p>
	 */
	private ManagedObject managedObject;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The type of the external identifier.</p>
	 */
	private String type;

	public ExternalId() {
	}

	public ExternalId(final String externalId, final String type) {
		this.externalId = externalId;
		this.type = type;
	}

	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public ManagedObject getManagedObject() {
		return managedObject;
	}
	
	public void setManagedObject(final ManagedObject managedObject) {
		this.managedObject = managedObject;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * <p>The managed object linked to the external ID.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ManagedObject {
	
		/**
		 * <p>Unique identifier of the object.</p>
		 */
		private String id;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
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
			if (r != null && r instanceof ManagedObject) {
				ManagedObject comparer = (ManagedObject) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
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
		if (r != null && r instanceof ExternalId) {
			ExternalId comparer = (ExternalId) r;
			if (String.valueOf(comparer.getExternalId()).equals(String.valueOf(this.getExternalId())) && comparer.getManagedObject().equals(this.getManagedObject()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
				return true;
			}
		}
		return false;
	}
}
