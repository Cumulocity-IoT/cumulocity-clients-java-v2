// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NotificationTokenClaims;
import com.cumulocity.client.model.NotificationToken;
import com.cumulocity.client.model.NotificationSubscriptionResult;

/**
 * <p>In order to receive subscribed notifications, a consumer application or microservice must obtain an authorization token that provides proof that the holder is allowed to receive subscribed notifications.</p>
 */
public class TokensApi extends AdaptableApi {

	public TokensApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Create a notification token</p>
	 * <p>Create a new JWT (JSON web token) access token which can be used to establish a successful WebSocket connection to read a sequence of notifications.</p>
	 * <p>In general, each request to obtain an access token consists of:</p>
	 * <ul>
	 * 	<li><p>The subscriber name which the client wishes to be identified with.</p>
	 * 	</li>
	 * 	<li><p>The subscription name. This value must be associated with a subscription that's already been created and in essence, the obtained token will give the ability to read notifications for the subscription that is specified here.</p>
	 * 	</li>
	 * 	<li><p>The token expiration duration.</p>
	 * 	</li>
	 * 	<li><p>The option to disable signing of the token by the Cumulocity platform.</p>
	 * 	</li>
	 * 	<li><p>The subscription type that the token should be associated with.</p>
	 * 	</li>
	 * 	<li><p>The option to use the token to create shared consumers of the subscription.</p>
	 * 	</li>
	 * 	<li><p>The option to select the non-persistent variant of the subscription, if one exists.</p>
	 * 	</li>
	 * </ul>
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A notification token was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<NotificationToken> createToken(final NotificationTokenClaims body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("notification2").path("token")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), NotificationToken.class);
	}
	
	/**
	 * <p>Unsubscribe a subscriber</p>
	 * <p>Unsubscribe a notification subscriber using the notification token.</p>
	 * <p>Once a subscription is made, notifications will be kept until they are consumed by all subscribers who have previously connected to the subscription. For non-volatile subscriptions, this can result in notifications remaining in storage if never consumed by the application.They will be deleted if a tenant is deleted. It can take up considerable space in permanent storage for high-frequency notification sources. Therefore, we recommend you to unsubscribe a subscriber that will never run again.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The notification subscription was deleted or is scheduled for deletion.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param token
	 * <p>Subscriptions associated with this token will be removed.</p>
	 */
	public CompletionStage<NotificationSubscriptionResult> unsubscribeSubscriber(final String xCumulocityProcessingMode, final String token) {
		return adapt().path("notification2").path("unsubscribe")
			.queryParam("token", token)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", NotificationSubscriptionResult.class);
	}
}
