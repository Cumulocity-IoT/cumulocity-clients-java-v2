// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
 * Managed objects can perform operations to store, retrieve and delete binaries. One binary can store only one file. Together with the binary, a managed object is created which acts as a metadata information for the binary.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class BinariesApi extends AdaptableApi {

	public BinariesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the stored files
	 * Retrieve the stored files as a collections of managed objects.
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the managed objects are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param currentPage The current page of the paginated results.
	 * @param ids The managed object IDs to search for. >**&#9432; Info:** If you query for multiple IDs at once, comma-separate the values. 
	 * @param owner Username of the owner of the managed objects.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
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
	 * Upload a file
	 * Uploading a file (binary) requires providing the following properties:
	 * 
	 * * `object` – In JSON format, it contains information about the file.
	 * * `file` – Contains the file to be uploaded.
	 * 
	 * After the file has been uploaded, the corresponding managed object will contain the fragment `c8y_IsBinary`.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A file was uploaded.</li>
	 *     <li>HTTP 400 - Unprocessable Entity – invalid payload.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 * </ul>
	 * @param pObject 
	 * @param file Path of the file to be uploaded.
	 * @return
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
	 * Retrieve a stored file
	 * Retrieve a stored file (managed object) by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_READ permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the file is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 */
	public CompletionStage<Response> getBinary(final String id) {
		return adapt().path("inventory").path("binaries").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
	
	/**
	 * Replace a file
	 * Upload and replace the attached file (binary) of a specific managed object by a given ID.<br>
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A file was uploaded.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @return
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
	 * Remove a stored file
	 * Remove a managed object and its stored file by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A managed object and its stored file was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 */
	public CompletionStage<Response> removeBinary(final String id) {
		return adapt().path("inventory").path("binaries").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
