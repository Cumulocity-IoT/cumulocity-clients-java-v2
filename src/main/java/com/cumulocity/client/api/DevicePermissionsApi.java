// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.UpdatedDevicePermissions;
import com.cumulocity.client.model.DevicePermissionOwners;

/**
 * <p>API methods to retrieve and update device permissions assignments.</p>
 * <p>Device permissions enable users to access and manipulate devices.</p>
 * <p>The device permission structure is <strong>[API:fragment_name:permission]</strong> where:</p>
 * <ul>
 * 	<li><p><strong>API</strong> is one of the following values: OPERATION, ALARM, AUDIT, EVENT, MANAGED_OBJECT, MEASUREMENT or "*"</p>
 * 	</li>
 * 	<li><p><strong>fragment_name</strong> can be the name of any fragment, for example, "c8y_Restart" or "*"</p>
 * 	</li>
 * 	<li><p><strong>permission</strong> is ADMIN, READ or "*"</p>
 * 	</li>
 * </ul>
 * <p>Required permission per HTTP method:</p>
 * <ul>
 * 	<li><p>GET - READ or "*"</p>
 * 	</li>
 * 	<li><p>PUT - ADMIN or "*"</p>
 * 	</li>
 * </ul>
 * <p>The wildcard "*" enables you to access every API and stored object regardless of the fragments that are inside it.</p>
 * <blockquote>
 * <p><strong>������ Important:</strong> If there is no fragment in an object, for example, to read the object, you must use the wildcard "*" for the <strong>fragment_name</strong> part of the device permission (see the structure above). For example: <code>"10200":["MEASUREMENT:*:READ"]</code>.</p>
 * </blockquote>
 */
public class DevicePermissionsApi extends AdaptableApi {

	public DevicePermissionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Returns all device permissions assignments</p>
	 * <p>Returns all device permissions assignments if the current user has READ permission.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the device permissions assignments are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<DevicePermissionOwners> getDevicePermissionAssignments(final String id) {
		return adapt().path("user").path("devicePermissions").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", DevicePermissionOwners.class);
	}
	
	/**
	 * <p>Updates the device permissions assignments</p>
	 * <p>Updates the device permissions assignments if the current user has ADMIN permission or CREATE permission and also has all device permissions.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The device permissions were successfully updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<Response> updateDevicePermissionAssignments(final UpdatedDevicePermissions body, final String id) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("devicePermissions").path(valueOf(id))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
}
