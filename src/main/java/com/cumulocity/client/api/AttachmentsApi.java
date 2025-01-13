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
import com.cumulocity.client.model.BinaryInfo;
import com.cumulocity.client.model.EventBinary;

/**
 * <p>It is possible to store, retrieve and delete binaries for events. Each event can have only one binary attached.</p>
 */
public class AttachmentsApi extends AdaptableApi {

	public AttachmentsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve the attached file of a specific event</p>
	 * <p>Retrieve the attached file (binary) of a specific event by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_READ <b>OR</b> EVENT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the file is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Attachment not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<Response> getEventAttachment(final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
	
	/**
	 * <p>Replace the attached file of a specific event</p>
	 * <p>Upload and replace the attached file (binary) of a specific event by a given ID.<br>The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A file was uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<EventBinary> replaceEventAttachment(final byte[] body, final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "text/plain")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("PUT", Entity.text(body), EventBinary.class);
	}
	
	/**
	 * <p>Attach a file to a specific event</p>
	 * <p>Upload a file (binary) as an attachment of a specific event by a given ID.The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> If there is a binary already attached to the event, the POST request results in a 409 error.</p>
	 * </blockquote>
	 * <p>When the file has been uploaded, the corresponding event contains the fragment <code>c8y_IsBinary</code> similar to:</p>
	 * <pre>
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * </pre>
	 * <p>There are two request body schemas you can use for your POST requests.<code>text/plain</code> is preselected (see below).If you set it to <code>multipart/form-data</code> each value is sent as a block of data (body part), with a user agent-defined delimiter (<code>boundary</code>) separating each part.The keys are given in the <code>Content-Disposition</code> header of each part.</p>
	 * <pre>
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * </pre>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A file was uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>An attachment exists already.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<EventBinary> uploadEventAttachment(final byte[] body, final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "text/plain")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.text(body), EventBinary.class);
	}
	
	/**
	 * <p>Attach a file to a specific event</p>
	 * <p>Upload a file (binary) as an attachment of a specific event by a given ID.The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> If there is a binary already attached to the event, the POST request results in a 409 error.</p>
	 * </blockquote>
	 * <p>When the file has been uploaded, the corresponding event contains the fragment <code>c8y_IsBinary</code> similar to:</p>
	 * <pre>
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * </pre>
	 * <p>There are two request body schemas you can use for your POST requests.<code>text/plain</code> is preselected (see below).If you set it to <code>multipart/form-data</code> each value is sent as a block of data (body part), with a user agent-defined delimiter (<code>boundary</code>) separating each part.The keys are given in the <code>Content-Disposition</code> header of each part.</p>
	 * <pre>
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * </pre>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A file was uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>An attachment exists already.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param pObject
	 * @param file
	 * <p>Path of the file to be uploaded.</p>
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<EventBinary> uploadEventAttachment(final BinaryInfo pObject, final byte[] file, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("object", pObject, MediaType.valueOf("application/json"));
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), EventBinary.class);
	}
	
	/**
	 * <p>Remove the attached file from a specific event</p>
	 * <p>Remove the attached file (binary) from a specific event by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A file was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Event not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the event.</p>
	 */
	public CompletionStage<Response> deleteEventAttachment(final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
