// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NotificationSubscription;
import com.cumulocity.client.model.NotificationSubscriptionCollection;

/**
 * <p>Methods to create, retrieve and delete notification subscriptions.</p>
 */
public class SubscriptionsApi extends AdaptableApi {

	public SubscriptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all subscriptions</p>
	 * <p>Retrieve all subscriptions on your tenant, or a specific subset based on queries.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all subscriptions are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param context
	 * <p>The context to which the subscription is associated.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param source
	 * <p>The managed object ID to which the subscription is associated.</p>
	 * @param subscription
	 * <p>The subscription name by which filtering will be done.</p>
	 * @param typeFilter
	 * <p>The type used to filter subscriptions. This will check the subscription's <code>subscriptionFilter.typeFilter</code> field.</p>
	 * <p><strong>ⓘ Info:</strong> Filtering by <code>typeFilter</code> may affect paging. Additional post filtering may be performed if OData-like expressions are used in the subscriptions.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<NotificationSubscriptionCollection> getSubscriptions(final String context, final int currentPage, final int pageSize, final String source, final String subscription, final String typeFilter, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("source", source)
			.queryParam("subscription", subscription)
			.queryParam("typeFilter", typeFilter)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscriptioncollection+json")
			.rx()
			.method("GET", NotificationSubscriptionCollection.class);
	}
	
	/**
	 * <p>Create a subscription</p>
	 * <p>Create a new subscription, for example, a subscription that forwards measurements and events of a specific type for a given device.</p>
	 * <p>In general, each subscription may consist of:</p>
	 * <ul>
	 * 	<li><p>The managed object to which the subscription is associated.</p>
	 * 	</li>
	 * 	<li><p>The context under which the subscription is to be processed.</p>
	 * 	</li>
	 * 	<li><p>The name of the subscription.</p>
	 * 	</li>
	 * 	<li><p>The applicable filter criteria.</p>
	 * 	</li>
	 * 	<li><p>The option to only include specific custom fragments in the forwarded data.</p>
	 * 	</li>
	 * 	<li><p>The option to use persistent or non-persistent message storage.</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A notification subscription was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Managed object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicated subscription.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<NotificationSubscription> createSubscription(final NotificationSubscription body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("notification2").path("subscriptions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.subscription+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
			.rx()
			.method("POST", Entity.json(jsonNode), NotificationSubscription.class);
	}
	
	/**
	 * <p>Remove subscriptions by source</p>
	 * <p>Remove subscriptions by source and context.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> The request will result in an error if there are no query parameters. The <code>source</code> parameter is optional only if the <code>context</code> parameter equals <code>tenant</code>.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A collection of subscriptions was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� error in query parameters</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param context
	 * <p>The context to which the subscription is associated.</p>
	 * <p><strong>ⓘ Info:</strong> If the value is <code>mo</code> (managed object), then <code>source</code> must also be provided in the query.</p>
	 * @param source
	 * <p>The managed object ID to which the subscription is associated.</p>
	 */
	public CompletionStage<Response> deleteSubscriptions(final String xCumulocityProcessingMode, final String context, final String source) {
		return adapt().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("source", source)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a specific subscription</p>
	 * <p>Retrieve a specific subscription by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the subscription is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Subscription not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the notification subscription.</p>
	 */
	public CompletionStage<NotificationSubscription> getSubscription(final String id) {
		return adapt().path("notification2").path("subscriptions").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
			.rx()
			.method("GET", NotificationSubscription.class);
	}
	
	/**
	 * <p>Remove a specific subscription</p>
	 * <p>Remove a specific subscription by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A subscription was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Subscription not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the notification subscription.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> deleteSubscription(final String id, final String xCumulocityProcessingMode) {
		return adapt().path("notification2").path("subscriptions").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
