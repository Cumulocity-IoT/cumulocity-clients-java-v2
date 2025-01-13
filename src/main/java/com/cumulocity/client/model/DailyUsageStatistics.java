// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Daily usage statistics.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DailyUsageStatistics {

	/**
	 * <p>Number of created alarms.</p>
	 */
	private int alarmsCreatedCount;

	/**
	 * <p>Number of updates made to the alarms.</p>
	 */
	private int alarmsUpdatedCount;

	/**
	 * <p>Date of this usage statistics object.</p>
	 */
	private String day;

	/**
	 * <p>Number of devices in the tenant identified by the fragment <code>c8y_IsDevice</code>. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private int deviceCount;

	/**
	 * <p>Number of devices which do not have child devices. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private int deviceEndpointCount;

	/**
	 * <p>Number of requests that were issued only by devices against the tenant. Updated every 5 minutes. The following requests are not included:</p>
	 * <ul>
	 * 	<li><p>Requests made to <kbd>/user</kbd>, <kbd>/tenant</kbd> and <kbd>/application</kbd> APIs</p>
	 * 	</li>
	 * 	<li><p>Application related requests (with <code>X-Cumulocity-Application-Key</code> header)</p>
	 * 	</li>
	 * </ul>
	 */
	private int deviceRequestCount;

	/**
	 * <p>Number of devices with children. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private int deviceWithChildrenCount;

	/**
	 * <p>Number of created events.</p>
	 */
	private int eventsCreatedCount;

	/**
	 * <p>Number of updates made to the events.</p>
	 */
	private int eventsUpdatedCount;

	/**
	 * <p>Number of created managed objects.</p>
	 */
	private int inventoriesCreatedCount;

	/**
	 * <p>Number of updates made to the managed objects.</p>
	 */
	private int inventoriesUpdatedCount;

	/**
	 * <p>Number of created measurements.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> Bulk creation of measurements is handled in a way that each measurement is counted individually.</p>
	 * </blockquote>
	 */
	private int measurementsCreatedCount;

	/**
	 * <p>Number of created operations.</p>
	 */
	private int operationsCreatedCount;

	/**
	 * <p>Number of updates made to the operations.</p>
	 */
	private int operationsUpdatedCount;

	/**
	 * <p>Number of requests that were made against the tenant. Updated every 5 minutes. The following requests are not included:</p>
	 * <ul>
	 * 	<li><p>Internal SmartREST requests used to resolve templates</p>
	 * 	</li>
	 * 	<li><p>Internal SLA monitoring requests</p>
	 * 	</li>
	 * 	<li><p>Calls to any <kbd>/health</kbd> endpoint</p>
	 * 	</li>
	 * 	<li><p>Device bootstrap process requests related to configuring and retrieving device credentials</p>
	 * 	</li>
	 * 	<li><p>Microservice SDK internal calls for applications and subscriptions</p>
	 * 	</li>
	 * </ul>
	 */
	private int requestCount;

	/**
	 * <p>Resources usage for each subscribed microservice application.</p>
	 */
	private UsageStatisticsResources resources;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Database storage in use, specified in bytes. It is affected by your retention rules and by the regularly running database optimization functions in Cumulocity IoT. If the size decreases, it does not necessarily mean that data was deleted. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private int storageSize;

	/**
	 * <p>Names of the tenant subscribed applications. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private String[] subscribedApplications;

	/**
	 * <p>Sum of all inbound transfers.</p>
	 */
	private int totalResourceCreateAndUpdateCount;

	public int getAlarmsCreatedCount() {
		return alarmsCreatedCount;
	}
	
	public void setAlarmsCreatedCount(final int alarmsCreatedCount) {
		this.alarmsCreatedCount = alarmsCreatedCount;
	}

	public int getAlarmsUpdatedCount() {
		return alarmsUpdatedCount;
	}
	
	public void setAlarmsUpdatedCount(final int alarmsUpdatedCount) {
		this.alarmsUpdatedCount = alarmsUpdatedCount;
	}

	public String getDay() {
		return day;
	}
	
	public void setDay(final String day) {
		this.day = day;
	}

	public int getDeviceCount() {
		return deviceCount;
	}
	
	public void setDeviceCount(final int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public int getDeviceEndpointCount() {
		return deviceEndpointCount;
	}
	
	public void setDeviceEndpointCount(final int deviceEndpointCount) {
		this.deviceEndpointCount = deviceEndpointCount;
	}

	public int getDeviceRequestCount() {
		return deviceRequestCount;
	}
	
	public void setDeviceRequestCount(final int deviceRequestCount) {
		this.deviceRequestCount = deviceRequestCount;
	}

	public int getDeviceWithChildrenCount() {
		return deviceWithChildrenCount;
	}
	
	public void setDeviceWithChildrenCount(final int deviceWithChildrenCount) {
		this.deviceWithChildrenCount = deviceWithChildrenCount;
	}

	public int getEventsCreatedCount() {
		return eventsCreatedCount;
	}
	
	public void setEventsCreatedCount(final int eventsCreatedCount) {
		this.eventsCreatedCount = eventsCreatedCount;
	}

	public int getEventsUpdatedCount() {
		return eventsUpdatedCount;
	}
	
	public void setEventsUpdatedCount(final int eventsUpdatedCount) {
		this.eventsUpdatedCount = eventsUpdatedCount;
	}

	public int getInventoriesCreatedCount() {
		return inventoriesCreatedCount;
	}
	
	public void setInventoriesCreatedCount(final int inventoriesCreatedCount) {
		this.inventoriesCreatedCount = inventoriesCreatedCount;
	}

	public int getInventoriesUpdatedCount() {
		return inventoriesUpdatedCount;
	}
	
	public void setInventoriesUpdatedCount(final int inventoriesUpdatedCount) {
		this.inventoriesUpdatedCount = inventoriesUpdatedCount;
	}

	public int getMeasurementsCreatedCount() {
		return measurementsCreatedCount;
	}
	
	public void setMeasurementsCreatedCount(final int measurementsCreatedCount) {
		this.measurementsCreatedCount = measurementsCreatedCount;
	}

	public int getOperationsCreatedCount() {
		return operationsCreatedCount;
	}
	
	public void setOperationsCreatedCount(final int operationsCreatedCount) {
		this.operationsCreatedCount = operationsCreatedCount;
	}

	public int getOperationsUpdatedCount() {
		return operationsUpdatedCount;
	}
	
	public void setOperationsUpdatedCount(final int operationsUpdatedCount) {
		this.operationsUpdatedCount = operationsUpdatedCount;
	}

	public int getRequestCount() {
		return requestCount;
	}
	
	public void setRequestCount(final int requestCount) {
		this.requestCount = requestCount;
	}

	public UsageStatisticsResources getResources() {
		return resources;
	}
	
	public void setResources(final UsageStatisticsResources resources) {
		this.resources = resources;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public int getStorageSize() {
		return storageSize;
	}
	
	public void setStorageSize(final int storageSize) {
		this.storageSize = storageSize;
	}

	public String[] getSubscribedApplications() {
		return subscribedApplications;
	}
	
	public void setSubscribedApplications(final String[] subscribedApplications) {
		this.subscribedApplications = subscribedApplications;
	}

	public int getTotalResourceCreateAndUpdateCount() {
		return totalResourceCreateAndUpdateCount;
	}
	
	public void setTotalResourceCreateAndUpdateCount(final int totalResourceCreateAndUpdateCount) {
		this.totalResourceCreateAndUpdateCount = totalResourceCreateAndUpdateCount;
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
		if (r != null && r instanceof DailyUsageStatistics) {
			DailyUsageStatistics comparer = (DailyUsageStatistics) r;
			if (Integer.valueOf(comparer.getAlarmsCreatedCount()).equals(Integer.valueOf(this.getAlarmsCreatedCount())) && Integer.valueOf(comparer.getAlarmsUpdatedCount()).equals(Integer.valueOf(this.getAlarmsUpdatedCount())) && String.valueOf(comparer.getDay()).equals(String.valueOf(this.getDay())) && Integer.valueOf(comparer.getDeviceCount()).equals(Integer.valueOf(this.getDeviceCount())) && Integer.valueOf(comparer.getDeviceEndpointCount()).equals(Integer.valueOf(this.getDeviceEndpointCount())) && Integer.valueOf(comparer.getDeviceRequestCount()).equals(Integer.valueOf(this.getDeviceRequestCount())) && Integer.valueOf(comparer.getDeviceWithChildrenCount()).equals(Integer.valueOf(this.getDeviceWithChildrenCount())) && Integer.valueOf(comparer.getEventsCreatedCount()).equals(Integer.valueOf(this.getEventsCreatedCount())) && Integer.valueOf(comparer.getEventsUpdatedCount()).equals(Integer.valueOf(this.getEventsUpdatedCount())) && Integer.valueOf(comparer.getInventoriesCreatedCount()).equals(Integer.valueOf(this.getInventoriesCreatedCount())) && Integer.valueOf(comparer.getInventoriesUpdatedCount()).equals(Integer.valueOf(this.getInventoriesUpdatedCount())) && Integer.valueOf(comparer.getMeasurementsCreatedCount()).equals(Integer.valueOf(this.getMeasurementsCreatedCount())) && Integer.valueOf(comparer.getOperationsCreatedCount()).equals(Integer.valueOf(this.getOperationsCreatedCount())) && Integer.valueOf(comparer.getOperationsUpdatedCount()).equals(Integer.valueOf(this.getOperationsUpdatedCount())) && Integer.valueOf(comparer.getRequestCount()).equals(Integer.valueOf(this.getRequestCount())) && comparer.getResources().equals(this.getResources()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && Integer.valueOf(comparer.getStorageSize()).equals(Integer.valueOf(this.getStorageSize())) && comparer.getSubscribedApplications().equals(this.getSubscribedApplications()) && Integer.valueOf(comparer.getTotalResourceCreateAndUpdateCount()).equals(Integer.valueOf(this.getTotalResourceCreateAndUpdateCount()))) {
				return true;
			}
		}
		return false;
	}
}
