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
public class ChildOperationsAddMultiple {

	/**
	 * <p>An array containing the IDs of the managed objects (children).</p>
	 */
	private References[] references;

	public ChildOperationsAddMultiple() {
	}

	public ChildOperationsAddMultiple(final References[] references) {
		this.references = references;
	}

	public References[] getReferences() {
		return references;
	}
	
	public void setReferences(final References[] references) {
		this.references = references;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class References {
	
		private ManagedObject managedObject;
	
		public ManagedObject getManagedObject() {
			return managedObject;
		}
		
		public void setManagedObject(final ManagedObject managedObject) {
			this.managedObject = managedObject;
		}
	
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class ManagedObject {
		
			/**
			 * <p>Unique identifier of the object.</p>
			 */
			private String id;
		
			public ManagedObject() {
			}
		
			public ManagedObject(final String id) {
				this.id = id;
			}
		
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
				if (r != null && r instanceof ManagedObject) {
					ManagedObject comparer = (ManagedObject) r;
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
			if (r != null && r instanceof References) {
				References comparer = (References) r;
				if (comparer.getManagedObject().equals(this.getManagedObject())) {
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
		if (r != null && r instanceof ChildOperationsAddMultiple) {
			ChildOperationsAddMultiple comparer = (ChildOperationsAddMultiple) r;
			if (comparer.getReferences().equals(this.getReferences())) {
				return true;
			}
		}
		return false;
	}
}
