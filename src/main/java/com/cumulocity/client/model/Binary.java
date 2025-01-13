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
public class Binary {

	/**
	 * <p>Fragment to identify this managed object as a file.</p>
	 */
	@JsonProperty(value = "c8y_IsBinary")
	private C8yIsBinary c8yIsBinary;

	/**
	 * <p>Media type of the file.</p>
	 */
	private String contentType;

	/**
	 * <p>Unique identifier of the object.</p>
	 */
	private String id;

	/**
	 * <p>Date and time of the file's last update.</p>
	 */
	private String lastUpdated;

	/**
	 * <p>Size of the file in bytes.</p>
	 */
	private int length;

	/**
	 * <p>Name of the managed object. It is set from the <code>object</code> contained in the payload.</p>
	 */
	private String name;

	/**
	 * <p>Username of the owner of the file.</p>
	 */
	private String owner;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Type of the managed object. It is set from the <code>object</code> contained in the payload.</p>
	 */
	private String type;

	public C8yIsBinary getC8yIsBinary() {
		return c8yIsBinary;
	}
	
	public void setC8yIsBinary(final C8yIsBinary c8yIsBinary) {
		this.c8yIsBinary = c8yIsBinary;
	}

	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(final String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getLength() {
		return length;
	}
	
	public void setLength(final int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(final String owner) {
		this.owner = owner;
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
	 * <p>Fragment to identify this managed object as a file.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yIsBinary {
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
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
		if (r != null && r instanceof Binary) {
			Binary comparer = (Binary) r;
			if (comparer.getC8yIsBinary().equals(this.getC8yIsBinary()) && String.valueOf(comparer.getContentType()).equals(String.valueOf(this.getContentType())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastUpdated()).equals(String.valueOf(this.getLastUpdated())) && Integer.valueOf(comparer.getLength()).equals(Integer.valueOf(this.getLength())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getOwner()).equals(String.valueOf(this.getOwner())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
				return true;
			}
		}
		return false;
	}
}
