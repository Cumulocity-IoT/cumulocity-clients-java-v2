// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Tenant;
import com.cumulocity.client.model.TenantTfaStrategy;
import com.cumulocity.client.model.TenantCollection;
import com.cumulocity.client.model.CurrentTenant;
import com.cumulocity.client.model.TenantTfaData;

/**
 * <p>Tenants are physically separated data spaces with a separate URL, with own users, a separate application management and no sharing of data by default. Users in a single tenant by default share the same URL and the same data space.</p>
 * <h3>Tenant ID and tenant domain</h3>
 * <p>The <strong>tenant ID</strong> is a unique identifier across all tenants in Cumulocity IoT and it follows the format t<number>, for example, t07007007. It is possible to specify the tenant ID while creating a subtenant, but the ID cannot be changed after creation. If the ID is not specified (recommended), it gets auto-generated for all tenant types.</p>
 * <p>The location where a tenant can be accessed is called <strong>tenant domain</strong>, for example, <em>mytenant.cumulocity.com</em>. It needs to be unique across all tenants and it can be changed after tenant creation.The tenant domain may contain only lowercase letters, digits and hyphens. It must start with a lowercase letter, hyphens are only allowed in the middle, and the minimum length is 2 characters. Note that the usage of underscore characters is deprecated but still possible for backward compatibility reasons.</p>
 * <p>In general, the tenant domain should be used for communication if it is known.</p>
 * <blockquote>
 * <p><strong>������ Important:</strong> For support user access, the tenant ID must be used and not the tenant domain.</p>
 * </blockquote>
 * <p>See <a href="#operation/getCurrentTenantResource">Tenant > Current tenant</a> for information on how to retrieve the tenant ID and domain of the current tenant via the API.</p>
 * <p>In the UI, the tenant ID is displayed in the user dropdown menu, see <a href="https://cumulocity.com/docs/get-familiar-with-the-ui/user-settings/">Getting started > Get familiar with the UI > User options and settings</a> in the Cumulocity IoT user documentation.</p>
 * <h3>Access rights and permissions</h3>
 * <p>There are two types of roles in Cumulocity IoT ��� global and inventory. Global roles are applied at the tenant level. In a Role Based Access Control (RBAC) approach you must use the inventory roles in order to have the correct level of separation. Apart from some global permissions (like "own user management") customer users will not be assigned any roles. Inventory roles must be created, or the default roles used, and then assigned to the user in combination with the assets the roles apply to. This needs to be done at least once for each customer.</p>
 * <p>In a multi-tenancy approach, as the tenant is completely separated from all other customers you do not necessarily need to be involved in setting up the access rights of the customer. If customers are given administration rights for their tenants, they can set up permissions on their own. It is not possible for customers to have any sight or knowledge of other customers.</p>
 * <p>In the RBAC approach, managing access is the most complicated part because a misconfiguration can potentially give customers access to data that they must not see, like other customers' data. The inventory roles allow you to granularly define access for only certain parts of data, but they don't protect you from accidental misconfigurations. A limitation here is that customers won't be able to create their own roles.</p>
 * <p>For more details, see <a href="https://cumulocity.com/docs/concepts/tenant-hierarchy/#comparison-of-various-use-cases">RBAC versus multi-tenancy approach</a>.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class TenantsApi extends AdaptableApi {

	public TenantsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all subtenants</p>
	 * <p>Retrieve all subtenants of the current tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the subtenants are sent in the response.</p></p>
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
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param company
	 * <p>Company name associated with the Cumulocity IoT tenant.</p>
	 * @param domain
	 * <p>Domain name of the Cumulocity IoT tenant.</p>
	 * @param parent
	 * <p>Identifier of the Cumulocity IoT tenant's parent.</p>
	 */
	public CompletionStage<TenantCollection> getTenants(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages, final String company, final String domain, final String parent) {
		return adapt().path("tenant").path("tenants")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.queryParam("company", company)
			.queryParam("domain", domain)
			.queryParam("parent", parent)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantcollection+json")
			.rx()
			.method("GET", TenantCollection.class);
	}
	
	/**
	 * <p>Create a tenant</p>
	 * <p>Create a subtenant for the current tenant.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_CREATE) <b>AND</b> the current tenant is allowed to create subtenants
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A tenant was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Conflict ��� The tenant domain/ID already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Tenant> createTenant(final Tenant body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "allowCreateTenants");
		removeFromNode(jsonNode, "parent");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "ownedApplications");
		removeFromNode(jsonNode, "applications");
		removeFromNode(jsonNode, "status");
		return adapt().path("tenant").path("tenants")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.tenant+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Tenant.class);
	}
	
	/**
	 * <p>Retrieve the current tenant</p>
	 * <p>Retrieve information about the current tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the information is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param withParent
	 * <p>When set to <code>true</code>, the returned result will contain the parent of the current tenant.</p>
	 */
	public CompletionStage<CurrentTenant> getCurrentTenant(final boolean withParent) {
		return adapt().path("tenant").path("currentTenant")
			.queryParam("withParent", withParent)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currenttenant+json")
			.rx()
			.method("GET", CurrentTenant.class);
	}
	
	/**
	 * <p>Retrieve a specific tenant</p>
	 * <p>Retrieve a specific tenant by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_READ <b>AND</b> (the current tenant is its parent <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the tenant is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<Tenant> getTenant(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("GET", Tenant.class);
	}
	
	/**
	 * <p>Update a specific tenant</p>
	 * <p>Update a specific tenant by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_UPDATE) <b>AND</b><br>
	 * ((the current tenant is its parent <b>AND</b> the current tenant is allowed to create subtenants) <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A tenant was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<Tenant> updateTenant(final Tenant body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "adminName");
		removeFromNode(jsonNode, "allowCreateTenants");
		removeFromNode(jsonNode, "parent");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "ownedApplications");
		removeFromNode(jsonNode, "applications");
		removeFromNode(jsonNode, "status");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.tenant+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Tenant.class);
	}
	
	/**
	 * <p>Remove a specific tenant</p>
	 * <p>Remove a specific tenant by a given ID.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Deleting a subtenant cannot be reverted. For security reasons, it is therefore only available in the management tenant. You cannot delete tenants from any tenant but the management tenant.</p>
	 * <p>Administrators in Enterprise Tenants are only allowed to suspend active subtenants, but not to delete them.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> is the management tenant
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A tenant was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<Response> deleteTenant(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve TFA settings of a specific tenant</p>
	 * <p>Retrieve the two-factor authentication settings of a specific tenant by a given tenant ID.</p>
	 * <section><h5>Required roles</h5>
	 * ((ROLE_TENANT_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_READ) <b>AND</b> (the current tenant is its parent <b>OR</b> is the management tenant <b>OR</b> the current user belongs to the tenant)) <b>OR</b> (the user belongs to the tenant <b>AND</b> ROLE_USER_MANAGEMENT_OWN_READ)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the TFA settings are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<TenantTfaData> getTenantTfaSettings(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("tfa")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TenantTfaData.class);
	}
	
	/**
	 * <p>Sets TFA settings for a specific tenant</p>
	 * <p>Sets the two-factor authentication settings of a specific tenant for a specific tenant ID.</p>
	 * <section><h5>Required roles</h5>
	 * ((ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_UPDATE) <b>AND</b> (the current tenant is its parent <b>OR</b> the current user belongs to the tenant)))
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>The tenant's TFA configuration was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<Response> updateTenantTfaSettings(final TenantTfaStrategy body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("tfa")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
}
