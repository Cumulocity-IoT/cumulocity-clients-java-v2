// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Application;
import com.cumulocity.client.model.ApplicationCollection;

/**
 * <p>API methods to retrieve, create, update and delete applications.</p>
 * <p>###��Application names</p>
 * <p>For each tenant, Cumulocity IoT manages the subscribed applications and provides a number of applications of various types.In case you want to subscribe a tenant to an application using an API, you must use the application name in the argument (as name).</p>
 * <p>Refer to the tables in <a href="https://cumulocity.com/docs/standard-tenant/ecosystem/#managing-applications">Platform administration > Standard tenant administration > Managing the ecosystem > Managing applications</a> in the Cumulocity IoT user documentation for the respective application name to be used.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class ApplicationsApi extends AdaptableApi {

	public ApplicationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all applications</p>
	 * <p>Retrieve all applications on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of applications is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param name
	 * <p>The name of the application.</p>
	 * @param owner
	 * <p>The ID of the tenant that owns the applications.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param providedFor
	 * <p>The ID of a tenant that is subscribed to the applications but doesn't own them.</p>
	 * @param subscriber
	 * <p>The ID of a tenant that is subscribed to the applications.</p>
	 * @param tenant
	 * <p>The ID of a tenant that either owns the application or is subscribed to the applications.</p>
	 * @param type
	 * <p>The type of the application. It is possible to use multiple values separated by a comma. For example, <code>EXTERNAL,HOSTED</code> will return only applications with type <code>EXTERNAL</code> or <code>HOSTED</code>.</p>
	 * @param user
	 * <p>The ID of a user that has access to the applications.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 * @param hasVersions
	 * <p>When set to <code>true</code>, the returned result contains applications with an <code>applicationVersions</code> field that is not empty. When set to <code>false</code>, the result will contain applications with an empty <code>applicationVersions</code> field.</p>
	 */
	public CompletionStage<ApplicationCollection> getApplications(final int currentPage, final String name, final String owner, final int pageSize, final String providedFor, final String subscriber, final String tenant, final String type, final String user, final boolean withTotalElements, final boolean withTotalPages, final boolean hasVersions) {
		return adapt().path("application").path("applications")
			.queryParam("currentPage", currentPage)
			.queryParam("name", name)
			.queryParam("owner", owner)
			.queryParam("pageSize", pageSize)
			.queryParam("providedFor", providedFor)
			.queryParam("subscriber", subscriber)
			.queryParam("tenant", tenant)
			.queryParam("type", type)
			.queryParam("user", user)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.queryParam("hasVersions", hasVersions)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
			.rx()
			.method("GET", ApplicationCollection.class);
	}
	
	/**
	 * <p>Create an application</p>
	 * <p>Create an application on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An application was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate key/name.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Application> createApplication(final Application body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "resourcesUrl");
		return adapt().path("application").path("applications")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Application.class);
	}
	
	/**
	 * <p>Retrieve a specific application</p>
	 * <p>Retrieve a specific application (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ <b>OR</b> current user has explicit access to the application
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the application is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 */
	public CompletionStage<Application> getApplication(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("GET", Application.class);
	}
	
	/**
	 * <p>Update a specific application</p>
	 * <p>Update a specific application (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An application was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Application> updateApplication(final Application body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "type");
		removeFromNode(jsonNode, "resourcesUrl");
		return adapt().path("application").path("applications").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Application.class);
	}
	
	/**
	 * <p>Delete an application</p>
	 * <p>Delete an application (by a given ID).This method is not supported by microservice applications.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> With regards to a hosted application, there is a caching mechanism in place that keeps the information about the placement of application files (html, javascript, css, fonts, etc.). Removing a hosted application, in normal circumstances, will cause the subsequent requests for application files to fail with an HTTP 404 error because the application is removed synchronously, its files are immediately removed on the node serving the request and at the same time the information is propagated to other nodes ��� but in rare cases there might be a delay with this propagation. In such situations, the files of the removed application can be served from those nodes up until the aforementioned cache expires. For the same reason, the cache can also cause HTTP 404 errors when the application is updated as it will keep the path to the files of the old version of the application. The cache is filled on demand, so there should not be issues if application files were not accessed prior to the delete request. The expiration delay of the cache can differ, but should not take more than one minute.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An application was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param force
	 * <p>Force deletion by unsubscribing all tenants from the application first and then deleting the application itself.</p>
	 */
	public CompletionStage<Response> deleteApplication(final String id, final boolean force, final String xCumulocityProcessingMode) {
		return adapt().path("application").path("applications").path(valueOf(id))
			.queryParam("force", force)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Copy an application</p>
	 * <p>Copy an application (by a given ID).</p>
	 * <p>This method is not supported by microservice applications.</p>
	 * <p>A request to the "clone" resource creates a new application based on an already existing one.</p>
	 * <p>The properties are copied to the newly created application and the prefix "clone" is added to the properties <code>name</code>, <code>key</code> and <code>contextPath</code> in order to be unique.</p>
	 * <p>If the target application is hosted and has an active version, the new application will have the active version with the same content.</p>
	 * <p>If the original application is hosted with versions, then only one binary version is cloned. By default it is a version with the "latest" tag. You can also specify a target version directly by using exactly one of the query parameters <code>version</code> or <code>tag</code>.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An application was copied.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� method not supported</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param version
	 * <p>The version field of the application version.</p>
	 * @param tag
	 * <p>The tag of the application version.</p>
	 */
	public CompletionStage<Application> copyApplication(final String id, final String version, final String tag, final String xCumulocityProcessingMode) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("clone")
			.queryParam("version", version)
			.queryParam("tag", tag)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("POST", Application.class);
	}
	
	/**
	 * <p>Retrieve applications by name</p>
	 * <p>Retrieve applications by name.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the applications are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param name
	 * <p>The name of the application.</p>
	 */
	public CompletionStage<ApplicationCollection> getApplicationsByName(final String name) {
		return adapt().path("application").path("applicationsByName").path(valueOf(name))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
			.rx()
			.method("GET", ApplicationCollection.class);
	}
	
	/**
	 * <p>Retrieve applications by tenant</p>
	 * <p>Retrieve applications subscribed or owned by a particular tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the applications are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 */
	public CompletionStage<ApplicationCollection> getApplicationsByTenant(final String tenantId) {
		return adapt().path("application").path("applicationsByTenant").path(valueOf(tenantId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
			.rx()
			.method("GET", ApplicationCollection.class);
	}
	
	/**
	 * <p>Retrieve applications by owner</p>
	 * <p>Retrieve all applications owned by a particular tenant (by a given tenant ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the applications are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
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
	public CompletionStage<ApplicationCollection> getApplicationsByOwner(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("application").path("applicationsByOwner").path(valueOf(tenantId))
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
			.rx()
			.method("GET", ApplicationCollection.class);
	}
	
	/**
	 * <p>Retrieve applications by user</p>
	 * <p>Retrieve all applications for a particular user (by a given username).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_USER_MANAGEMENT_OWN_READ <b>AND</b> is the current user) <b>OR</b> (ROLE_USER_MANAGEMENT_READ <b>AND</b> ROLE_APPLICATION_MANAGEMENT_READ)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the applications are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param username
	 * <p>The username of the a user.</p>
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
	public CompletionStage<ApplicationCollection> getApplicationsByUser(final String username, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("application").path("applicationsByUser").path(valueOf(username))
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
			.rx()
			.method("GET", ApplicationCollection.class);
	}
}
