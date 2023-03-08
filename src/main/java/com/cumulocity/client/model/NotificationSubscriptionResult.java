// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
public class NotificationSubscriptionResult {

	/**
	 * <p>The status of the notification subscription deletion.</p>
	 */
	private Result result;

	public Result getResult() {
		return result;
	}
	
	public void setResult(final Result result) {
		this.result = result;
	}

	
	/**
	 * <p>The status of the notification subscription deletion.</p>
	 */
	public enum Result {
		@JsonProperty("DONE")
		DONE("DONE"),
		@JsonProperty("SCHEDULED")
		SCHEDULED("SCHEDULED");
	
		private String value;
	
		Result(final String value) {
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
		if (r != null && r instanceof NotificationSubscriptionResult) {
			NotificationSubscriptionResult comparer = (NotificationSubscriptionResult) r;
			if (comparer.getResult().equals(this.getResult())) {
				return true;
			}
		}
		return false;
	}
}
