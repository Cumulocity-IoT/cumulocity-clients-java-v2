// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The term "agent" refers to the piece of software that connects a device with Cumulocity.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yAgent {

	/**
	 * <p>The name of the agent.</p>
	 */
	private String name;

	/**
	 * <p>The version of the agent.</p>
	 */
	private String version;

	/**
	 * <p>The URL of the agent, for example, its code repository.</p>
	 */
	private String url;

	public C8yAgent() {
	}

	public C8yAgent(final String name, final String version) {
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(final String url) {
		this.url = url;
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
		if (r != null && r instanceof C8yAgent) {
			C8yAgent comparer = (C8yAgent) r;
			if (String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion())) && String.valueOf(comparer.getUrl()).equals(String.valueOf(this.getUrl()))) {
				return true;
			}
		}
		return false;
	}
}
