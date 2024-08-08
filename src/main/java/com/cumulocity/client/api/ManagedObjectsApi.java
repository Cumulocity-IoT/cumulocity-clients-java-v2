// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ManagedObject;
import com.cumulocity.client.model.ManagedObjectUser;
import com.cumulocity.client.model.ManagedObjectCollection;
import com.cumulocity.client.model.SupportedMeasurements;
import com.cumulocity.client.model.SupportedSeries;

/**
 * <p>The inventory stores devices and other assets relevant to your IoT solution. We refer to them as managed objects and such can be ���smart objects���, for example, smart electricity meters, home automation gateways or GPS devices.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class ManagedObjectsApi extends AdaptableApi {

	public ManagedObjectsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all managed objects</p>
	 * <p>Retrieve all managed objects (for example, devices, assets, etc.) registered in your tenant, or a subset based on queries.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the collection of objects is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Invalid data was sent.</p></p>
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
	 * @param fragmentType
	 * <p>A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.</p>
	 * @param ids
	 * <p>The managed object IDs to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you query for multiple IDs at once, comma-separate the values.</p>
	 * @param onlyRoots
	 * <p>When set to <code>true</code> it returns managed objects which don't have any parent. If the current user doesn't have access to the parent, this is also root for the user.</p>
	 * @param owner
	 * <p>Username of the owner of the managed objects.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param q
	 * <p>Similar to the parameter <code>query</code>, but it assumes that this is a device query request and it adds automatically the search criteria <code>fragmentType=c8y_IsDevice</code>.</p>
	 * @param query
	 * <p>Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in <a href="#tag/Query-language">Query language</a>.</p>
	 * @param skipChildrenNames
	 * <p>When set to <code>true</code>, the returned references of child devices won't contain their names.</p>
	 * @param text
	 * <p>Search for managed objects where a property value is equal to the given one.The following properties are examined: <code>id, type, name, owner, externalIds</code>.</p>
	 * @param type
	 * <p>The type of managed object to search for.</p>
	 * @param withChildren
	 * <p>Determines if children with ID and name should be returned when fetching the managed object. Set it to <code>false</code> to improve query performance.</p>
	 * @param withChildrenCount
	 * <p>When set to <code>true</code>, the returned result will contain the total number of children in the respective objects (<code>childAdditions</code>, <code>childAssets</code> and <code>childDevices</code>).</p>
	 * @param withGroups
	 * <p>When set to <code>true</code> it returns additional information about the groups to which the searched managed object belongs. This results in setting the <code>assetParents</code> property with additional information about the groups.</p>
	 * @param withParents
	 * <p>When set to <code>true</code>, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withLatestValues
	 * <p>If set to true the platform returns managed objects with the fragment `c8y_LatestMeasurements, which contains the latest measurement values reported by the device to the platform.</p>
	 * <p><strong>������ Feature Preview:</strong> The parameter is a part of the Latest Measurement feature which is still under public preview.</p>
	 */
	public CompletionStage<ManagedObjectCollection> getManagedObjects(final String childAdditionId, final String childAssetId, final String childDeviceId, final int currentPage, final String fragmentType, final String[] ids, final boolean onlyRoots, final String owner, final int pageSize, final String q, final String query, final boolean skipChildrenNames, final String text, final String type, final boolean withChildren, final boolean withChildrenCount, final boolean withGroups, final boolean withParents, final boolean withTotalElements, final boolean withTotalPages, final boolean withLatestValues) {
		return adapt().path("inventory").path("managedObjects")
			.queryParam("childAdditionId", childAdditionId)
			.queryParam("childAssetId", childAssetId)
			.queryParam("childDeviceId", childDeviceId)
			.queryParam("currentPage", currentPage)
			.queryParam("fragmentType", fragmentType)
			.queryParam("ids", ids, false)
			.queryParam("onlyRoots", onlyRoots)
			.queryParam("owner", owner)
			.queryParam("pageSize", pageSize)
			.queryParam("q", q)
			.queryParam("query", query)
			.queryParam("skipChildrenNames", skipChildrenNames)
			.queryParam("text", text)
			.queryParam("type", type)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withGroups", withGroups)
			.queryParam("withParents", withParents)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.queryParam("withLatestValues", withLatestValues)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectcollection+json")
			.rx()
			.method("GET", ManagedObjectCollection.class);
	}
	
	/**
	 * <p>Create a managed object</p>
	 * <p>Create a managed object, for example, a device with temperature measurements support or a binary switch.<br>In general, each managed object may consist of:</p>
	 * <ul>
	 * 	<li><p>A unique identifier that references the object.</p>
	 * 	</li>
	 * 	<li><p>The name of the object.</p>
	 * 	</li>
	 * 	<li><p>The most specific type of the managed object.</p>
	 * 	</li>
	 * 	<li><p>A time stamp showing the last update.</p>
	 * 	</li>
	 * 	<li><p>Fragments with specific meanings, for example, <code>c8y_IsDevice</code>, <code>c8y_SupportedOperations</code>.</p>
	 * 	</li>
	 * 	<li><p>Any additional custom fragments.</p>
	 * 	</li>
	 * </ul>
	 * <p>Imagine, for example, that you want to describe electric meters from different vendors. Depending on the make of the meter, one may have a relay and one may be capable to measure a single phase or three phases (for example, a three-phase electricity sensor). A fragment <code>c8y_ThreePhaseElectricitySensor</code> would identify such an electric meter. Devices' characteristics are identified by storing fragments for each of them.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> For more details about fragments with specific meanings, review the sections <a href="#section/Device-management-library">Device management library</a> and <a href="#section/Sensor-library">Sensor library</a>.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_CREATE
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A managed object was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<ManagedObject> createManagedObject(final ManagedObject body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "c8y_LatestMeasurements");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return adapt().path("inventory").path("managedObjects")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
			.rx()
			.method("POST", Entity.json(jsonNode), ManagedObject.class);
	}
	
	/**
	 * <p>Retrieve a specific managed object</p>
	 * <p>Retrieve a specific managed object (for example, device, group, template) by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the object is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param skipChildrenNames
	 * <p>When set to <code>true</code>, the returned references of child devices won't contain their names.</p>
	 * @param withChildren
	 * <p>Determines if children with ID and name should be returned when fetching the managed object. Set it to <code>false</code> to improve query performance.</p>
	 * @param withChildrenCount
	 * <p>When set to <code>true</code>, the returned result will contain the total number of children in the respective objects (<code>childAdditions</code>, <code>childAssets</code> and <code>childDevices</code>).</p>
	 * @param withParents
	 * <p>When set to <code>true</code>, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.</p>
	 * @param withLatestValues
	 * <p>If set to true the platform returns managed objects with the fragment `c8y_LatestMeasurements, which contains the latest measurement values reported by the device to the platform.</p>
	 * <p><strong>������ Feature Preview:</strong> The parameter is a part of the Latest Measurement feature which is still under public preview.</p>
	 */
	public CompletionStage<ManagedObject> getManagedObject(final String id, final boolean skipChildrenNames, final boolean withChildren, final boolean withChildrenCount, final boolean withParents, final boolean withLatestValues) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id))
			.queryParam("skipChildrenNames", skipChildrenNames)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withParents", withParents)
			.queryParam("withLatestValues", withLatestValues)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
			.rx()
			.method("GET", ManagedObject.class);
	}
	
	/**
	 * <p>Update a specific managed object</p>
	 * <p>Update a specific managed object (for example, device) by a given ID.</p>
	 * <p>For example, if you want to specify that your managed object is a device, you must add the fragment <code>c8y_IsDevice</code>.</p>
	 * <p>The endpoint can also be used as a device availability heartbeat.If you only specifiy the <code>id</code>, it updates the date when the last message was received and no other property.The response then only contains the <code>id</code> instead of the full managed object.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A managed object was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<ManagedObject> updateManagedObject(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "c8y_LatestMeasurements");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return adapt().path("inventory").path("managedObjects").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), ManagedObject.class);
	}
	
	/**
	 * <p>Remove a specific managed object</p>
	 * <p>Remove a specific managed object (for example, device) by a given ID.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> Inventory DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when the deleted managed object has a lot of associated data. After sending the request, the platform starts deleting the associated data in an asynchronous way. Finally, the requested managed object is deleted after all associated data has been deleted.</p>
	 * </blockquote>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> By default, the delete operation is always propagated to the subgroups, but only if the deleted object is a group.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A managed object was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Conflict ��� The managed object is associated to other objects, for example child devices.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param cascade
	 * <p>When set to <code>true</code> and the managed object is a device or group, all the hierarchy will be deleted.</p>
	 * @param forceCascade
	 * <p>When set to <code>true</code> all the hierarchy will be deleted without checking the type of managed object. It takes precedence over the parameter <code>cascade</code>.</p>
	 * @param withDeviceUser
	 * <p>When set to <code>true</code> and the managed object is a device, it deletes the associated device user (credentials).</p>
	 */
	public CompletionStage<Response> deleteManagedObject(final String id, final String xCumulocityProcessingMode, final boolean cascade, final boolean forceCascade, final boolean withDeviceUser) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id))
			.queryParam("cascade", cascade)
			.queryParam("forceCascade", forceCascade)
			.queryParam("withDeviceUser", withDeviceUser)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve the latest availability date of a specific managed object</p>
	 * <p>Retrieve the date when a specific managed object (by a given ID) sent the last message to Cumulocity IoT.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the date is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<String> getLatestAvailability(final String id) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("availability")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
			.rx()
			.method("GET", String.class);
	}
	
	/**
	 * <p>Retrieve all supported measurement fragments of a specific managed object</p>
	 * <p>Retrieve all measurement types of a specific managed object by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all measurement types are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<SupportedMeasurements> getSupportedMeasurements(final String id) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("supportedMeasurements")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", SupportedMeasurements.class);
	}
	
	/**
	 * <p>Retrieve all supported measurement fragments and series of a specific managed object</p>
	 * <p>Retrieve all supported measurement fragments and series of a specific managed object by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all supported measurement series are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<SupportedSeries> getSupportedSeries(final String id) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("supportedSeries")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", SupportedSeries.class);
	}
	
	/**
	 * <p>Retrieve the username and state of a specific managed object</p>
	 * <p>Retrieve the device owner's username and state (enabled or disabled) of a specific managed object (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> ROLE_MANAGED_OBJECT_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the username and state are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 */
	public CompletionStage<ManagedObjectUser> getManagedObjectUser(final String id) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("user")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ManagedObjectUser.class);
	}
	
	/**
	 * <p>Update the user's details of a specific managed object</p>
	 * <p>Update the device owner's state (enabled or disabled) of a specific managed object (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_MANAGED_OBJECT_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The user's details of a specific managed object were updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param id
	 * <p>Unique identifier of the managed object.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<ManagedObjectUser> updateManagedObjectUser(final ManagedObjectUser body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "userName");
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("user")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectuser+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), ManagedObjectUser.class);
	}
}
