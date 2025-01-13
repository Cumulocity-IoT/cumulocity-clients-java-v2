// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Operation;
import com.cumulocity.client.model.OperationCollection;

/**
 * <p>API methods to create, retrieve, update and delete operations in Cumulocity IoT.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class OperationsApi extends AdaptableApi {

	public OperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve a list of operations</p>
	 * <p>Retrieve a list of operations.</p>
	 * <p>Notes about operation collections:</p>
	 * <ul>
	 * 	<li><p>The embedded operation object contains <code>deviceExternalIDs</code> only when queried with an <code>agentId</code> parameter.</p>
	 * 	</li>
	 * 	<li><p>The embedded operation object is filled with <code>deviceName</code>, but only when requesting resource: Get a collection of operations.</p>
	 * 	</li>
	 * 	<li><p>Operations are returned in the order of their ascending IDs.</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of operations is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param agentId
	 * <p>An agent ID that may be part of the operation. If this parameter is set, the operation response objects contain the <code>deviceExternalIDs</code> object.</p>
	 * @param bulkOperationId
	 * <p>The bulk operation ID that this operation belongs to.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the operation.</p>
	 * @param dateTo
	 * <p>End date or date and time of the operation.</p>
	 * @param deviceId
	 * <p>The ID of the device the operation is performed for.</p>
	 * @param fragmentType
	 * <p>The type of fragment that must be part of the operation.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param revert
	 * <p>If you are using a range query (that is, at least one of the <code>dateFrom</code> or <code>dateTo</code> parameters is included in the request), then setting <code>revert=true</code> will sort the results by the newest operations first.By default, the results are sorted by the oldest operations first.</p>
	 * @param status
	 * <p>Status of the operation.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<OperationCollection> getOperations(final String agentId, final String bulkOperationId, final int currentPage, final String dateFrom, final String dateTo, final String deviceId, final String fragmentType, final int pageSize, final boolean revert, final String status, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("devicecontrol").path("operations")
			.queryParam("agentId", agentId)
			.queryParam("bulkOperationId", bulkOperationId)
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("deviceId", deviceId)
			.queryParam("fragmentType", fragmentType)
			.queryParam("pageSize", pageSize)
			.queryParam("revert", revert)
			.queryParam("status", status)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operationcollection+json")
			.rx()
			.method("GET", OperationCollection.class);
	}
	
	/**
	 * <p>Create an operation</p>
	 * <p>Create an operation.</p>
	 * <p>It is possible to add custom fragments to operations, for example <code>com_cumulocity_model_WebCamDevice</code> is a custom object of the webcam operation.</p>
	 * <p>There are some custom fragments which are used by web applications (like Device management) to provide additional context to operations, for example:</p>
	 * <ul>
	 * 	<li><p><code>description</code> - this fragment can be used to provide a brief user-friendly description of the operation which is later displayed in the operations list views within web applications.</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the device <b>OR</b> ADMIN permissions on the device
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An operation was created.</p></p>
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
	public CompletionStage<Operation> createOperation(final Operation body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "deviceExternalIDs", "self");
		removeFromNode(jsonNode, "bulkOperationId");
		removeFromNode(jsonNode, "failureReason");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return adapt().path("devicecontrol").path("operations")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.operation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Operation.class);
	}
	
	/**
	 * <p>Delete a list of operations</p>
	 * <p>Delete a list of operations.</p>
	 * <p>The DELETE method allows for deletion of operation collections.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A list of operations was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param agentId
	 * <p>An agent ID that may be part of the operation.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the operation.</p>
	 * @param dateTo
	 * <p>End date or date and time of the operation.</p>
	 * @param deviceId
	 * <p>The ID of the device the operation is performed for.</p>
	 * @param status
	 * <p>Status of the operation.</p>
	 */
	public CompletionStage<Response> deleteOperations(final String xCumulocityProcessingMode, final String agentId, final String dateFrom, final String dateTo, final String deviceId, final String status) {
		return adapt().path("devicecontrol").path("operations")
			.queryParam("agentId", agentId)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("deviceId", deviceId)
			.queryParam("status", status)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a specific operation</p>
	 * <p>Retrieve a specific operation (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the operation is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Operation not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the operation.</p>
	 */
	public CompletionStage<Operation> getOperation(final String id) {
		return adapt().path("devicecontrol").path("operations").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("GET", Operation.class);
	}
	
	/**
	 * <p>Update a specific operation status</p>
	 * <p>Update a specific operation (by a given ID).You can only update its status.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An operation was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Operation not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Validation error.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the operation.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Operation> updateOperation(final Operation body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "deviceExternalIDs", "self");
		removeFromNode(jsonNode, "bulkOperationId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "deviceId");
		return adapt().path("devicecontrol").path("operations").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.operation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Operation.class);
	}
}
