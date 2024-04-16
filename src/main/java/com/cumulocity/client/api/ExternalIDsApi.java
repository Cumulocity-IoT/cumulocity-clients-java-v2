// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ExternalId;
import com.cumulocity.client.model.ExternalIds;

/**
 * <p>The external ID resource represents an individual external ID that can be queried and deleted.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class ExternalIDsApi extends AdaptableApi {

	public ExternalIDsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all external IDs of a specific managed object</p>
	 * <p>Retrieve all external IDs of a existing managed object (identified by ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all the external IDs are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<ExternalIds> getExternalIds(final String id) {
		return adapt().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalidcollection+json")
			.rx()
			.method("GET", ExternalIds.class);
	}
	
	/**
	 * <p>Create an external ID</p>
	 * <p>Create an external ID for an existing managed object (identified by ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An external ID was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Global ID not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate – Identity already bound to a different Global ID.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<ExternalId> createExternalId(final ExternalId body, final String id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "managedObject");
		removeFromNode(jsonNode, "self");
		return adapt().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.externalid+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
			.rx()
			.method("POST", Entity.json(jsonNode), ExternalId.class);
	}
	
	/**
	 * <p>Retrieve a specific external ID</p>
	 * <p>Retrieve a specific external ID of a particular type.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the external ID is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>External ID not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param type
	 * <p>The identifier used in the external system that Cumulocity IoT interfaces with.</p>
	 * @param externalId
	 * <p>The type of the external identifier.</p>
	 */
	public CompletionStage<ExternalId> getExternalId(final String type, final String externalId) {
		return adapt().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
			.rx()
			.method("GET", ExternalId.class);
	}
	
	/**
	 * <p>Remove a specific external ID</p>
	 * <p>Remove a specific external ID of a particular type.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An external ID was deleted.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>External ID not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param type
	 * <p>The identifier used in the external system that Cumulocity IoT interfaces with.</p>
	 * @param externalId
	 * <p>The type of the external identifier.</p>
	 */
	public CompletionStage<Response> deleteExternalId(final String type, final String externalId) {
		return adapt().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
