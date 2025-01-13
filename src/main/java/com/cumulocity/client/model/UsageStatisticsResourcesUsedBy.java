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
public class UsageStatisticsResourcesUsedBy {

	/**
	 * <p>Reason for calculating statistics of the specified microservice.</p>
	 */
	private String cause;

	/**
	 * <p>Number of CPU usage for a single microservice.</p>
	 */
	private int cpu;

	/**
	 * <p>Number of memory usage for a single microservice.</p>
	 */
	private int memory;

	/**
	 * <p>Name of the microservice.</p>
	 */
	private String name;

	public String getCause() {
		return cause;
	}
	
	public void setCause(final String cause) {
		this.cause = cause;
	}

	public int getCpu() {
		return cpu;
	}
	
	public void setCpu(final int cpu) {
		this.cpu = cpu;
	}

	public int getMemory() {
		return memory;
	}
	
	public void setMemory(final int memory) {
		this.memory = memory;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
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
		if (r != null && r instanceof UsageStatisticsResourcesUsedBy) {
			UsageStatisticsResourcesUsedBy comparer = (UsageStatisticsResourcesUsedBy) r;
			if (String.valueOf(comparer.getCause()).equals(String.valueOf(this.getCause())) && Integer.valueOf(comparer.getCpu()).equals(Integer.valueOf(this.getCpu())) && Integer.valueOf(comparer.getMemory()).equals(Integer.valueOf(this.getMemory())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName()))) {
				return true;
			}
		}
		return false;
	}
}
