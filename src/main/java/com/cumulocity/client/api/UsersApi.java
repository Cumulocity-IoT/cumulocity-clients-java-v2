// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.User;
import com.cumulocity.client.model.SubscribedUser;
import com.cumulocity.client.model.UserCollection;
import com.cumulocity.client.model.UserTfaData;
import com.cumulocity.client.model.UserReferenceCollection;
import com.cumulocity.client.model.UserReference;

/**
 * <p>API methods to create, retrieve, update and delete users in Cumulocity.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class UsersApi extends AdaptableApi {

	public UsersApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all users for a specific tenant</p>
	 * <p>Retrieve all users for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all users are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param groups
	 * <p>Numeric group identifiers. The response will contain only users which belong to at least one of the specified groups.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple user groups at once, comma-separate the values.</p>
	 * @param onlyDevices
	 * <p>If set to <code>true</code>, the response will only contain users created during bootstrap process (starting with ���device_���).If the flag is absent or <code>false</code> the result will not contain ���device_��� users.</p>
	 * @param owner
	 * <p>Exact username of the owner of the user</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param username
	 * <p>Prefix or full username</p>
	 * @param withSubusersCount
	 * <p>If set to <code>true</code>, then each of returned user will contain an additional field ���subusersCount���.It is the number of direct subusers (users with corresponding ���owner���).</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<UserCollection> getUsers(final String tenantId, final int currentPage, final String[] groups, final boolean onlyDevices, final String owner, final int pageSize, final String username, final boolean withSubusersCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("users")
			.queryParam("currentPage", currentPage)
			.queryParam("groups", groups, false)
			.queryParam("onlyDevices", onlyDevices)
			.queryParam("owner", owner)
			.queryParam("pageSize", pageSize)
			.queryParam("username", username)
			.queryParam("withSubusersCount", withSubusersCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.usercollection+json")
			.rx()
			.method("GET", UserCollection.class);
	}
	
	/**
	 * <p>Create a user for a specific tenant</p>
	 * <p>Create a user for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to roles, groups, device permissions and applications
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A user was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate ��� The userName or alias already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 */
	public CompletionStage<User> createUser(final User body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "passwordStrength");
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "groups");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("users")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.user+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("POST", Entity.json(jsonNode), User.class);
	}
	
	/**
	 * <p>Retrieve a specific user for a specific tenant</p>
	 * <p>Retrieve a specific user (by a given user ID) for a specific tenant (by a given tenant ID).</p>
	 * <p>Users in the response are sorted by username in ascending order.Only objects which the user is allowed to see are returned to the user.The user password is never returned in a GET response. Authentication mechanism is provided by another interface.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the user is sent in the response.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<User> getUser(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("GET", User.class);
	}
	
	/**
	 * <p>Update a specific user for a specific tenant</p>
	 * <p>Update a specific user (by a given user ID) for a specific tenant (by a given tenant ID).</p>
	 * <p>Any change in user's roles, device permissions and groups creates corresponding audit records with type "User" and activity "User updated" with information which properties have been changed.</p>
	 * <p>When the user is updated with changed permissions or groups, a corresponding audit record is created with type "User" and activity "User updated".</p>
	 * <p>Note that you cannot update the password or email of another user, they can only be updated for the current user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to update root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to update non-root users in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to update descendants of the current user in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A user was updated.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<User> updateUser(final User body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "passwordStrength");
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "groups");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "userName");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.user+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), User.class);
	}
	
	/**
	 * <p>Delete a specific user for a specific tenant</p>
	 * <p>Delete a specific user (by a given user ID) for a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> not the current user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A user was removed.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<Response> deleteUser(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve the TFA settings of a specific user</p>
	 * <p>Retrieve the two-factor authentication settings for the specified user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user) <b>OR</b> is the current user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the TFA settings are sent in the response.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<UserTfaData> getUserTfaSettings(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("tfa")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", UserTfaData.class);
	}
	
	/**
	 * <p>Retrieve a user by username in a specific tenant</p>
	 * <p>Retrieve a user by username in a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the user is sent in the response.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param username
	 * <p>The username of the a user.</p>
	 */
	public CompletionStage<User> getUserByUsername(final String tenantId, final String username) {
		return adapt().path("user").path(valueOf(tenantId)).path("userByName").path(valueOf(username))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("GET", User.class);
	}
	
	/**
	 * <p>Retrieve the users of a specific user group of a specific tenant</p>
	 * <p>Retrieve the users of a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user group)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the users are sent in the response.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<UserReferenceCollection> getUsersFromUserGroup(final String tenantId, final int groupId, final int currentPage, final int pageSize, final boolean withTotalElements) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreferencecollection+json")
			.rx()
			.method("GET", UserReferenceCollection.class);
	}
	
	/**
	 * <p>Add a user to a specific user group of a specific tenant</p>
	 * <p>Add a user to a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign root users in a user hierarchy <b>OR</b> users that are not in any hierarchy to any group<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign non-root users in a user hierarchy to groups accessible by the parent of assigned user<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to assign descendants of the current user in a user hierarchy to groups accessible by current user <b>AND</b> accessible by the parent of assigned user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The user was added to the group.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 */
	public CompletionStage<UserReference> assignUserToUserGroup(final SubscribedUser body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.userreference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), UserReference.class);
	}
	
	/**
	 * <p>Remove a specific user from a specific user group of a specific tenant</p>
	 * <p>Remove a specific user (by a given user ID) from a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A user was removed from a group.</p></p>
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
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 * @param groupId
	 * <p>Unique identifier of the user group.</p>
	 * @param userId
	 * <p>Unique identifier of the a user.</p>
	 */
	public CompletionStage<Response> removeUserFromUserGroup(final String tenantId, final int groupId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Terminate a user's session</p>
	 * <p>After logging out, a user has to enter valid credentials again to get access to the platform.</p>
	 * <p>The request is responsible for removing cookies from the browser and invalidating internal platform access tokens.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the user is logged out.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param cookie
	 * <p>The authorization cookie storing the access token of the user. This parameter is specific to OAI-Secure authentication.</p>
	 * @param xXSRFTOKEN
	 * <p>Prevents XRSF attack of the authenticated user. This parameter is specific to OAI-Secure authentication.</p>
	 */
	public CompletionStage<Response> logout(final String cookie, final String xXSRFTOKEN) {
		return adapt().path("user").path("logout")
			.request()
			.header("Cookie", cookie)
			.header("X-XSRF-TOKEN", xXSRFTOKEN)
			.header("Accept", "application/json")
			.rx()
			.method("POST");
	}
	
	/**
	 * <p>Terminate all tenant users' sessions and invalidate tokens</p>
	 * <p>The user with the role ROLE_USER_MANAGEMENT_ADMIN is authorized to log out all tenant users with a toked based access.</p>
	 * <p>The request is responsible for terminating all tenant users' toked based sessions and invalidating internal platform access tokens.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>AND</b> is the current tenant
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the users (with a token based access) are logged out.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity tenant.</p>
	 */
	public CompletionStage<Response> logoutAllUsers(final String tenantId) {
		return adapt().path("user").path("logout").path(valueOf(tenantId)).path("allUsers")
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("POST");
	}
}
