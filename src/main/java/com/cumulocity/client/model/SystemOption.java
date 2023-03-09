// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>A tuple storing tenant configuration.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SystemOption {

	/**
	 * <p>Name of the system option category.</p>
	 */
	private String category;

	/**
	 * <p>A unique identifier for this system option.</p>
	 */
	private String key;

	/**
	 * <p>Value of this system option.</p>
	 */
	private String value;

	public String getCategory() {
		return category;
	}
	
	public void setCategory(final String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(final String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(final String value) {
		this.value = value;
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
		if (r != null && r instanceof SystemOption) {
			SystemOption comparer = (SystemOption) r;
			if (String.valueOf(comparer.getCategory()).equals(String.valueOf(this.getCategory())) && String.valueOf(comparer.getKey()).equals(String.valueOf(this.getKey())) && String.valueOf(comparer.getValue()).equals(String.valueOf(this.getValue()))) {
				return true;
			}
		}
		return false;
	}
}
