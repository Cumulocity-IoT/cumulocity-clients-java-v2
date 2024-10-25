// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NewDeviceRequest;
import com.cumulocity.client.model.NewDeviceRequestCollection;

/**
 * <p>API methods to create, retrieve, update and delete new device requests in Cumulocity IoT.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class NewDeviceRequestsApi extends AdaptableApi {

	public NewDeviceRequestsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve a list of new device requests</p>
	 * <p>Retrieve a list of new device requests.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of new device requests sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<NewDeviceRequestCollection> getNewDeviceRequests(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("devicecontrol").path("newDeviceRequests")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.newdevicerequestcollection+json")
			.rx()
			.method("GET", NewDeviceRequestCollection.class);
	}
	
	/**
	 * <p>Create a new device request</p>
	 * <p>Create a new device request.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A new device request was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<NewDeviceRequest> createNewDeviceRequest(final NewDeviceRequest body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "securityToken");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "tenantId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "status");
		return adapt().path("devicecontrol").path("newDeviceRequests")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.newdevicerequest+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("POST", Entity.json(jsonNode), NewDeviceRequest.class);
	}
	
	/**
	 * <p>Retrieve a specific new device request</p>
	 * <p>Retrieve a specific new device request (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the new device request is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>New device request not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param requestId
	 * <p>Unique identifier of the new device request.</p>
	 */
	public CompletionStage<NewDeviceRequest> getNewDeviceRequest(final String requestId) {
		return adapt().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", NewDeviceRequest.class);
	}
	
	/**
	 * <p>Update a specific new device request status</p>
	 * <p>Update a specific new device request (by a given ID).You can only update its status.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A new device request was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>New device request not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param requestId
	 * <p>Unique identifier of the new device request.</p>
	 */
	public CompletionStage<NewDeviceRequest> updateNewDeviceRequest(final NewDeviceRequest body, final String requestId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "groupId");
		removeFromNode(jsonNode, "tenantId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "type");
		return adapt().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.newdevicerequest+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), NewDeviceRequest.class);
	}
	
	/**
	 * <p>Delete a specific new device request</p>
	 * <p>Delete a specific new device request (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A new device request was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>New device request not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param requestId
	 * <p>Unique identifier of the new device request.</p>
	 */
	public CompletionStage<Response> deleteNewDeviceRequest(final String requestId) {
		return adapt().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
