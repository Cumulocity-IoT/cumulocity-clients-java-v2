// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Event;
import com.cumulocity.client.model.EventCollection;

/**
 * <p>Events are used to pass real-time information through Cumulocity IoT.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class EventsApi extends AdaptableApi {

	public EventsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all events</p>
	 * <p>Retrieve all events on your tenant.</p>
	 * <p>In case of executing <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a> between an upper and lower boundary, for example, querying using <code>dateFrom</code>���<code>dateTo</code> or <code>createdFrom</code>���<code>createdTo</code>, the newest registered events are returned first. It is possible to change the order using the query parameter <code>revert=true</code>.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all events are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param createdFrom
	 * <p>Start date or date and time of the event's creation (set by the platform during creation).</p>
	 * @param createdTo
	 * <p>End date or date and time of the event's creation (set by the platform during creation).</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the event occurrence (provided by the device).</p>
	 * @param dateTo
	 * <p>End date or date and time of the event occurrence (provided by the device).</p>
	 * @param fragmentType
	 * <p>A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.</p>
	 * @param fragmentValue
	 * <p>Allows filtering events by the fragment's value, but only when provided together with <code>fragmentType</code>.</p>
	 * <p><strong>������ Important:</strong> Only fragments with a string value are supported.</p>
	 * @param lastUpdatedFrom
	 * <p>Start date or date and time of the last update made.</p>
	 * @param lastUpdatedTo
	 * <p>End date or date and time of the last update made.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param revert
	 * <p>If you are using a range query (that is, at least one of the <code>dateFrom</code> or <code>dateTo</code> parameters is included in the request), then setting <code>revert=true</code> will sort the results by the oldest events first.By default, the results are sorted by the newest events first.</p>
	 * @param source
	 * <p>The managed object ID to which the event is associated.</p>
	 * @param type
	 * <p>The type of event to search for.</p>
	 * @param withSourceAssets
	 * <p>When set to <code>true</code> also events for related source assets will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withSourceDevices
	 * <p>When set to <code>true</code> also events for related source devices will be included in the request. When this parameter is provided a <code>source</code> must be specified.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<EventCollection> getEvents(final String createdFrom, final String createdTo, final int currentPage, final String dateFrom, final String dateTo, final String fragmentType, final String fragmentValue, final String lastUpdatedFrom, final String lastUpdatedTo, final int pageSize, final boolean revert, final String source, final String type, final boolean withSourceAssets, final boolean withSourceDevices, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("event").path("events")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("fragmentType", fragmentType)
			.queryParam("fragmentValue", fragmentValue)
			.queryParam("lastUpdatedFrom", lastUpdatedFrom)
			.queryParam("lastUpdatedTo", lastUpdatedTo)
			.queryParam("pageSize", pageSize)
			.queryParam("revert", revert)
			.queryParam("source", source)
			.queryParam("type", type)
			.queryParam("withSourceAssets", withSourceAssets)
			.queryParam("withSourceDevices", withSourceDevices)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.eventcollection+json")
			.rx()
			.method("GET", EventCollection.class);
	}
	
	/**
	 * <p>Create an event</p>
	 * <p>An event must be associated with a source (managed object) identified by an ID.<br>In general, each event consists of:</p>
	 * <ul>
	 * 	<li><p>A type to identify the nature of the event.</p>
	 * 	</li>
	 * 	<li><p>A time stamp to indicate when the event was last updated.</p>
	 * 	</li>
	 * 	<li><p>A description of the event.</p>
	 * 	</li>
	 * 	<li><p>The managed object which originated the event.</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An event was created.</p></p>
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
	public CompletionStage<Event> createEvent(final Event body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("event").path("events")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.event+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Event.class);
	}
	
	/**
	 * <p>Remove event collections</p>
	 * <p>Remove event collections specified by query parameters.</p>
	 * <p>DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when the deleted event has a lot of associated data. After sending the request, the platform starts deleting the associated data in an asynchronous way. Finally, the requested event is deleted after all associated data has been deleted.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> DELETE requires at least one of the following parameters: <code>source</code>, <code>dateFrom</code>, <code>dateTo</code>, <code>createdFrom</code>, <code>createdTo</code>.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A collection of events was removed.</p></p>
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
	 * <p>Start date or date and time of the event's creation (set by the platform during creation).</p>
	 * @param createdTo
	 * <p>End date or date and time of the event's creation (set by the platform during creation).</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the event occurrence (provided by the device).</p>
	 * @param dateTo
	 * <p>End date or date and time of the event occurrence (provided by the device).</p>
	 * @param fragmentType
	 * <p>A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.</p>
	 * @param source
	 * <p>The managed object ID to which the event is associated.</p>
	 * @param type
	 * <p>The type of event to search for.</p>
	 */
	public CompletionStage<Response> deleteEvents(final String xCumulocityProcessingMode, final String createdFrom, final String createdTo, final String dateFrom, final String dateTo, final String fragmentType, final String source, final String type) {
		return adapt().path("event").path("events")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("fragmentType", fragmentType)
			.queryParam("source", source)
			.queryParam("type", type)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a specific event</p>
	 * <p>Retrieve a specific event by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_READ <b>OR</b> owner of the source <b>OR</b> EVENT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the event is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<Event> getEvent(final String id) {
		return adapt().path("event").path("events").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("GET", Event.class);
	}
	
	/**
	 * <p>Update a specific event</p>
	 * <p>Update a specific event by a given ID. Only its text description and custom fragments can be updated.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An event was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Event> updateEvent(final Event body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source");
		removeFromNode(jsonNode, "time");
		removeFromNode(jsonNode, "type");
		return adapt().path("event").path("events").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.event+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Event.class);
	}
	
	/**
	 * <p>Remove a specific event</p>
	 * <p>Remove a specific event by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An event was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> deleteEvent(final String id, final String xCumulocityProcessingMode) {
		return adapt().path("event").path("events").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
