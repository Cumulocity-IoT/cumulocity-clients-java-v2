// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The manifest of the web application.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class WebApplicationManifest {

	/**
	 * <p>A legacy flag that identified a certain type of web application that would control the behavior of plugin tab in the application details view.It is no longer used.</p>
	 */
	@Deprecated
	private boolean pWebpaas;

	/**
	 * <p>The content security policy of the application.</p>
	 */
	private String contentSecurityPolicy;

	/**
	 * <p>A flag that decides if the application is shown in the app switcher on the UI.</p>
	 */
	private boolean noAppSwitcher;

	/**
	 * <p>A flag that decides if the application tabs are displayed horizontally or not.</p>
	 */
	private boolean tabsHorizontal;

	public boolean getPWebpaas() {
		return pWebpaas;
	}
	
	public void setPWebpaas(final boolean pWebpaas) {
		this.pWebpaas = pWebpaas;
	}

	public String getContentSecurityPolicy() {
		return contentSecurityPolicy;
	}
	
	public void setContentSecurityPolicy(final String contentSecurityPolicy) {
		this.contentSecurityPolicy = contentSecurityPolicy;
	}

	public boolean getNoAppSwitcher() {
		return noAppSwitcher;
	}
	
	public void setNoAppSwitcher(final boolean noAppSwitcher) {
		this.noAppSwitcher = noAppSwitcher;
	}

	public boolean getTabsHorizontal() {
		return tabsHorizontal;
	}
	
	public void setTabsHorizontal(final boolean tabsHorizontal) {
		this.tabsHorizontal = tabsHorizontal;
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
		if (r != null && r instanceof WebApplicationManifest) {
			WebApplicationManifest comparer = (WebApplicationManifest) r;
			if (Boolean.valueOf(comparer.getPWebpaas()).equals(Boolean.valueOf(this.getPWebpaas())) && String.valueOf(comparer.getContentSecurityPolicy()).equals(String.valueOf(this.getContentSecurityPolicy())) && Boolean.valueOf(comparer.getNoAppSwitcher()).equals(Boolean.valueOf(this.getNoAppSwitcher())) && Boolean.valueOf(comparer.getTabsHorizontal()).equals(Boolean.valueOf(this.getTabsHorizontal()))) {
				return true;
			}
		}
		return false;
	}
}
