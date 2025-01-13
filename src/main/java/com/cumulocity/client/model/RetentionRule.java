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
public class RetentionRule {

	/**
	 * <p>The data type(s) to which the rule is applied.</p>
	 */
	private DataType dataType;

	/**
	 * <p>Indicates whether the rule is editable or not. It can be updated only by the Management tenant.</p>
	 */
	private boolean editable;

	/**
	 * <p>The fragment type(s) to which the rule is applied. Used by the data types EVENT, MEASUREMENT, OPERATION and BULK_OPERATION.</p>
	 */
	private String fragmentType;

	/**
	 * <p>Unique identifier of the retention rule.</p>
	 */
	private String id;

	/**
	 * <p>Maximum age expressed in number of days.</p>
	 */
	private int maximumAge;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The source(s) to which the rule is applied. Used by all data types.</p>
	 */
	private String source;

	/**
	 * <p>The type(s) to which the rule is applied. Used by the data types ALARM, AUDIT, EVENT and MEASUREMENT.</p>
	 */
	private String type;

	public DataType getDataType() {
		return dataType;
	}
	
	public void setDataType(final DataType dataType) {
		this.dataType = dataType;
	}

	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable(final boolean editable) {
		this.editable = editable;
	}

	public String getFragmentType() {
		return fragmentType;
	}
	
	public void setFragmentType(final String fragmentType) {
		this.fragmentType = fragmentType;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public int getMaximumAge() {
		return maximumAge;
	}
	
	public void setMaximumAge(final int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(final String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	
	/**
	 * <p>The data type(s) to which the rule is applied.</p>
	 */
	public enum DataType {
		@JsonProperty("ALARM")
		ALARM("ALARM"),
		@JsonProperty("AUDIT")
		AUDIT("AUDIT"),
		@JsonProperty("BULK_OPERATION")
		BULKOPERATION("BULK_OPERATION"),
		@JsonProperty("EVENT")
		EVENT("EVENT"),
		@JsonProperty("MEASUREMENT")
		MEASUREMENT("MEASUREMENT"),
		@JsonProperty("OPERATION")
		OPERATION("OPERATION"),
		@JsonProperty("*")
		ALL("*");
	
		private String value;
	
		DataType(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
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
		if (r != null && r instanceof RetentionRule) {
			RetentionRule comparer = (RetentionRule) r;
			if (comparer.getDataType().equals(this.getDataType()) && Boolean.valueOf(comparer.getEditable()).equals(Boolean.valueOf(this.getEditable())) && String.valueOf(comparer.getFragmentType()).equals(String.valueOf(this.getFragmentType())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && Integer.valueOf(comparer.getMaximumAge()).equals(Integer.valueOf(this.getMaximumAge())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getSource()).equals(String.valueOf(this.getSource())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
				return true;
			}
		}
		return false;
	}
}
