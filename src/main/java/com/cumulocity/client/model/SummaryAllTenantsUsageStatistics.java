// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SummaryAllTenantsUsageStatistics {

	/**
	 * <p>Number of created alarms.</p>
	 */
	private int alarmsCreatedCount;

	/**
	 * <p>Number of updates made to the alarms.</p>
	 */
	private int alarmsUpdatedCount;

	/**
	 * <p>Date and time of the tenant's creation.</p>
	 */
	private String creationTime;

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
	 * <p>Tenant external reference.</p>
	 */
	private String externalReference;

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
	 * <p>ID of the parent tenant.</p>
	 */
	private String parentTenantId;

	/**
	 * <p>Peak value of <code>deviceCount</code> calculated for the requested time period of the summary.</p>
	 */
	private int peakDeviceCount;

	/**
	 * <p>Peak value of <code>deviceWithChildrenCount</code> calculated for the requested time period of the summary.</p>
	 */
	private int peakDeviceWithChildrenCount;

	/**
	 * <p>Peak value of used storage size in bytes, calculated for the requested time period of the summary.</p>
	 */
	private int peakStorageSize;

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
	 * <p>Database storage in use, specified in bytes. It is affected by your retention rules and by the regularly running database optimization functions in Cumulocity IoT. If the size decreases, it does not necessarily mean that data was deleted. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private int storageSize;

	/**
	 * <p>Names of the tenant subscribed applications. Updated only three times a day starting at 8:57, 16:57 and 23:57.</p>
	 */
	private String[] subscribedApplications;

	/**
	 * <p>The tenant's company name.</p>
	 */
	private String tenantCompany;

	/**
	 * <p>An object with a list of custom properties.</p>
	 */
	private CustomProperties tenantCustomProperties;

	/**
	 * <p>URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots <code>.</code>, hyphens <code>-</code> and underscores <code>_</code>.</p>
	 */
	private String tenantDomain;

	/**
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	private String tenantId;

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

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
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

	public String getExternalReference() {
		return externalReference;
	}
	
	public void setExternalReference(final String externalReference) {
		this.externalReference = externalReference;
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

	public String getParentTenantId() {
		return parentTenantId;
	}
	
	public void setParentTenantId(final String parentTenantId) {
		this.parentTenantId = parentTenantId;
	}

	public int getPeakDeviceCount() {
		return peakDeviceCount;
	}
	
	public void setPeakDeviceCount(final int peakDeviceCount) {
		this.peakDeviceCount = peakDeviceCount;
	}

	public int getPeakDeviceWithChildrenCount() {
		return peakDeviceWithChildrenCount;
	}
	
	public void setPeakDeviceWithChildrenCount(final int peakDeviceWithChildrenCount) {
		this.peakDeviceWithChildrenCount = peakDeviceWithChildrenCount;
	}

	public int getPeakStorageSize() {
		return peakStorageSize;
	}
	
	public void setPeakStorageSize(final int peakStorageSize) {
		this.peakStorageSize = peakStorageSize;
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

	public String getTenantCompany() {
		return tenantCompany;
	}
	
	public void setTenantCompany(final String tenantCompany) {
		this.tenantCompany = tenantCompany;
	}

	public CustomProperties getTenantCustomProperties() {
		return tenantCustomProperties;
	}
	
	public void setTenantCustomProperties(final CustomProperties tenantCustomProperties) {
		this.tenantCustomProperties = tenantCustomProperties;
	}

	public String getTenantDomain() {
		return tenantDomain;
	}
	
	public void setTenantDomain(final String tenantDomain) {
		this.tenantDomain = tenantDomain;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
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
		if (r != null && r instanceof SummaryAllTenantsUsageStatistics) {
			SummaryAllTenantsUsageStatistics comparer = (SummaryAllTenantsUsageStatistics) r;
			if (Integer.valueOf(comparer.getAlarmsCreatedCount()).equals(Integer.valueOf(this.getAlarmsCreatedCount())) && Integer.valueOf(comparer.getAlarmsUpdatedCount()).equals(Integer.valueOf(this.getAlarmsUpdatedCount())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && Integer.valueOf(comparer.getDeviceCount()).equals(Integer.valueOf(this.getDeviceCount())) && Integer.valueOf(comparer.getDeviceEndpointCount()).equals(Integer.valueOf(this.getDeviceEndpointCount())) && Integer.valueOf(comparer.getDeviceRequestCount()).equals(Integer.valueOf(this.getDeviceRequestCount())) && Integer.valueOf(comparer.getDeviceWithChildrenCount()).equals(Integer.valueOf(this.getDeviceWithChildrenCount())) && String.valueOf(comparer.getExternalReference()).equals(String.valueOf(this.getExternalReference())) && Integer.valueOf(comparer.getEventsCreatedCount()).equals(Integer.valueOf(this.getEventsCreatedCount())) && Integer.valueOf(comparer.getEventsUpdatedCount()).equals(Integer.valueOf(this.getEventsUpdatedCount())) && Integer.valueOf(comparer.getInventoriesCreatedCount()).equals(Integer.valueOf(this.getInventoriesCreatedCount())) && Integer.valueOf(comparer.getInventoriesUpdatedCount()).equals(Integer.valueOf(this.getInventoriesUpdatedCount())) && Integer.valueOf(comparer.getMeasurementsCreatedCount()).equals(Integer.valueOf(this.getMeasurementsCreatedCount())) && Integer.valueOf(comparer.getOperationsCreatedCount()).equals(Integer.valueOf(this.getOperationsCreatedCount())) && Integer.valueOf(comparer.getOperationsUpdatedCount()).equals(Integer.valueOf(this.getOperationsUpdatedCount())) && String.valueOf(comparer.getParentTenantId()).equals(String.valueOf(this.getParentTenantId())) && Integer.valueOf(comparer.getPeakDeviceCount()).equals(Integer.valueOf(this.getPeakDeviceCount())) && Integer.valueOf(comparer.getPeakDeviceWithChildrenCount()).equals(Integer.valueOf(this.getPeakDeviceWithChildrenCount())) && Integer.valueOf(comparer.getPeakStorageSize()).equals(Integer.valueOf(this.getPeakStorageSize())) && Integer.valueOf(comparer.getRequestCount()).equals(Integer.valueOf(this.getRequestCount())) && comparer.getResources().equals(this.getResources()) && Integer.valueOf(comparer.getStorageSize()).equals(Integer.valueOf(this.getStorageSize())) && comparer.getSubscribedApplications().equals(this.getSubscribedApplications()) && String.valueOf(comparer.getTenantCompany()).equals(String.valueOf(this.getTenantCompany())) && comparer.getTenantCustomProperties().equals(this.getTenantCustomProperties()) && String.valueOf(comparer.getTenantDomain()).equals(String.valueOf(this.getTenantDomain())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && Integer.valueOf(comparer.getTotalResourceCreateAndUpdateCount()).equals(Integer.valueOf(this.getTotalResourceCreateAndUpdateCount()))) {
				return true;
			}
		}
		return false;
	}
}
