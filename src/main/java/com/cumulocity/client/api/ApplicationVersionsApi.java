// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ApplicationVersionTag;
import com.cumulocity.client.model.ApplicationVersion;
import com.cumulocity.client.model.ApplicationVersionCollection;

/**
 * <p>API methods to retrieve, create, update and delete application versions.</p>
 */
public class ApplicationVersionsApi extends AdaptableApi {

	public ApplicationVersionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve a specific version of an application</p>
	 * <p>Retrieve the selected version of an application in your tenant. To select the version, use only the version or only the tag query parameter.</p>
	 * <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the application version is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>both parameters (version and tag) are present.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param version
	 * <p>The version field of the application version.</p>
	 * @param tag
	 * <p>The tag of the application version.</p>
	 */
	public CompletionStage<ApplicationVersion> getApplicationVersion(final String id, final String version, final String tag) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions?version=1.0")
			.queryParam("version", version)
			.queryParam("tag", tag)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("GET", ApplicationVersion.class);
	}
	
	/**
	 * <p>Retrieve all versions of an application</p>
	 * <p>Retrieve all versions of an application in your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the list of application versions is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application version not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>This application doesn't support versioning.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 */
	public CompletionStage<ApplicationVersionCollection> getApplicationVersions(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersionCollection+json")
			.rx()
			.method("GET", ApplicationVersionCollection.class);
	}
	
	/**
	 * <p>Create an application version</p>
	 * <p>Create an application version in your tenant.</p>
	 * <p>Uploaded version and tags can only contain upper and lower case letters, integers and <code>.</code>,<code>+</code>,<code> -</code>. Other characters are prohibited.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An application version was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application version not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate version/tag or versions limit exceeded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>tag or version contains unacceptable characters.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param applicationBinary
	 * <p>The ZIP file to be uploaded.</p>
	 * @param applicationVersion
	 * <p>The JSON file with version information.</p>
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 */
	public CompletionStage<ApplicationVersion> createApplicationVersion(final byte[] applicationBinary, final String applicationVersion, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("applicationBinary", applicationBinary, MediaType.valueOf("application/zip"));
		multiPartEntity.field("applicationVersion", applicationVersion, MediaType.valueOf("text/plain"));
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), ApplicationVersion.class);
	}
	
	/**
	 * <p>Delete a specific version of an application</p>
	 * <p>Delete a specific version of an application in your tenant, by a given tag or version.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A version was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application version not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Version with tag latest cannot be removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>both parameters (version and tag) are present.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param version
	 * <p>The version field of the application version.</p>
	 * @param tag
	 * <p>The tag of the application version.</p>
	 */
	public CompletionStage<Response> deleteApplicationVersion(final String id, final String version, final String tag) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.queryParam("version", version)
			.queryParam("tag", tag)
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Replace an application version's tags</p>
	 * <p>Replaces the tags of a given application version in your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An application version was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Application version not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate version/tag or versions limit exceeded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>tag contains unacceptable characters.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param version
	 * <p>Version of the application.</p>
	 */
	public CompletionStage<ApplicationVersion> updateApplicationVersion(final ApplicationVersionTag body, final String id, final String version) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions").path(valueOf(version))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), ApplicationVersion.class);
	}
}
