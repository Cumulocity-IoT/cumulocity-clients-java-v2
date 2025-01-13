// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ChildOperationsAddOne;
import com.cumulocity.client.model.ChildOperationsAddMultiple;
import com.cumulocity.client.model.ManagedObject;
import com.cumulocity.client.model.ManagedObjectReferenceCollection;
import com.cumulocity.client.model.ManagedObjectReference;

/**
 * <p>Managed objects can contain collections of references to child devices, additions and assets.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class ChildOperationsApi extends AdaptableApi {

	public ChildOperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all child additions of a specific managed object</p>
	 * <p>Retrieve all child additions of a specific managed object by a given ID, or a subset based on queries.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all child additions are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param query
	 * <p>Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in <a href="#tag/Query-language">Query language</a>.</p>
	 * @param withChildren
	 * <p>Determines if children with ID and name should be returned when fetching the managed object. Set it to <code>false</code> to improve query performance.</p>
	 * @param withChildrenCount
	 * <p>When set to <code>true</code>, the returned result will contain the total number of children in the respective objects (<code>childAdditions</code>, <code>childAssets</code> and <code>childDevices</code>).</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<ManagedObjectReferenceCollection> getChildAdditions(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.rx()
			.method("GET", ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * <p>Assign a managed object as child addition</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child addition.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAddition(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child addition</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child addition.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAddition(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child addition</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child addition.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAddition(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "c8y_LatestMeasurements");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Remove specific child additions from its parent</p>
	 * <p>Remove specific child additions (by given child IDs) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>Child additions were removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildAdditions(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("DELETE", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Retrieve a specific child addition of a specific managed object</p>
	 * <p>Retrieve a specific child addition (by a given child ID) of a specific managed object (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the child addition is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 */
	public CompletionStage<ManagedObjectReference> getChildAddition(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ManagedObjectReference.class);
	}
	
	/**
	 * <p>Remove a specific child addition from its parent</p>
	 * <p>Remove a specific child addition (by a given child ID) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A child addition was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildAddition(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve all child assets of a specific managed object</p>
	 * <p>Retrieve all child assets of a specific managed object by a given ID, or a subset based on queries.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all child assets are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param query
	 * <p>Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in <a href="#tag/Query-language">Query language</a>.</p>
	 * @param withChildren
	 * <p>Determines if children with ID and name should be returned when fetching the managed object. Set it to <code>false</code> to improve query performance.</p>
	 * @param withChildrenCount
	 * <p>When set to <code>true</code>, the returned result will contain the total number of children in the respective objects (<code>childAdditions</code>, <code>childAssets</code> and <code>childDevices</code>).</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<ManagedObjectReferenceCollection> getChildAssets(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.rx()
			.method("GET", ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * <p>Assign a managed object as child asset</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child asset.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAsset(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child asset</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child asset.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAsset(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child asset</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child asset.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildAsset(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "c8y_LatestMeasurements");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Remove specific child assets from its parent</p>
	 * <p>Remove specific child assets (by given child IDs) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>Child assets were removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildAssets(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("DELETE", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Retrieve a specific child asset of a specific managed object</p>
	 * <p>Retrieve a specific child asset (by a given child ID) of a specific managed object (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the child asset is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 */
	public CompletionStage<ManagedObjectReference> getChildAsset(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ManagedObjectReference.class);
	}
	
	/**
	 * <p>Remove a specific child asset from its parent</p>
	 * <p>Remove a specific child asset (by a given child ID) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A child asset was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildAsset(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve all child devices of a specific managed object</p>
	 * <p>Retrieve all child devices of a specific managed object by a given ID, or a subset based on queries.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all child devices are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param query
	 * <p>Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in <a href="#tag/Query-language">Query language</a>.</p>
	 * @param withChildren
	 * <p>Determines if children with ID and name should be returned when fetching the managed object. Set it to <code>false</code> to improve query performance.</p>
	 * @param withChildrenCount
	 * <p>When set to <code>true</code>, the returned result will contain the total number of children in the respective objects (<code>childAdditions</code>, <code>childAssets</code> and <code>childDevices</code>).</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<ManagedObjectReferenceCollection> getChildDevices(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.rx()
			.method("GET", ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * <p>Assign a managed object as child device</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child device.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildDevice(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child device</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child device.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildDevice(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Assign a managed object as child device</p>
	 * <p>The possible ways to assign child objects are:</p>
	 * <ul>
	 * 	<li><p>Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).</p>
	 * 	</li>
	 * 	<li><p>Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was assigned as child device.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> assignAsChildDevice(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "c8y_LatestMeasurements");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Remove specific child devices from its parent</p>
	 * <p>Remove specific child devices (by given child IDs) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>Child devices were removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildDevices(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.rx()
			.method("DELETE", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Retrieve a specific child device of a specific managed object</p>
	 * <p>Retrieve a specific child device (by a given child ID) of a specific managed object (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the child device is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 */
	public CompletionStage<ManagedObjectReference> getChildDevice(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ManagedObjectReference.class);
	}
	
	/**
	 * <p>Remove a specific child device from its parent</p>
	 * <p>Remove a specific child device (by a given child ID) from its parent (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A child device was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param childId
	 * <p>Unique identifier of the child object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> unassignChildDevice(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
