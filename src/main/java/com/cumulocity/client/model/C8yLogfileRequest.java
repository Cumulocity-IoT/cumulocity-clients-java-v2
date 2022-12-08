// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request a device to send a log file and view it in Cumulocity IoT's log viewer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yLogfileRequest {

	/**
	 * Indicates the log file to select.
	 */
	private String logFile;

	/**
	 * Start date and time of log entries in the log file to be sent.
	 */
	private String dateFrom;

	/**
	 * End date and time of log entries in the log file to be sent.
	 */
	private String dateTo;

	/**
	 * Provide a text that needs to be present in the log entry.
	 */
	private String searchText;

	/**
	 * Upper limit of the number of lines that should be sent to Cumulocity IoT after filtering.
	 */
	private int maximumLines;

	/**
	 * A link to the log file request.
	 */
	private String file;

	public String getLogFile() {
		return logFile;
	}
	
	public void setLogFile(final String logFile) {
		this.logFile = logFile;
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

	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(final String searchText) {
		this.searchText = searchText;
	}

	public int getMaximumLines() {
		return maximumLines;
	}
	
	public void setMaximumLines(final int maximumLines) {
		this.maximumLines = maximumLines;
	}

	public String getFile() {
		return file;
	}
	
	public void setFile(final String file) {
		this.file = file;
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
		if (r != null && r instanceof C8yLogfileRequest) {
			C8yLogfileRequest comparer = (C8yLogfileRequest) r;
			if (String.valueOf(comparer.getLogFile()).equals(String.valueOf(this.getLogFile())) && String.valueOf(comparer.getDateFrom()).equals(String.valueOf(this.getDateFrom())) && String.valueOf(comparer.getDateTo()).equals(String.valueOf(this.getDateTo())) && String.valueOf(comparer.getSearchText()).equals(String.valueOf(this.getSearchText())) && Integer.valueOf(comparer.getMaximumLines()).equals(Integer.valueOf(this.getMaximumLines())) && String.valueOf(comparer.getFile()).equals(String.valueOf(this.getFile()))) {
				return true;
			}
		}
		return false;
	}
}
