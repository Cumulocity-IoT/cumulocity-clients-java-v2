// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EditableOption {

	/**
	 * <p>Indicates if option can be edited.</p>
	 */
	private boolean editable;

	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable(final boolean editable) {
		this.editable = editable;
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
		if (r != null && r instanceof EditableOption) {
			EditableOption comparer = (EditableOption) r;
			if (Boolean.valueOf(comparer.getEditable()).equals(Boolean.valueOf(this.getEditable()))) {
				return true;
			}
		}
		return false;
	}
}
