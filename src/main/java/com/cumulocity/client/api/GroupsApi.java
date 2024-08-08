// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Group;
import com.cumulocity.client.model.UserGroupCollection;
import com.cumulocity.client.model.GroupReferenceCollection;

/**
 * <p>API methods to create, retrieve, update and delete user groups.</p>
 * <blockquote>
 * <p><strong>������ Important:</strong> In the Cumulocity IoT user interface, user groups are referred to as "global roles". Global roles are not to be confused with user roles.</p>
 * </blockquote>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class GroupsApi extends AdaptableApi {

	public GroupsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all user groups of a specific tenant</p>
	 * <p>Retrieve all user groups of a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all user groups are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<UserGroupCollection> getTenantUserGroups(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupcollection+json")
			.rx()
			.method("GET", UserGroupCollection.class);
	}
	
	/**
	 * <p>Create a user group for a specific tenant</p>
	 * <p>Create a user group for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A user group was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate ��� Group name already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<Group> createUserGroup(final Group body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "users");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("groups")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.group+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Group.class);
	}
	
	/**
	 * <p>Retrieve a specific user group for a specific tenant</p>
	 * <p>Retrieve a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and the user group is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Group not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 */
	public CompletionStage<Group> getUserGroup(final String tenantId, final int groupId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("GET", Group.class);
	}
	
	/**
	 * <p>Update a specific user group for a specific tenant</p>
	 * <p>Update a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A user group was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Group not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 */
	public CompletionStage<Group> updateUserGroup(final Group body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "users");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.group+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Group.class);
	}
	
	/**
	 * <p>Delete a specific user group for a specific tenant</p>
	 * <p>Delete a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A user group was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Group not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 */
	public CompletionStage<Response> deleteUserGroup(final String tenantId, final int groupId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a user group by group name for a specific tenant</p>
	 * <p>Retrieve a user group by group name for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to groups
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and the user group is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Group not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param groupName
	 * <p>The name of the user group.</p>
	 */
	public CompletionStage<Group> getUserGroupByName(final String tenantId, final String groupName) {
		return adapt().path("user").path(valueOf(tenantId)).path("groupByName").path(valueOf(groupName))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("GET", Group.class);
	}
	
	/**
	 * <p>Get all user groups for specific user in a specific tenant</p>
	 * <p>Get all user groups for a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and all groups for the user are sent in the response.</p></p>
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
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<GroupReferenceCollection> getUserGroups(final String tenantId, final String userId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("groups")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupreferencecollection+json")
			.rx()
			.method("GET", GroupReferenceCollection.class);
	}
}
