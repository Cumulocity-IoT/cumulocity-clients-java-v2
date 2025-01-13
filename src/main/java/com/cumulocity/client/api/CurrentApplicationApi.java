// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Application;
import com.cumulocity.client.model.ApplicationSettings;
import com.cumulocity.client.model.ApplicationUserCollection;

/**
 * <p>API methods to retrieve and update the current application and to retrieve its subscribers.It is the authenticated microservice user's application.</p>
 */
public class CurrentApplicationApi extends AdaptableApi {

	public CurrentApplicationApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve the current application</p>
	 * <p>Retrieve the current application.This only works inside an application, for example, a microservice.</p>
	 * <section><h5>Required roles</h5>
	 * Microservice bootstrap user required.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the current application sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<Application> getCurrentApplication() {
		return adapt().path("application").path("currentApplication")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("GET", Application.class);
	}
	
	/**
	 * <p>Update the current application</p>
	 * <p>Update the current application.This only works inside an application, for example, a microservice. This method is deprecated as it is only used by legacy microservices that are not running on Kubernetes.</p>
	 * <section><h5>Required roles</h5>
	 * Microservice bootstrap user required.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The current application was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	@Deprecated
	public CompletionStage<Application> updateCurrentApplication(final Application body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "resourcesUrl");
		return adapt().path("application").path("currentApplication")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Application.class);
	}
	
	/**
	 * <p>Retrieve the current application settings</p>
	 * <p>Retrieve the current application settings.This only works inside an application, for example, a microservice.</p>
	 * <section><h5>Required roles</h5>
	 * Microservice bootstrap user <b>OR</b> microservice service user required.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the current application settings are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<ApplicationSettings[]> getCurrentApplicationSettings() {
		return adapt().path("application").path("currentApplication").path("settings")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationsettings+json")
			.rx()
			.method("GET", ApplicationSettings[].class);
	}
	
	/**
	 * <p>Retrieve the subscribed users of the current application</p>
	 * <p>Retrieve the subscribed users of the current application.</p>
	 * <section><h5>Required roles</h5>
	 * Microservice bootstrap user required.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of subscribed users for the current application is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<ApplicationUserCollection> getSubscribedUsers() {
		return adapt().path("application").path("currentApplication").path("subscriptions")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.applicationusercollection+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ApplicationUserCollection.class);
	}
}
