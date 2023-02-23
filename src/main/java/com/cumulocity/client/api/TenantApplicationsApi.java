// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.SubscribedApplicationReference;
import com.cumulocity.client.model.ApplicationReferenceCollection;
import com.cumulocity.client.model.ApplicationReference;

/**
 * References to the tenant subscribed applications.
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class TenantApplicationsApi extends AdaptableApi {

	public TenantApplicationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve subscribed applications
	 * Retrieve the tenant subscribed applications by a given tenant ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_READ <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (the current tenant is its parent <b>OR</b> is the management tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the tenant applications are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<ApplicationReferenceCollection> getSubscribedApplications(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("applications")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationreferencecollection+json")
			.rx()
			.method("GET", ApplicationReferenceCollection.class);
	}
	
	/**
	 * Subscribe to an application
	 * Subscribe a tenant (by a given ID) to an application.
	 * 
	 * <section><h5>Required roles</h5>
	 * 1. the current tenant is application owner and has the role ROLE_APPLICATION_MANAGEMENT_ADMIN <b>OR</b><br>
	 * 2. for applications that are not microservices, the current tenant is the management tenant or the parent of the application owner tenant, and the user has one of the follwoing roles: ROLE_TENANT_MANAGEMENT_ADMIN, ROLE_TENANT_MANAGEMENT_UPDATE <b>OR</b><br>
	 * 3. for microservices, the current tenant is the management tenant or the parent of the application owner tenant, and the user has the role ROLE_TENANT_MANAGEMENT_ADMIN OR ROLE_TENANT_MANAGEMENT_UPDATE and one of following conditions is met:<br>
	 * * the microservice has no manifest<br>
	 * * the microservice version is supported<br>
	 * * the current tenant is subscribed to 'feature-privileged-microservice-hosting'
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A tenant was subscribed to an application.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - The application is already assigned to the tenant.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<ApplicationReference> subscribeApplication(final SubscribedApplicationReference body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("applications")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.applicationreference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationreference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), ApplicationReference.class);
	}
	
	/**
	 * Unsubscribe from an application
	 * Unsubscribe a tenant (by a given tenant ID) from an application (by a given application ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> is the application owner <b>AND</b> is the current tenant) <b>OR</b><br>
	 * ((ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_UPDATE) <b>AND</b> (the current tenant is its parent <b>OR</b> is the management tenant))
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A tenant was unsubscribed from an application.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param applicationId Unique identifier of the application.
	 */
	public CompletionStage<Response> unsubscribeApplication(final String tenantId, final String applicationId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("applications").path(valueOf(applicationId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
