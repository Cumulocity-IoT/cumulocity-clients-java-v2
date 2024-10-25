// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
import com.cumulocity.client.model.BinaryCollection;
import com.cumulocity.client.model.Binary;

/**
 * <p>Managed objects can perform operations to store, retrieve and delete binaries. One binary can store only one file. Together with the binary, a managed object is created which acts as a metadata information for the binary.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> Supports only HTTP 1.1 clients.<strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class BinariesApi extends AdaptableApi {

	public BinariesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Search for stored files</p>
	 * <p>Retrieve metadata information about stored files. Search for files by query parameters. This will not download the files.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the managed objects are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param childAdditionId
	 * <p>Search for a specific child addition and list all the groups to which it belongs.</p>
	 * @param childAssetId
	 * <p>Search for a specific child asset and list all the groups to which it belongs.</p>
	 * @param childDeviceId
	 * <p>Search for a specific child device and list all the groups to which it belongs.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param ids
	 * <p>The managed object IDs to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple IDs at once, comma-separate the values.</p>
	 * @param owner
	 * <p>Username of the owner of the managed objects.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param text
	 * <p>Search for managed objects where a property value is equal to the given one.The following properties are examined: <code>id, type, name, owner, externalIds</code>.</p>
	 * @param type
	 * <p>The type of managed object to search for.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<BinaryCollection> getBinaries(final String childAdditionId, final String childAssetId, final String childDeviceId, final int currentPage, final String[] ids, final String owner, final int pageSize, final String text, final String type, final boolean withTotalPages) {
		return adapt().path("inventory").path("binaries")
			.queryParam("childAdditionId", childAdditionId)
			.queryParam("childAssetId", childAssetId)
			.queryParam("childDeviceId", childDeviceId)
			.queryParam("currentPage", currentPage)
			.queryParam("ids", ids, false)
			.queryParam("owner", owner)
			.queryParam("pageSize", pageSize)
			.queryParam("text", text)
			.queryParam("type", type)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectcollection+json")
			.rx()
			.method("GET", BinaryCollection.class);
	}
	
	/**
	 * <p>Upload a file</p>
	 * <p>Uploading a file (binary) requires providing the following properties:</p>
	 * <ul>
	 * 	<li><p><code>object</code> ��� In JSON format, it contains information about the file.</p>
	 * 	</li>
	 * 	<li><p><code>file</code> ��� Contains the file to be uploaded.</p>
	 * 	</li>
	 * </ul>
	 * <p>After the file has been uploaded, the corresponding managed object will contain the fragment <code>c8y_IsBinary</code>.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE <b>OR</b> ROLE_BINARY_ADMIN <b>OR</b> ROLE_BINARY_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A file was uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param pObject
	 * @param file
	 * <p>Path of the file to be uploaded.</p>
	 */
	public CompletionStage<Binary> uploadBinary(final BinaryInfo pObject, final byte[] file) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("object", pObject, MediaType.valueOf("application/json"));
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("inventory").path("binaries")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), Binary.class);
	}
	
	/**
	 * <p>Retrieve a stored file</p>
	 * <p>Retrieve a stored file (managed object) by a given ID.Supports chunk download and resuming an interrupted download using the <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Range"><code>Range</code> header</a>.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_BINARY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_READ permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the file is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<Response> getBinary(final String id) {
		return adapt().path("inventory").path("binaries").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
	
	/**
	 * <p>Replace a file</p>
	 * <p>Upload and replace the attached file (binary) of a specific managed object by a given ID.<br></p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_BINARY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A file was uploaded.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<Binary> replaceBinary(final byte[] body, final String id) {
		return adapt().path("inventory").path("binaries").path(valueOf(id))
			.request()
			.header("Content-Type", "text/plain")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
			.rx()
			.method("PUT", Entity.text(body), Binary.class);
	}
	
	/**
	 * <p>Remove a stored file</p>
	 * <p>Remove a managed object and its stored file by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_BINARY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A managed object and its stored file was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<Response> removeBinary(final String id) {
		return adapt().path("inventory").path("binaries").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
