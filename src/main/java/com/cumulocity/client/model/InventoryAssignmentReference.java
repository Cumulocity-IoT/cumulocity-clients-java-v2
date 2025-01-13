// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>An inventory role reference.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InventoryAssignmentReference {

	/**
	 * <p>An array of roles that are assigned to the managed object for the user.</p>
	 */
	private Roles[] roles;

	public Roles[] getRoles() {
		return roles;
	}
	
	public void setRoles(final Roles[] roles) {
		this.roles = roles;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Roles {
	
		/**
		 * <p>A unique identifier for this inventory role.</p>
		 */
		private int id;
	
		public int getId() {
			return id;
		}
		
		public void setId(final int id) {
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
			if (r != null && r instanceof Roles) {
				Roles comparer = (Roles) r;
				if (Integer.valueOf(comparer.getId()).equals(Integer.valueOf(this.getId()))) {
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
		if (r != null && r instanceof InventoryAssignmentReference) {
			InventoryAssignmentReference comparer = (InventoryAssignmentReference) r;
			if (comparer.getRoles().equals(this.getRoles())) {
				return true;
			}
		}
		return false;
	}
}
