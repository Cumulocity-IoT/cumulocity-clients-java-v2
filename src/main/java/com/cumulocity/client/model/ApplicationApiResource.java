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
public class ApplicationApiResource {

	/**
	 * <p>Collection of all applications..</p>
	 */
	private String applications;

	/**
	 * <p>A reference to a resource of type Application.</p>
	 */
	private String applicationById;

	/**
	 * <p>Read-only collection of all applications with a particular name.</p>
	 */
	private String applicationsByName;

	/**
	 * <p>Read-only collection of all applications subscribed by a particular tenant.</p>
	 */
	private String applicationsByTenant;

	/**
	 * <p>Read-only collection of all applications owned by a particular tenant.</p>
	 */
	private String applicationsByOwner;

	/**
	 * <p>Read-only collection of all applications owned by a particular user.</p>
	 */
	private String applicationsByUser;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public String getApplications() {
		return applications;
	}
	
	public void setApplications(final String applications) {
		this.applications = applications;
	}

	public String getApplicationById() {
		return applicationById;
	}
	
	public void setApplicationById(final String applicationById) {
		this.applicationById = applicationById;
	}

	public String getApplicationsByName() {
		return applicationsByName;
	}
	
	public void setApplicationsByName(final String applicationsByName) {
		this.applicationsByName = applicationsByName;
	}

	public String getApplicationsByTenant() {
		return applicationsByTenant;
	}
	
	public void setApplicationsByTenant(final String applicationsByTenant) {
		this.applicationsByTenant = applicationsByTenant;
	}

	public String getApplicationsByOwner() {
		return applicationsByOwner;
	}
	
	public void setApplicationsByOwner(final String applicationsByOwner) {
		this.applicationsByOwner = applicationsByOwner;
	}

	public String getApplicationsByUser() {
		return applicationsByUser;
	}
	
	public void setApplicationsByUser(final String applicationsByUser) {
		this.applicationsByUser = applicationsByUser;
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
		if (r != null && r instanceof ApplicationApiResource) {
			ApplicationApiResource comparer = (ApplicationApiResource) r;
			if (String.valueOf(comparer.getApplications()).equals(String.valueOf(this.getApplications())) && String.valueOf(comparer.getApplicationById()).equals(String.valueOf(this.getApplicationById())) && String.valueOf(comparer.getApplicationsByName()).equals(String.valueOf(this.getApplicationsByName())) && String.valueOf(comparer.getApplicationsByTenant()).equals(String.valueOf(this.getApplicationsByTenant())) && String.valueOf(comparer.getApplicationsByOwner()).equals(String.valueOf(this.getApplicationsByOwner())) && String.valueOf(comparer.getApplicationsByUser()).equals(String.valueOf(this.getApplicationsByUser())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
