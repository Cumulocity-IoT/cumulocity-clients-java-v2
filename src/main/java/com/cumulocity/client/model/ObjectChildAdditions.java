// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A collection of references to child additions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ObjectChildAdditions {

	/**
	 * The total number of child additions. Only present if the value is greater than 0.
	 */
	private int count;

	/**
	 * An array with the references to child devices.
	 */
	private ManagedObjectReferenceTuple[] references;

	/**
	 * Link to this resource's child additions.
	 */
	private String self;

	public int getCount() {
		return count;
	}
	
	public void setCount(final int count) {
		this.count = count;
	}

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
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof ObjectChildAdditions) {
			ObjectChildAdditions comparer = (ObjectChildAdditions) r;
			if (Integer.valueOf(comparer.getCount()).equals(Integer.valueOf(this.getCount())) && comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
