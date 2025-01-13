// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A collection of references to addition parent objects.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ObjectAdditionParents {

	/**
	 * <p>An array with the references to addition parent objects.</p>
	 */
	private ManagedObjectReferenceTuple[] references;

	/**
	 * <p>Link to this resource's addition parent objects.</p>
	 */
	private String self;

	public ManagedObjectReferenceTuple[] getReferences() {
		return references;
	}
	
	public void setReferences(final ManagedObjectReferenceTuple[] references) {
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
		if (r != null && r instanceof ObjectAdditionParents) {
			ObjectAdditionParents comparer = (ObjectAdditionParents) r;
			if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
