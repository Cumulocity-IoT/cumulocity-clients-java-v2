// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>All available system options of the tenant.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SystemOptionCollection {

	/**
	 * <p>An array containing the predefined system options.</p>
	 */
	private SystemOption[] options;

	public SystemOption[] getOptions() {
		return options;
	}
	
	public void setOptions(final SystemOption[] options) {
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
		if (r != null && r instanceof SystemOptionCollection) {
			SystemOptionCollection comparer = (SystemOptionCollection) r;
			if (comparer.getOptions().equals(this.getOptions())) {
				return true;
			}
		}
		return false;
	}
}
