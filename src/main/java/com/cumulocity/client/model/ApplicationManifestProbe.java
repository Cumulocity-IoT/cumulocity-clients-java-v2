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
public class ApplicationManifestProbe {

	/**
	 * <p>The probe failure threshold.</p>
	 */
	private int failureThreshold;

	/**
	 * <p>The probe period in seconds.</p>
	 */
	private int periodSeconds;

	/**
	 * <p>The probe timeout in seconds.</p>
	 */
	private int timeoutSeconds;

	/**
	 * <p>The probe success threshold.</p>
	 */
	private int successThreshold;

	/**
	 * <p>The probe's initial delay in seconds.</p>
	 */
	private int initialDelaySeconds;

	/**
	 * <p>The probe's HTTP GET method information.</p>
	 */
	private HttpGet httpGet;

	public int getFailureThreshold() {
		return failureThreshold;
	}
	
	public void setFailureThreshold(final int failureThreshold) {
		this.failureThreshold = failureThreshold;
	}

	public int getPeriodSeconds() {
		return periodSeconds;
	}
	
	public void setPeriodSeconds(final int periodSeconds) {
		this.periodSeconds = periodSeconds;
	}

	public int getTimeoutSeconds() {
		return timeoutSeconds;
	}
	
	public void setTimeoutSeconds(final int timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	public int getSuccessThreshold() {
		return successThreshold;
	}
	
	public void setSuccessThreshold(final int successThreshold) {
		this.successThreshold = successThreshold;
	}

	public int getInitialDelaySeconds() {
		return initialDelaySeconds;
	}
	
	public void setInitialDelaySeconds(final int initialDelaySeconds) {
		this.initialDelaySeconds = initialDelaySeconds;
	}

	public HttpGet getHttpGet() {
		return httpGet;
	}
	
	public void setHttpGet(final HttpGet httpGet) {
		this.httpGet = httpGet;
	}

	/**
	 * <p>The probe's HTTP GET method information.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class HttpGet {
	
		/**
		 * <p>The HTTP path.</p>
		 */
		private String path;
	
		/**
		 * <p>The HTTP port.</p>
		 */
		private int port;
	
		public String getPath() {
			return path;
		}
		
		public void setPath(final String path) {
			this.path = path;
		}
	
		public int getPort() {
			return port;
		}
		
		public void setPort(final int port) {
			this.port = port;
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
			if (r != null && r instanceof HttpGet) {
				HttpGet comparer = (HttpGet) r;
				if (String.valueOf(comparer.getPath()).equals(String.valueOf(this.getPath())) && Integer.valueOf(comparer.getPort()).equals(Integer.valueOf(this.getPort()))) {
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
		if (r != null && r instanceof ApplicationManifestProbe) {
			ApplicationManifestProbe comparer = (ApplicationManifestProbe) r;
			if (Integer.valueOf(comparer.getFailureThreshold()).equals(Integer.valueOf(this.getFailureThreshold())) && Integer.valueOf(comparer.getPeriodSeconds()).equals(Integer.valueOf(this.getPeriodSeconds())) && Integer.valueOf(comparer.getTimeoutSeconds()).equals(Integer.valueOf(this.getTimeoutSeconds())) && Integer.valueOf(comparer.getSuccessThreshold()).equals(Integer.valueOf(this.getSuccessThreshold())) && Integer.valueOf(comparer.getInitialDelaySeconds()).equals(Integer.valueOf(this.getInitialDelaySeconds())) && comparer.getHttpGet().equals(this.getHttpGet())) {
				return true;
			}
		}
		return false;
	}
}
