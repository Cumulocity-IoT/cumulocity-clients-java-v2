// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.FeatureToggleValue;
import com.cumulocity.client.model.FeatureToggle;
import com.cumulocity.client.model.TenantFeatureToggleValue;

public class FeatureTogglesApi extends AdaptableApi {

	public FeatureTogglesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve list of feature toggles with values for current tenant.</p>
	 * <p>Retrieve a list of all defined feature toggles with values calculated for a tenant of authenticated user.</p>
	 * <section><h5>Required roles</h5>
	 * none, any authenticated user can call this endpoint
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggles are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<FeatureToggle[]> listCurrentTenantFeatures() {
		return adapt().path("features")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", FeatureToggle[].class);
	}
	
	/**
	 * <p>Retrieve a specific feature toggle with value for current tenant.</p>
	 * <p>Retrieve a specific feature toggles defined under given key, with value calculated for a tenant of authenticated user.</p>
	 * <section><h5>Required roles</h5>
	 * none, any authenticated user can call this endpoint
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggle is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<FeatureToggle> getCurrentTenantFeature() {
		return adapt().path("features").path(valueOf(featurekey))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", FeatureToggle.class);
	}
	
	/**
	 * <p>Retrieve list of feature toggles values overrides of all tenants.</p>
	 * <p>Retrieve a list of all existing feature toggle value overrides for all tenants.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> current tenant is management
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggles are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<TenantFeatureToggleValue[]> listTenantFeatureToggleValues() {
		return adapt().path("features").path(valueOf(featurekey)).path("by-tenant")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TenantFeatureToggleValue[].class);
	}
	
	/**
	 * <p>Sets the value of feature toggle override for the current tenant.</p>
	 * <p>Sets the value of feature toggle override for a tenant of authenticated user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> (current tenant is management <b>OR</b> the feature toggle phase is PUBLIC_PREVIEW or GENERALLY_AVAILABLE)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggle value override was set.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> setCurrentTenantFeatureToggleValue(final FeatureToggleValue body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("features").path(valueOf(featurekey)).path("by-tenant")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Removes the feature toggle override for the current tenant.</p>
	 * <p>Removes the feature toggle override for a tenant of authenticated user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> (current tenant is management <b>OR</b> the feature toggle phase is PUBLIC_PREVIEW or GENERALLY_AVAILABLE)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggle value override was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<Response> unsetCurrentTenantFeatureToggleValue() {
		return adapt().path("features").path(valueOf(featurekey)).path("by-tenant")
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Sets the value of feature toggle override for a given tenant.</p>
	 * <p>Sets the value of feature toggle override for a given tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> current tenant is management.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggle value override was set.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> setGivenTenantFeatureToggleValue(final FeatureToggleValue body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("features").path(valueOf(featurekey)).path("by-tenant").path(valueOf(tenantid))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Removes the feature toggle override for a given tenant.</p>
	 * <p>Removes the feature toggle override for a given tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> current tenant is management.
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the feature toggle value override was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<Response> unsetGivenTenantFeatureToggleValue() {
		return adapt().path("features").path(valueOf(featurekey)).path("by-tenant").path(valueOf(tenantid))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
