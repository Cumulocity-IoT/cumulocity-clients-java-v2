// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.BulkOperation;
import com.cumulocity.client.model.BulkOperationCollection;

/**
 * <p>The bulk operations API allows to schedule an operation on a group of devices to be executed at a specified time.It is required to specify the delay between the creation of subsequent operations.When the bulk operation is created, it has the status ACTIVE.When all operations are created, the bulk operation has the status COMPLETED.It is also possible to cancel an already created bulk operation by deleting it.</p>
 * <p>When you create a bulk operation, you can run it in two modes:</p>
 * <ul>
 * 	<li><p>If <code>groupId</code> is passed, it works the standard way, that means, it takes devices from a group and schedules operations on them.</p>
 * 	</li>
 * 	<li><p>If <code>failedParentId</code> is passed, it takes the already processed bulk operation by that ID, and schedules operations on devices for which the previous operations failed.</p>
 * 	</li>
 * </ul>
 * <p>Note that passing both <code>groupId</code> and <code>failedParentId</code> will not work, and a bulk operation works with groups of type <code>static</code> and <code>dynamic</code>.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The bulk operations API requires different roles than the rest of the device control API: <code>BULK_OPERATION_READ</code> and <code>BULK_OPERATION_ADMIN</code>.</p>
 * <p>The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class BulkOperationsApi extends AdaptableApi {

	public BulkOperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve a list of bulk operations</p>
	 * <p>Retrieve a list of bulk operations.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of bulk operations sent in the response.</p></p>
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
	 */
	public CompletionStage<BulkOperationCollection> getBulkOperations(final int currentPage, final int pageSize, final boolean withTotalElements) {
		return adapt().path("devicecontrol").path("bulkoperations")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.bulkoperationcollection+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", BulkOperationCollection.class);
	}
	
	/**
	 * <p>Create a bulk operation</p>
	 * <p>Create a bulk operation.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A bulk operation was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<BulkOperation> createBulkOperation(final BulkOperation body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "generalStatus");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "progress");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return adapt().path("devicecontrol").path("bulkoperations")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.bulkoperation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
			.rx()
			.method("POST", Entity.json(jsonNode), BulkOperation.class);
	}
	
	/**
	 * <p>Retrieve a specific bulk operation</p>
	 * <p>Retrieve a specific bulk operation (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the bulk operation is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Bulk operation not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the bulk operation.</p>
	 */
	public CompletionStage<BulkOperation> getBulkOperation(final String id) {
		return adapt().path("devicecontrol").path("bulkoperations").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
			.rx()
			.method("GET", BulkOperation.class);
	}
	
	/**
	 * <p>Update a specific bulk operation</p>
	 * <p>Update a specific bulk operation (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A bulk operation was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Bulk operation not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the bulk operation.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<BulkOperation> updateBulkOperation(final BulkOperation body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "generalStatus");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "progress");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return adapt().path("devicecontrol").path("bulkoperations").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.bulkoperation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), BulkOperation.class);
	}
	
	/**
	 * <p>Delete a specific bulk operation</p>
	 * <p>Delete a specific bulk operation (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A bulk operation was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Bulk operation not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the bulk operation.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> deleteBulkOperation(final String id, final String xCumulocityProcessingMode) {
		return adapt().path("devicecontrol").path("bulkoperations").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
