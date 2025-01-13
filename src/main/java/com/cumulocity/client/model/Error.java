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
public class Error {

	/**
	 * <p>The type of error returned.</p>
	 */
	private String error;

	/**
	 * <p>A human-readable message providing more details about the error.</p>
	 */
	private String message;

	/**
	 * <p>A URI reference [<a href="https://tools.ietf.org/html/rfc3986">RFC3986</a>] that identifies the error code reported.</p>
	 */
	private String info;

	public String getError() {
		return error;
	}
	
	public void setError(final String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(final String message) {
		this.message = message;
	}

	public String getInfo() {
		return info;
	}
	
	public void setInfo(final String info) {
		this.info = info;
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
		if (r != null && r instanceof Error) {
			Error comparer = (Error) r;
			if (String.valueOf(comparer.getError()).equals(String.valueOf(this.getError())) && String.valueOf(comparer.getMessage()).equals(String.valueOf(this.getMessage())) && String.valueOf(comparer.getInfo()).equals(String.valueOf(this.getInfo()))) {
				return true;
			}
		}
		return false;
	}
}
