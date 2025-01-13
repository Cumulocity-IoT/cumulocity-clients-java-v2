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
public class RangeStatisticsFile {

	/**
	 * <p>Statistics generation start date.</p>
	 */
	private String dateFrom;

	/**
	 * <p>Statistics generation end date.</p>
	 */
	private String dateTo;

	public RangeStatisticsFile() {
	}

	public RangeStatisticsFile(final String dateFrom, final String dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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
		if (r != null && r instanceof RangeStatisticsFile) {
			RangeStatisticsFile comparer = (RangeStatisticsFile) r;
			if (String.valueOf(comparer.getDateFrom()).equals(String.valueOf(this.getDateFrom())) && String.valueOf(comparer.getDateTo()).equals(String.valueOf(this.getDateTo()))) {
				return true;
			}
		}
		return false;
	}
}
