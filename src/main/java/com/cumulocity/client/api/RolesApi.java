// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.SubscribedRole;
import com.cumulocity.client.model.UserRoleCollection;
import com.cumulocity.client.model.Role;
import com.cumulocity.client.model.RoleReferenceCollection;
import com.cumulocity.client.model.RoleReference;

/**
 * <p>API methods to create, retrieve, update and delete user roles.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class RolesApi extends AdaptableApi {

	public RolesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all user roles</p>
	 * <p>Retrieve all user roles.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user role
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all user roles are sent in the response.</p></p>
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
	public CompletionStage<UserRoleCollection> getUserRoles(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolecollection+json")
			.rx()
			.method("GET", UserRoleCollection.class);
	}
	
	/**
	 * <p>Retrieve a user role by name</p>
	 * <p>Retrieve a user role by name.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> current user has access to the role with this name
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the user role is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Role not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param name
	 * <p>The name of the user role.</p>
	 */
	public CompletionStage<Role> getUserRole(final String name) {
		return adapt().path("user").path("roles").path(valueOf(name))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.role+json")
			.rx()
			.method("GET", Role.class);
	}
	
	/**
	 * <p>Retrieve all roles assigned to a specific user group in a specific tenant</p>
	 * <p>Retrieve all roles assigned to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request succeeded and the roles are sent in the response.</p></p>
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
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 */
	public CompletionStage<RoleReferenceCollection> getGroupRoles(final String tenantId, final int groupId, final int currentPage, final int pageSize) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereferencecollection+json")
			.rx()
			.method("GET", RoleReferenceCollection.class);
	}
	
	/**
	 * <p>Assign a role to a specific user group in a specific tenant</p>
	 * <p>Assign a role to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A user role was assigned to a user group.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Group not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Conflict ��� Role already assigned to the user group.</p></p>
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
	public CompletionStage<RoleReference> assignGroupRole(final SubscribedRole body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), RoleReference.class);
	}
	
	/**
	 * <p>Unassign a specific role for a specific user group in a specific tenant</p>
	 * <p>Unassign a specific role (given by a role ID) for a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A role was unassigned from a user group.</p></p>
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
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 * @param roleId
	 * <p>Unique identifier of the user role.</p>
	 */
	public CompletionStage<Response> unassignGroupRole(final String tenantId, final int groupId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Assign a role to specific user in a specific tenant</p>
	 * <p>Assign a role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <p>When a role is assigned to a user, a corresponding audit record is created with type "User" and activity "User updated".</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign any role to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign roles accessible by the parent of assigned user to non-root users in a user hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to assign roles accessible by the current user <b>AND</b> accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A user role was assigned to a user.</p></p>
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
	public CompletionStage<RoleReference> assignUserRole(final SubscribedRole body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), RoleReference.class);
	}
	
	/**
	 * <p>Unassign a specific role from a specific user in a specific tenant</p>
	 * <p>Unassign a specific role (by a given role ID) from a specific user (by a given user ID) in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> has access to roles
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A user role was unassigned from a user.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>User not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 * @param roleId
	 * <p>Unique identifier of the user role.</p>
	 */
	public CompletionStage<Response> unassignUserRole(final String tenantId, final String userId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
