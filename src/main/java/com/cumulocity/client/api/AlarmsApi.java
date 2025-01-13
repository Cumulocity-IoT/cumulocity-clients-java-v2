// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Alarm;
import com.cumulocity.client.model.AlarmCollection;

/**
 * <p>An alarm represents an event that requires manual action, for example, when the temperature of a fridge increases above a particular threshold.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class AlarmsApi extends AdaptableApi {

	public AlarmsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all alarms</p>
	 * <p>Retrieve all alarms on your tenant, or a specific subset based on queries. The results are sorted by the newest alarms first.</p>
	 * <h4>Query parameters</h4>
	 * <p>The query parameter <code>withTotalPages</code> only works when the user has the ROLE_ALARM_READ role, otherwise it is ignored.</p>
	 * <section><h5>Required roles</h5>
	 * The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are returned. If a user has access to alarms through inventory roles, only those alarms are returned.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all alarms are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param createdFrom
	 * <p>Start date or date and time of the alarm creation.</p>
	 * @param createdTo
	 * <p>End date or date and time of the alarm creation.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the alarm occurrence.</p>
	 * @param dateTo
	 * <p>End date or date and time of the alarm occurrence.</p>
	 * @param lastUpdatedFrom
	 * <p>Start date or date and time of the last update made.</p>
	 * @param lastUpdatedTo
	 * <p>End date or date and time of the last update made.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param resolved
	 * <p>When set to <code>true</code> only alarms with status CLEARED will be fetched, whereas <code>false</code> will fetch all alarms with status ACTIVE or ACKNOWLEDGED. Takes precedence over the <code>status</code> parameter.</p>
	 * @param severity
	 * <p>The severity of the alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm severities at once, comma-separate the values.</p>
	 * @param source
	 * <p>The managed object ID to which the alarm is associated.</p>
	 * @param status
	 * <p>The status of the alarm to search for. Should not be used when <code>resolved</code> parameter is provided.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm statuses at once, comma-separate the values.</p>
	 * @param type
	 * <p>The types of alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.</p>
	 * @param withSourceAssets
	 * <p>When set to <code>true</code> also alarms for related source assets will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withSourceDevices
	 * <p>When set to <code>true</code> also alarms for related source devices will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<AlarmCollection> getAlarms(final String createdFrom, final String createdTo, final int currentPage, final String dateFrom, final String dateTo, final String lastUpdatedFrom, final String lastUpdatedTo, final int pageSize, final boolean resolved, final String[] severity, final String source, final String[] status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("lastUpdatedFrom", lastUpdatedFrom)
			.queryParam("lastUpdatedTo", lastUpdatedTo)
			.queryParam("pageSize", pageSize)
			.queryParam("resolved", resolved)
			.queryParam("severity", severity, false)
			.queryParam("source", source)
			.queryParam("status", status, false)
			.queryParam("type", type, false)
			.queryParam("withSourceAssets", withSourceAssets)
			.queryParam("withSourceDevices", withSourceDevices)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarmcollection+json")
			.rx()
			.method("GET", AlarmCollection.class);
	}
	
	/**
	 * <p>Update alarm collections</p>
	 * <p>Update alarm collections specified by query parameters. At least one query parameter is required to avoid accidentally updating all existing alarms.<br>Currently, only the status of alarms can be modified.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> Since this operation can take considerable time, the request returns after maximum 0.5 seconds of processing, and the update operation continues as a background process in the platform.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_ALARM_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An alarm collection was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 202 <p>An alarm collection is being updated in background.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param createdFrom
	 * <p>Start date or date and time of the alarm creation.</p>
	 * @param createdTo
	 * <p>End date or date and time of the alarm creation.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the alarm occurrence.</p>
	 * @param dateTo
	 * <p>End date or date and time of the alarm occurrence.</p>
	 * @param resolved
	 * <p>When set to <code>true</code> only alarms with status CLEARED will be fetched, whereas <code>false</code> will fetch all alarms with status ACTIVE or ACKNOWLEDGED. Takes precedence over the <code>status</code> parameter.</p>
	 * @param severity
	 * <p>The severity of the alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm severities at once, comma-separate the values.</p>
	 * @param source
	 * <p>The managed object ID to which the alarm is associated.</p>
	 * @param status
	 * <p>The status of the alarm to search for. Should not be used when <code>resolved</code> parameter is provided.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm statuses at once, comma-separate the values.</p>
	 * @param withSourceAssets
	 * <p>When set to <code>true</code> also alarms for related source assets will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withSourceDevices
	 * <p>When set to <code>true</code> also alarms for related source devices will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 */
	public CompletionStage<Response> updateAlarms(final Alarm body, final String xCumulocityProcessingMode, final String createdFrom, final String createdTo, final String dateFrom, final String dateTo, final boolean resolved, final String[] severity, final String source, final String[] status, final boolean withSourceAssets, final boolean withSourceDevices) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "severity");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source");
		removeFromNode(jsonNode, "text");
		removeFromNode(jsonNode, "time");
		removeFromNode(jsonNode, "type");
		return adapt().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", resolved)
			.queryParam("severity", severity, false)
			.queryParam("source", source)
			.queryParam("status", status, false)
			.queryParam("withSourceAssets", withSourceAssets)
			.queryParam("withSourceDevices", withSourceDevices)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Create an alarm</p>
	 * <p>An alarm must be associated with a source (managed object) identified by ID.<br>In general, each alarm may consist of:</p>
	 * <ul>
	 * 	<li><p>A status showing whether the alarm is ACTIVE, ACKNOWLEDGED or CLEARED.</p>
	 * 	</li>
	 * 	<li><p>A time stamp to indicate when the alarm was last updated.</p>
	 * 	</li>
	 * 	<li><p>The severity of the alarm: CRITICAL, MAJOR, MINOR or WARNING.</p>
	 * 	</li>
	 * 	<li><p>A history of changes to the event in form of audit logs.</p>
	 * 	</li>
	 * </ul>
	 * <h3>Alarm suppression</h3>
	 * <p>If the source device is in maintenance mode, the alarm is not created and not reported to the Cumulocity IoT event processing engine. When sending a POST request to create a new alarm and if the source device is in maintenance mode, the self link of the alarm will be:</p>
	 * <pre>
	 * "self": "https://<TENANT_DOMAIN>/alarm/alarms/null"
	 * </pre>
	 * <h3>Alarm de-duplication</h3>
	 * <p>If an ACTIVE or ACKNOWLEDGED alarm with the same source and type exists, no new alarm is created.Instead, the existing alarm is updated by incrementing the <code>count</code> property; the <code>time</code> property is also updated.Any other changes are ignored, and the alarm history is not updated. Alarms with status CLEARED are not de-duplicated.The first occurrence of the alarm is recorded in the <code>firstOccurrenceTime</code> property.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_ALARM_ADMIN <b>OR</b> owner of the source <b>OR</b> ALARM_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An alarm was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Alarm> createAlarm(final Alarm body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("alarm").path("alarms")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Alarm.class);
	}
	
	/**
	 * <p>Remove alarm collections</p>
	 * <p>Remove alarm collections specified by query parameters.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> DELETE requires at least one of the following parameters: <code>source</code>, <code>dateFrom</code>, <code>dateTo</code>, <code>createdFrom</code>, <code>createdTo</code>.Also note that DELETE requests are not synchronous. The response could be returned before the delete request has been completed.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_ALARM_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A collection of alarms was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param createdFrom
	 * <p>Start date or date and time of the alarm creation.</p>
	 * @param createdTo
	 * <p>End date or date and time of the alarm creation.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the alarm occurrence.</p>
	 * @param dateTo
	 * <p>End date or date and time of the alarm occurrence.</p>
	 * @param resolved
	 * <p>When set to <code>true</code> only alarms with status CLEARED will be fetched, whereas <code>false</code> will fetch all alarms with status ACTIVE or ACKNOWLEDGED. Takes precedence over the <code>status</code> parameter.</p>
	 * @param severity
	 * <p>The severity of the alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm severities at once, comma-separate the values.</p>
	 * @param source
	 * <p>The managed object ID to which the alarm is associated.</p>
	 * @param status
	 * <p>The status of the alarm to search for. Should not be used when <code>resolved</code> parameter is provided.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm statuses at once, comma-separate the values.</p>
	 * @param type
	 * <p>The types of alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.</p>
	 * @param withSourceAssets
	 * <p>When set to <code>true</code> also alarms for related source assets will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withSourceDevices
	 * <p>When set to <code>true</code> also alarms for related source devices will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 */
	public CompletionStage<Response> deleteAlarms(final String xCumulocityProcessingMode, final String createdFrom, final String createdTo, final String dateFrom, final String dateTo, final boolean resolved, final String[] severity, final String source, final String[] status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices) {
		return adapt().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", resolved)
			.queryParam("severity", severity, false)
			.queryParam("source", source)
			.queryParam("status", status, false)
			.queryParam("type", type, false)
			.queryParam("withSourceAssets", withSourceAssets)
			.queryParam("withSourceDevices", withSourceDevices)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a specific alarm</p>
	 * <p>Retrieve a specific alarm by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_ALARM_READ <b>OR</b> owner of the source <b>OR</b> ALARM_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the alarm is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Alarm not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the alarm.</p>
	 */
	public CompletionStage<Alarm> getAlarm(final String id) {
		return adapt().path("alarm").path("alarms").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
			.rx()
			.method("GET", Alarm.class);
	}
	
	/**
	 * <p>Update a specific alarm</p>
	 * <p>Update a specific alarm by a given ID.Only text, status, severity and custom properties can be modified. A request will be rejected when non-modifiable properties are provided in the request body.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> Changes to alarms will generate a new audit record. The audit record will include the username and application that triggered the update, if applicable. If the update operation doesn���t change anything (that is, the request body contains data that is identical to the already present in the database), there will be no audit record added and no notifications will be sent.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_ALARM_ADMIN <b>OR</b> owner of the source <b>OR</b> ALARM_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An alarm was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Alarm not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the alarm.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Alarm> updateAlarm(final Alarm body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source");
		removeFromNode(jsonNode, "time");
		removeFromNode(jsonNode, "type");
		return adapt().path("alarm").path("alarms").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Alarm.class);
	}
	
	/**
	 * <p>Retrieve the total number of alarms</p>
	 * <p>Count the total number of active alarms on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are counted. Otherwise, inventory role permissions are used to count the alarms and the limit is 100.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the number of active alarms is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param dateFrom
	 * <p>Start date or date and time of the alarm occurrence.</p>
	 * @param dateTo
	 * <p>End date or date and time of the alarm occurrence.</p>
	 * @param resolved
	 * <p>When set to <code>true</code> only alarms with status CLEARED will be fetched, whereas <code>false</code> will fetch all alarms with status ACTIVE or ACKNOWLEDGED. Takes precedence over the <code>status</code> parameter.</p>
	 * @param severity
	 * <p>The severity of the alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm severities at once, comma-separate the values.</p>
	 * @param source
	 * <p>The managed object ID to which the alarm is associated.</p>
	 * @param status
	 * <p>The status of the alarm to search for. Should not be used when <code>resolved</code> parameter is provided.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm statuses at once, comma-separate the values.</p>
	 * @param type
	 * <p>The types of alarm to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.</p>
	 * @param withSourceAssets
	 * <p>When set to <code>true</code> also alarms for related source assets will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withSourceDevices
	 * <p>When set to <code>true</code> also alarms for related source devices will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 */
	public CompletionStage<Integer> getNumberOfAlarms(final String dateFrom, final String dateTo, final boolean resolved, final String[] severity, final String source, final String[] status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices) {
		return adapt().path("alarm").path("alarms").path("count")
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", resolved)
			.queryParam("severity", severity, false)
			.queryParam("source", source)
			.queryParam("status", status, false)
			.queryParam("type", type, false)
			.queryParam("withSourceAssets", withSourceAssets)
			.queryParam("withSourceDevices", withSourceDevices)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
			.rx()
			.method("GET", Integer.class);
	}
}
