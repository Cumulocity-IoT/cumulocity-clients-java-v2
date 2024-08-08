// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ApplicationBinaries;
import com.cumulocity.client.model.Application;

/**
 * <p>An API method to upload an application binary. It is a deployable microservice or web application.</p>
 */
public class ApplicationBinariesApi extends AdaptableApi {

	public ApplicationBinariesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all application attachments</p>
	 * <p>Retrieve all application attachments.This method is not supported by microservice applications.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the application attachments are sent in the response.</p></p>
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
	public CompletionStage<ApplicationBinaries> getApplicationAttachments(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.applicationbinaries+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ApplicationBinaries.class);
	}
	
	/**
	 * <p>Upload an application attachment</p>
	 * <p>Upload an application attachment (by a given application ID).</p>
	 * <p>For the applications of type ���microservice��� and ���web application��� to be available for Cumulocity IoT platform users, an attachment ZIP file must be uploaded.</p>
	 * <p>For a microservice application, the ZIP file must consist of:</p>
	 * <ul>
	 * 	<li><p>cumulocity.json - file describing the deployment</p>
	 * 	</li>
	 * 	<li><p>image.tar - executable Docker image</p>
	 * 	</li>
	 * </ul>
	 * <p>For a web application, the ZIP file must include an index.html file in the root directory.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The application attachments have been uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param file
	 * <p>The ZIP file to be uploaded.</p>
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 */
	public CompletionStage<Application> uploadApplicationAttachment(final byte[] file, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("file", file, MediaType.valueOf("application/zip"));
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), Application.class);
	}
	
	/**
	 * <p>Retrieve a specific application attachment</p>
	 * <p>Retrieve a specific application attachment (by a given application ID and a given binary ID).This method is not supported by microservice applications.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the application attachment is sent as a ZIP file in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param binaryId
	 * <p>Unique identifier of the binary.</p>
	 */
	public CompletionStage<Response> getApplicationAttachment(final String id, final String binaryId) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/zip")
			.rx()
			.method("GET");
	}
	
	/**
	 * <p>Delete a specific application attachment</p>
	 * <p>Delete  a specific application attachment (by a given application ID and a given binary ID).This method is not supported by microservice applications.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An application binary was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the application.</p>
	 * @param binaryId
	 * <p>Unique identifier of the binary.</p>
	 */
	public CompletionStage<Response> deleteApplicationAttachment(final String id, final String binaryId) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
