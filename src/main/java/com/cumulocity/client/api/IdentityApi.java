// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.IdentityApiResource;

/**
 * <p>Cumulocity can associate devices and assets with multiple external identities.For instance, devices can often be identified by the IMEI of their modem, by a micro-controller serial number or by an asset tag.This is useful, for example, when you have non-functional hardware and must replace the hardware without losing the data that was recorded.</p>
 * <p>The identity API resource returns URIs and URI templates for associating external identifiers with unique identifiers.</p>
 */
public class IdentityApi extends AdaptableApi {

	public IdentityApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve URIs to collections of external IDs</p>
	 * <p>Retrieve URIs and URI templates for associating external identifiers with unique identifiers.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the URIs are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<IdentityApiResource> getIdentityApiResource() {
		return adapt().path("identity")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.identityapi+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", IdentityApiResource.class);
	}
}
