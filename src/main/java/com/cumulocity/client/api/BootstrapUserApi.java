// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.BootstrapUser;

/**
 * <p>API methods to retrieve the bootstrap user of an application.</p>
 */
public class BootstrapUserApi extends AdaptableApi {

	public BootstrapUserApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve the bootstrap user for a specific application</p>
	 * <p>Retrieve the bootstrap user for a specific application (by a given ID).</p>
	 * <p>This only works for microservice applications.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the bootstrap user of the application is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Bad request.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 */
	public CompletionStage<BootstrapUser> getBootstrapUser(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("bootstrapUser")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("GET", BootstrapUser.class);
	}
}
