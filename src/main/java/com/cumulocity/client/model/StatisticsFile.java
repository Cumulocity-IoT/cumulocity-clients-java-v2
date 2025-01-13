// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Statistics file metadata.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class StatisticsFile {

	/**
	 * <p>Unique identifier of the file.</p>
	 */
	private String id;

	/**
	 * <p>Domain name where the statistics file come from.</p>
	 */
	private String instanceName;

	/**
	 * <p>File generation date.</p>
	 */
	private String generationDate;

	/**
	 * <p>Start date or date and time of the statistics attached in the file.</p>
	 */
	private String dateFrom;

	/**
	 * <p>End date or date and time of the statistics attached in the file.</p>
	 */
	private String dateTo;

	/**
	 * <p>The type of statistics:</p>
	 * <ul>
	 * 	<li><p>REAL - generated by the system on the first day of the month and including statistics from the previous month.</p>
	 * 	</li>
	 * 	<li><p>TEST - generated by the user with a time range specified in the query parameters (<code>dateFrom</code>, <code>dateTo</code>).</p>
	 * 	</li>
	 * </ul>
	 */
	private Type type;

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getInstanceName() {
		return instanceName;
	}
	
	public void setInstanceName(final String instanceName) {
		this.instanceName = instanceName;
	}

	public String getGenerationDate() {
		return generationDate;
	}
	
	public void setGenerationDate(final String generationDate) {
		this.generationDate = generationDate;
	}

	public String getDateFrom() {
		return dateFrom;
	}
	
	public void setDateFrom(final String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}
	
	public void setDateTo(final String dateTo) {
		this.dateTo = dateTo;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	
	/**
	 * <p>The type of statistics:</p>
	 * <ul>
	 * 	<li><p>REAL - generated by the system on the first day of the month and including statistics from the previous month.</p>
	 * 	</li>
	 * 	<li><p>TEST - generated by the user with a time range specified in the query parameters (<code>dateFrom</code>, <code>dateTo</code>).</p>
	 * 	</li>
	 * </ul>
	 */
	public enum Type {
		@JsonProperty("REAL")
		REAL("REAL"),
		@JsonProperty("TEST")
		TEST("TEST");
	
		private String value;
	
		Type(final String value) {
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
		if (r != null && r instanceof StatisticsFile) {
			StatisticsFile comparer = (StatisticsFile) r;
			if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getInstanceName()).equals(String.valueOf(this.getInstanceName())) && String.valueOf(comparer.getGenerationDate()).equals(String.valueOf(this.getGenerationDate())) && String.valueOf(comparer.getDateFrom()).equals(String.valueOf(this.getDateFrom())) && String.valueOf(comparer.getDateTo()).equals(String.valueOf(this.getDateTo())) && comparer.getType().equals(this.getType())) {
				return true;
			}
		}
		return false;
	}
}
