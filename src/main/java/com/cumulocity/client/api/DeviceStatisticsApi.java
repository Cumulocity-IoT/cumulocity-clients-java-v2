// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.DeviceStatisticsCollection;

/**
 * <p>Device statistics are collected for each inventory object with at least one measurement, event or alarm. There are no additional checks if the inventory object is marked as device using the <code>c8y_IsDevice</code> fragment. When the first measurement, event or alarm is created for a specific inventory object, Cumulocity IoT is always considering this as a device and starts counting.</p>
 * <p>Device statistics are counted with daily and monthy rate. All requests are considered when counting device statistics, no matter which processing mode is used.</p>
 * <p>The following requests are counted:</p>
 * <ul>
 * 	<li><p>Alarm creation and update</p>
 * 	</li>
 * 	<li><p>Event creation and update</p>
 * 	</li>
 * 	<li><p>Measurement creation</p>
 * 	</li>
 * 	<li><p>Bulk measurement creation is counted as multiple requests</p>
 * 	</li>
 * 	<li><p>Bulk alarm status update is counted as multiple requests</p>
 * 	</li>
 * 	<li><p>MQTT and SmartREST requests with multiple rows are counted as multiple requests</p>
 * 	</li>
 * </ul>
 * <h3>Frequently asked questions</h3>
 * <h4>Are operations on device firmware counted?</h4>
 * <p><strong>No</strong>, device configuration and firmware update operate on inventory objects, hence they are not counted.</p>
 * <h4>Are requests done by the UI applications, for example, when browsing device details, counted?</h4>
 * <p><strong>No</strong>, viewing device details performs only GET requests which are not counted.</p>
 * <h4>Is the clear alarm operation done from the UI counted?</h4>
 * <p><strong>Yes</strong>, a clear alarm operation in fact performs an alarm update operation and it will be counted as device request.</p>
 * <h4>Is there any operation performed on the device which is counted?</h4>
 * <p><strong>Yes</strong>, retrieving device logs requires from the device to create an event and attach a binary with logs to it. Those are two separate requests and both are counted.</p>
 * <h4>When I have a device with children are the requests counted always to the root device or separately for each child?</h4>
 * <p>Separately for each child.</p>
 * <h4>Why do device statistics show significantly smaller request numbers than the total number of created and updated request from usage statistics?</h4>
 * <p>The important aspect here is the moment of recording values for the counters. For inbound data usage statistics we count every request that passed authorization, including invalid requests, as stated in usage statistics description.</p>
 * <p>For device statistics it is different. We count requests after data is successfully stored in the database (or transient), which means the request was valid and there was no problem with persistence.</p>
 * <p>In summary, if you observe that your usage statistics counters are significantly larger than your device statistics counters, there is a good chance that some devices or microservices in your tenant are constantly sending invalid requests. In such a situation, the client should check the state of theirs tenant.</p>
 */
public class DeviceStatisticsApi extends AdaptableApi {

	public DeviceStatisticsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve monthly device statistics</p>
	 * <p>Retrieve monthly device statistics from a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_STATISTICS_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the devices statistics are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param date
	 * <p>Date (format YYYY-MM-dd) of the queried month (the day value is ignored).</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param deviceId
	 * <p>The ID of the device to search for.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<DeviceStatisticsCollection> getMonthlyDeviceStatistics(final String tenantId, final String date, final int currentPage, final String deviceId, final int pageSize, final boolean withTotalPages) {
		return adapt().path("tenant").path("statistics").path("device").path(valueOf(tenantId)).path("monthly").path(valueOf(date))
			.queryParam("currentPage", currentPage)
			.queryParam("deviceId", deviceId)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", DeviceStatisticsCollection.class);
	}
	
	/**
	 * <p>Retrieve daily device statistics</p>
	 * <p>Retrieve daily device statistics from a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_STATISTICS_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the devices statistics are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param date
	 * <p>Date (format YYYY-MM-dd) of the queried day.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param deviceId
	 * <p>The ID of the device to search for.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<DeviceStatisticsCollection> getDailyDeviceStatistics(final String tenantId, final String date, final int currentPage, final String deviceId, final int pageSize, final boolean withTotalPages) {
		return adapt().path("tenant").path("statistics").path("device").path(valueOf(tenantId)).path("daily").path(valueOf(date))
			.queryParam("currentPage", currentPage)
			.queryParam("deviceId", deviceId)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", DeviceStatisticsCollection.class);
	}
}
