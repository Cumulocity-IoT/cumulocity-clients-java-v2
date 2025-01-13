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
public class SubscribedApplicationReference {

	/**
	 * <p>The application to be subscribed to.</p>
	 */
	private Application application;

	public SubscribedApplicationReference() {
	}

	public SubscribedApplicationReference(final Application application) {
		this.application = application;
	}

	public Application getApplication() {
		return application;
	}
	
	public void setApplication(final Application application) {
		this.application = application;
	}

	/**
	 * <p>The application to be subscribed to.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Application {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public Application() {
		}
	
		public Application(final String self) {
			this.self = self;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof Application) {
				Application comparer = (Application) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
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
		if (r != null && r instanceof SubscribedApplicationReference) {
			SubscribedApplicationReference comparer = (SubscribedApplicationReference) r;
			if (comparer.getApplication().equals(this.getApplication())) {
				return true;
			}
		}
		return false;
	}
}
