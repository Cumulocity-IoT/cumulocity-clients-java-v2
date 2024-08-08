// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.InventoryRole;
import com.cumulocity.client.model.InventoryAssignment;
import com.cumulocity.client.model.InventoryAssignmentReference;
import com.cumulocity.client.model.InventoryRoleCollection;
import com.cumulocity.client.model.InventoryAssignmentCollection;

/**
 * <p>API methods to create, retrieve, update and delete inventory roles.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class InventoryRolesApi extends AdaptableApi {

	public InventoryRolesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all inventory roles</p>
	 * <p>Retrieve all inventory roles.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and all inventory roles are sent in the response.</p></p>
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
	 */
	public CompletionStage<InventoryRoleCollection> getInventoryRoles(final int currentPage, final int pageSize, final boolean withTotalElements) {
		return adapt().path("user").path("inventoryroles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryrolecollection+json")
			.rx()
			.method("GET", InventoryRoleCollection.class);
	}
	
	/**
	 * <p>Create an inventory role</p>
	 * <p>Create an inventory role.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An inventory role was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate ��� The inventory role already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<InventoryRole> createInventoryRole(final InventoryRole body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path("inventoryroles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryrole+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("POST", Entity.json(jsonNode), InventoryRole.class);
	}
	
	/**
	 * <p>Retrieve a specific inventory role</p>
	 * <p>Retrieve a specific inventory role (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the inventory role
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and the inventory role is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the inventory role.</p>
	 */
	public CompletionStage<InventoryRole> getInventoryRole(final int id) {
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", InventoryRole.class);
	}
	
	/**
	 * <p>Update a specific inventory role</p>
	 * <p>Update a specific inventory role (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An inventory role was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the inventory role.</p>
	 */
	public CompletionStage<InventoryRole> updateInventoryRole(final InventoryRole body, final int id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryrole+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), InventoryRole.class);
	}
	
	/**
	 * <p>Remove a specific inventory role</p>
	 * <p>Remove a specific inventory role (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An inventory role was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the inventory role.</p>
	 */
	public CompletionStage<Response> deleteInventoryRole(final int id) {
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve all inventory roles assigned to a user</p>
	 * <p>Retrieve all inventory roles assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is the parent of the user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the inventory roles are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>User not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<InventoryAssignmentCollection> getUserInventoryRoles(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignmentcollection+json")
			.rx()
			.method("GET", InventoryAssignmentCollection.class);
	}
	
	/**
	 * <p>Assign an inventory role to a user</p>
	 * <p>Assign an existing inventory role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign any inventory role to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign inventory roles accessible by the parent of the assigned user to non-root users in a user hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to assign inventory roles accessible by the current user <b>AND</b> accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An inventory role was assigned to a user.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>User not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<InventoryAssignment> assignUserInventoryRole(final InventoryAssignment body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.rx()
			.method("POST", Entity.json(jsonNode), InventoryAssignment.class);
	}
	
	/**
	 * <p>Retrieve a specific inventory role assigned to a user</p>
	 * <p>Retrieve a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is the parent of the user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the inventory role is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 * @param id
	 * <p>Unique identifier of the inventory assignment.</p>
	 */
	public CompletionStage<InventoryAssignment> getUserInventoryRole(final String tenantId, final String userId, final int id) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.rx()
			.method("GET", InventoryAssignment.class);
	}
	
	/**
	 * <p>Update a specific inventory role assigned to a user</p>
	 * <p>Update a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to update the assignment of any inventory roles to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to update the assignment of inventory roles accessible by the assigned user parent, to non-root users in a user hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to update the assignment of inventory roles accessible by the current user <b>AND</b> the parent of the assigned user to the descendants of the current user in the user hierarchy
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An inventory assignment was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 * @param id
	 * <p>Unique identifier of the inventory assignment.</p>
	 */
	public CompletionStage<InventoryAssignment> updateUserInventoryRole(final InventoryAssignmentReference body, final String tenantId, final String userId, final int id) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), InventoryAssignment.class);
	}
	
	/**
	 * <p>Remove a specific inventory role assigned to a user</p>
	 * <p>Remove a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>AND</b> (is not in user hierarchy <b>OR</b> is root in the user hierarchy) <b>OR</b> ROLE_USER_MANAGEMENT_ADMIN <b>AND</b> is in user hiararchy <b>AND</b> has parent access to inventory assignments <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user <b>AND</b> has current user access to inventory assignments <b>AND</b> has parent access to inventory assignments
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An inventory assignment was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 * @param id
	 * <p>Unique identifier of the inventory assignment.</p>
	 */
	public CompletionStage<Response> unassignUserInventoryRole(final String tenantId, final String userId, final int id) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
