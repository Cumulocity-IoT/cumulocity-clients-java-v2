// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.RangeStatisticsFile;
import com.cumulocity.client.model.TenantUsageStatisticsCollection;
import com.cumulocity.client.model.SummaryTenantUsageStatistics;
import com.cumulocity.client.model.SummaryAllTenantsUsageStatistics;
import com.cumulocity.client.model.TenantUsageStatisticsFileCollection;
import com.cumulocity.client.model.StatisticsFile;

/**
 * <p>Days are counted according to server timezone, so be aware that the tenant usage statistics displaying/filtering may not work correctly when the client is not in the same timezone as the server. However, it is possible to send a request with a time range (using the query parameters <code>dateFrom</code> and <code>dateTo</code>) in zoned format (for example, <code>2020-10-26T03:00:00%2B01:00</code>). Statistics from past days are stored with daily aggregations, which means that for a specific day you get either the statistics for the whole day or none at all.</p>
 * <h3>Request counting in SmartREST and MQTT</h3>
 * <ul>
 * 	<li><p>SmartREST: Each row in a SmartREST request is transformed into a separate HTTP request. For example, if one SmartREST request contains 10 rows, then 10 separate calls are executed, meaning that request count is increased by 10.</p>
 * 	</li>
 * 	<li><p>MQTT: Each row/line counts as a separate request. Creating custom template counts as a single request.</p>
 * 	</li>
 * </ul>
 * <h3>REST specific counting details</h3>
 * <ul>
 * 	<li><p>All counters increase also when the request is invalid, for example, wrong payload or missing permissions.</p>
 * 	</li>
 * 	<li><p>Bulk measurements creation and bulk alarm status update are counted as a single "requestCount"/"deviceRequestCount" and multiple inbound data transfer count.</p>
 * 	</li>
 * </ul>
 * <h3>SmartREST 1.0 specific counting details</h3>
 * <ul>
 * 	<li><p>Invalid SmartREST requests are not counted, for example, when the template doesn't exist.</p>
 * 	</li>
 * 	<li><p>A new template registration is treated as two separate requests. Create a new inventory object which increases "requestCount", "deviceRequestCount" and "inventoriesCreatedCount". There is also a second request which binds the template with X-ID, this increases "requestCount" and "deviceRequestCount".</p>
 * 	</li>
 * 	<li><p>Each row in a SmartREST request is transformed into a separate HTTP request. For example, if one SmartREST request contains 10 rows, then 10 separate calls are executed, meaning that both "requestCount" and "deviceRequestCount" are increased by 10.</p>
 * 	</li>
 * </ul>
 * <h3>MQTT specific counting details</h3>
 * <ul>
 * 	<li><p>Invalid requests are counted, for example, when sending a message with a wrong template ID.</p>
 * 	</li>
 * 	<li><p>Device creation request and automatic device creation are counted.</p>
 * 	</li>
 * 	<li><p>Each row/line counts as a separate request.</p>
 * 	</li>
 * 	<li><p>Creating a custom template counts as a single request, no matter how many rows are sent in the request.</p>
 * 	</li>
 * 	<li><p>There is one special SmartREST 2.0 template (402 Create location update event with device update) which is doing two things in one call, that is, create a new location event and update the location of the device. It is counted as two separate requests.</p>
 * 	</li>
 * </ul>
 * <h3>JSON via MQTT specific counting details</h3>
 * <ul>
 * 	<li><p>Invalid requests are counted, for example, when the message payload is invalid.</p>
 * 	</li>
 * 	<li><p>Bulk creation requests are counted as a single "requestCount"/"deviceRequestCount" and multiple inbound data transfer count.</p>
 * 	</li>
 * 	<li><p>Bulk creation requests with a wrong payload are not counted for inbound data transfer count.</p>
 * 	</li>
 * </ul>
 * <h3>Total inbound data transfer</h3>
 * <p>Inbound data transfer refers to the total number of inbound requests performed to transfer data into the Cumulocity IoT platform. This includes sensor readings, alarms, events, commands and alike that are transferred between devices and the Cumulocity IoT platform using the REST and/or MQTT interfaces. Such an inbound request could also originate from a custom microservice, website or any other client.</p>
 * <p>See the table below for more information on how the counters are increased. Additionally, it shows how inbound data transfers are handled for both MQTT and REST:</p>
 * <p>|Type of transfer|MQTT counter information|REST counter information||:---------------|:-----------------------|:-----------------------||Creation of an <strong>alarm</strong> in one request|One alarm creation is counted.|One alarm creation is counted via REST.||Update of an <strong>alarm</strong> (for example, status change)|One alarm update is counted.|One alarm update is counted via REST.||Creation of <strong>multiple alarms</strong> in one request|Each alarm creation in a single MQTT request will be counted.|Not supported by C8Y (REST does not support creating multiple alarms in one call).||Update of <strong>multiple alarms</strong> (for example, status change) in one request|Each alarm update in a single MQTT request will be counted.|Each alarm that matches the filter is counted as an alarm update (causing multiple updates).||Creation of an <strong>event</strong> in one request|One event creation is counted.|One event creation is counted.||Update of an <strong>event</strong> (for example, text change)|One event update is counted.|One event update is counted.||Creation of <strong>multiple events</strong> in one request|Each event creation in a single MQTT request will be counted.|Not supported by C8Y (REST does not support creating multiple events in one call).||Update of <strong>multiple events</strong> (for example, text change) in one request|Each event update in a single MQTT request will be counted.|Not supported by C8Y (REST does not support updating multiple events in one call).||Creation of a <strong>measurement</strong> in one request|One measurement creation is counted. |One measurement creation is counted.||Creation of <strong>multiple measurements</strong> in one request|Each measurement creation in a single MQTT request will be counted. Example: If MQTT is used to report 5 measurements, the measurementCreated counter will be incremented by five.|REST allows multiple measurements to be created by sending multiple measurements in one call. In this case, each measurement sent via REST is counted individually. The call itself is not counted. For example, if somebody sends 5 measurements via REST in one call, the corresponding counter will be increased by 5. Measurements with multiple series are counted as a singular measurement.||Creation of a <strong>managed object</strong> in one request|One managed object creation is counted.|One managed object creation is counted.||Update of one <strong>managed object</strong> (for example, status change)|One managed object update is counted.|One managed object update is counted.||Update of <strong>multiple managed objects</strong> in one request|Each managed object update in a single MQTT request will be counted.|Not supported by C8Y (REST does not support updating multiple managed objects in one call).||Creation/update of <strong>multiple alarms/measurements/events/inventories</strong> mixed in a single call.|Each MQTT line is processed separately. If it is a creation/update of an event/alarm/measurement/inventory, the corresponding counter is increased by one.|Not supported by the REST API.||Assign/unassign of <strong>child devices and child assets</strong> in one request|One managed object update is counted.|One managed object update is counted.|</p>
 * <h3>Microservice usage statistics</h3>
 * <p>The microservice usage statistics gathers information on the resource usage for tenants for each subscribed application which are collected on a daily base.</p>
 * <p>The microservice usage's information is stored in the <code>resources</code> object.</p>
 * <h3>Frequently asked questions</h3>
 * <h4>Which requests are counted as general "requestCount"?</h4>
 * <p>All requests which the platform receives are counted, including,for example, UI requests, microservices requests, device requests and agents requests. Only a few internal endpoints are not counted:</p>
 * <ul>
 * 	<li><p><code>/health</code> (and all endpoints including this URI fragment, like <code>/tenant/health</code>)</p>
 * 	</li>
 * 	<li><p><code>/application/currentApplication</code> (and all subresources, like <code>/application/currentApplication/subscriptions</code>)</p>
 * 	</li>
 * 	<li><p><code>/tenant/limit</code></p>
 * 	</li>
 * 	<li><p><code>/devicecontrol/deviceCredentials</code></p>
 * 	</li>
 * 	<li><p><code>/inventory/templates</code> (and all subresources)</p>
 * 	</li>
 * </ul>
 * <h4>My devices are not sending any data, but "requestCount" is increasing, and the total number is really big. Why is this happening?</h4>
 * <p>Not only device requests are counted. Every user interaction with UI applications generates some requests to the backend API. Additionally you may have subscribed standard or custom microservices, which also regularly send requests to the platform.</p>
 * <p>Example: If you have four microservices and each microservice sends five requests per minute, this setup creates <code>4 * 5 * 60 * 24 = 28800</code> requests per day. Similar numbers arise if there are multiple users working with the given tenant UI concurrently.</p>
 * <h4>Which requests are counted as "deviceRequestCount"?</h4>
 * <p>All requests from "requestCount" except the following:</p>
 * <ul>
 * 	<li><p>Tenant API requests</p>
 * 	</li>
 * 	<li><p>Application API requests</p>
 * 	</li>
 * 	<li><p>User API requests</p>
 * 	</li>
 * 	<li><p>Requests with the proper HTTP header <code>X-Cumulocity-Application-Key</code>, matching the application key of one of the applications used by a particular tenant</p>
 * 	</li>
 * </ul>
 * <p>The exclusion of the APIs in the list above means that requests to endpoints which start with the mentioned API prefixes are not counted. For example, for the Tenant API the following endpoints are not counted (the list is incomplete):</p>
 * <ul>
 * 	<li><p><code>/tenant/tenants</code></p>
 * 	</li>
 * 	<li><p><code>/tenant/currentTenant</code></p>
 * 	</li>
 * 	<li><p><code>/tenant/statistics</code></p>
 * 	</li>
 * 	<li><p><code>/tenant/options</code></p>
 * 	</li>
 * </ul>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> Each microservice and web application must include the <code>X-Cumulocity-Application-Key</code> header in all requests.Otherwise such requests are counted as device requests which incorrectly affects the "deviceRequestCount" usage metric.</p>
 * </blockquote>
 */
public class UsageStatisticsApi extends AdaptableApi {

	public UsageStatisticsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve statistics of the current tenant</p>
	 * <p>Retrieve usage statistics of the current tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_STATISTICS_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the tenant statistics are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the statistics.</p>
	 * @param dateTo
	 * <p>End date or date and time of the statistics.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<TenantUsageStatisticsCollection> getTenantUsageStatisticsCollectionResource(final int currentPage, final String dateFrom, final String dateTo, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("tenant").path("statistics")
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantusagestatisticscollection+json")
			.rx()
			.method("GET", TenantUsageStatisticsCollection.class);
	}
	
	/**
	 * <p>Retrieve a usage statistics summary</p>
	 * <p>Retrieve a usage statistics summary of a tenant.</p>
	 * <section><h5>Required roles</h5> ROLE_TENANT_STATISTICS_READ <b>OR</b> ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ If the `tenant` request parameter is specified, then the current tenant must be the management tenant <b>OR</b> the parent of the requested `tenant`. </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the usage statistics summary is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param dateFrom
	 * <p>Start date or date and time of the statistics.</p>
	 * @param dateTo
	 * <p>End date or date and time of the statistics.</p>
	 * @param tenant
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<SummaryTenantUsageStatistics> getTenantUsageStatistics(final String dateFrom, final String dateTo, final String tenant) {
		return adapt().path("tenant").path("statistics").path("summary")
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("tenant", tenant)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantusagestatisticssummary+json")
			.rx()
			.method("GET", SummaryTenantUsageStatistics.class);
	}
	
	/**
	 * <p>Retrieve a summary of all usage statistics</p>
	 * <p>Retrieve a summary of all tenants usage statistics.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the usage statistics summary is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param dateFrom
	 * <p>Start date or date and time of the statistics.</p>
	 * @param dateTo
	 * <p>End date or date and time of the statistics.</p>
	 */
	public CompletionStage<SummaryAllTenantsUsageStatistics[]> getTenantsUsageStatistics(final String dateFrom, final String dateTo) {
		return adapt().path("tenant").path("statistics").path("allTenantsSummary")
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", SummaryAllTenantsUsageStatistics[].class);
	}
	
	/**
	 * <p>Retrieve usage statistics files metadata</p>
	 * <p>Retrieve usage statistics summary files report metadata.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> This is only accessible by the Management tenant.</p>
	 * </blockquote>
	 * <p>Date range defines the search criteria for files which have any data inside this range. For example, query containing <code>dateFrom=2023-03-01&dateTo=2023-03-31</code>will return files with statistics from ranges 2023-02-25---2023-03-03, 2023-03-04---2023-03-05, 2023-03-15---2023-04-15, but not the files wherethe whole range of data is outside of queried range, like 2023-02-01---2023-02-27.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the tenant statistics are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the range included in statistics file.</p>
	 * @param dateTo
	 * <p>End date or date and time of the range included in statistics file.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<TenantUsageStatisticsFileCollection> getMetadata(final int currentPage, final String dateFrom, final String dateTo, final int pageSize, final boolean withTotalPages) {
		return adapt().path("tenant").path("statistics").path("files")
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantStatisticsfilecollection+json")
			.rx()
			.method("GET", TenantUsageStatisticsFileCollection.class);
	}
	
	/**
	 * <p>Generate a statistics file report</p>
	 * <p>Generate a TEST statistics file report for a given time range.</p>
	 * <p>There are two types of statistics files:</p>
	 * <ul>
	 * 	<li><p>REAL - generated by the system on the first day of the month and including statistics from the previous month.</p>
	 * 	</li>
	 * 	<li><p>TEST - generated by the user with a time range specified in the query parameters (<code>dateFrom</code>, <code>dateTo</code>).</p>
	 * 	</li>
	 * </ul>
	 * <p><code>dateFrom</code> and <code>dateTo</code> are using daily granularity and each day is stored with respect to local Time Zone of the server.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> This is only accessible by the Management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A statistics file was generated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<StatisticsFile> generateStatisticsFile(final RangeStatisticsFile body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("statistics").path("files")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.tenantstatisticsdate+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantstatisticsfile+json")
			.rx()
			.method("POST", Entity.json(jsonNode), StatisticsFile.class);
	}
	
	/**
	 * <p>Retrieve a usage statistics file</p>
	 * <p>Retrieve a specific usage statistics file (by a given ID).</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> This is only accessible by the Management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the file is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Statistics file not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the statistics file.</p>
	 */
	public CompletionStage<Response> getStatisticsFile(final String id) {
		return adapt().path("tenant").path("statistics").path("files").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
	
	/**
	 * <p>Retrieve the latest usage statistics file</p>
	 * <p>Retrieve the latest usage statistics file with REAL data for a given month.</p>
	 * <p>There are two types of statistics files:</p>
	 * <ul>
	 * 	<li><p>REAL - generated by the system on the first day of the month and includes statistics for the previous month.</p>
	 * 	</li>
	 * 	<li><p>TEST - generated by the user with a time range specified in the query parameters (<code>dateFrom</code>, <code>dateTo</code>).</p>
	 * 	</li>
	 * </ul>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> This is only accessible by the Management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the file is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param month
	 * <p>Date (format YYYY-MM-dd) specifying the month for which the statistics file will be downloaded (the day value is ignored).</p>
	 */
	public CompletionStage<Response> getLatestStatisticsFile(final String month) {
		return adapt().path("tenant").path("statistics").path("files").path("latest").path(valueOf(month))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
}
