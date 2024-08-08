// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.RealtimeNotification;

/**
 * <h1>Real-time operations</h1>
 * <p>Real-time notification services of Cumulocity IoT have their own subscription channel name format and URL. The real-time notifications are available for <a href="#tag/Alarm-notification-API">Alarms</a>, <a href="#tag/Device-control-notification-API">Device control</a>, <a href="#tag/Event-notification-API">Events</a>, <a href="#tag/Inventory-notification-API">Inventory</a> and <a href="#tag/Measurement-notification-API">Measurements</a>.</p>
 * <p>Note that when using long-polling, all POST requests must contain the Accept header, otherwise an empty response body will be returned.All requests are sent to the <kbd>/notification/realtime</kbd> endpoint.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The long-polling interface is designed as a mechanism for custom applications to poll infrequent events from Cumulocity IoT. The long-polling interface is not designed as a mechanism to stream large data volumes (>100kB/sec) or frequent data (>50 events/sec) out of Cumulocity IoT. The usage of long-polling is not supported for such use cases.</p>
 * </blockquote>
 * <h2>Handshake</h2>
 * <p>A real-time notifications client initiates the connection negotiation by sending a message to the <code>/meta/handshake</code> channel. In response, the client receives a <code>clientId</code> which identifies a conversation and must be passed in every non-handshake request.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The number of parallel connections that can be opened at the same time by a single user is limited. After exceeding this limit when a new connection is created, the oldest one will be closed and the newly created one will be added in its place. This limit is configurable and managed per installation. Its default value is 10 connections per user, subscription channel and server node.</p>
 * </blockquote>
 * <p>When using WebSockets, a property <code>ext</code> containing an authentication object must be sent. In case of basic authentication, the token is used with Base64 encoded credentials. In case of OAuth authentication, the request must have the cookie with the authorization name, holding the access token. Moreover, the XSRF token must be forwarded as part of the handshake message.</p>
 * <h3>Request example</h3>
 * <pre>
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "version": "1.0"
 *   }
 * ]
 * </pre>
 * <h3>Response example</h3>
 * <p>A successful response looks like:</p>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "minimumVersion": "1.0",
 *     "successful": true,
 *     "supportedConnectionTypes": [
 *       "long-polling",
 *       "smartrest-long-polling",
 *       "websocket"
 *     ],
 *     "version": "1.0"
 *   }
 * ]
 * </pre>
 * <p>When an error occurs, the response looks like:</p>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "error": "403::Handshake denied",
 *     "successful": false
 *   }
 * ]
 * </pre>
 * <h2>Subscribe</h2>
 * <p>A notification client can send subscribe messages and specify the desired channel to receive output messages from the Cumulocity IoT server. The client will receive the messages in succeeding connect requests.</p>
 * <p>Each REST API that uses the real-time notification service has its own format for channel names. See <a href="#tag/Device-control-notification-API">Device control</a> for more details.</p>
 * <h3>Request example</h3>
 * <pre>
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/subscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>"
 *   }
 * ]
 * </pre>
 * <h3>Response example</h3>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/subscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>",
 *     "successful": true
 *   }
 * ]
 * </pre>
 * <h2>Unsubscribe</h2>
 * <p>To stop receiving notifications from a channel, send a message to the channel <code>/meta/unsubscribe</code> in the same format as used during subscription.</p>
 * <h3>Request example</h3>
 * <p>Example Request:</p>
 * <pre>
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/unsubscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>"
 *   }
 * ]
 * </pre>
 * <h3>Response example</h3>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/unsubscribe",
 *     "subscription": "/alarms/<DEVICE_ID>",
 *     "successful": true
 *   }
 * ]
 * </pre>
 * <h2>Connect</h2>
 * <p>After a Bayeux client has discovered the server's capabilities with a handshake exchange and subscribed to the desired channels, a connection is established by sending a message to the <code>/meta/connect</code> channel. This message may be transported over any of the transports returned by the server in the handshake response. Requests to the connect channel must be immediately repeated after every response to receive the next batch of notifications.</p>
 * <h3>Request example</h3>
 * <pre>
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/connect",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "connectionType": "long-polling",
 *     "advice": {
 *       "timeout": 5400000,
 *       "interval": 3000
 *     }
 *   }
 * ]
 * </pre>
 * <h3>Response example</h3>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/connect",
 *     "data": null,
 *     "advice": {
 *       "interval": 3000,
 *       "timeout": 5400000
 *     },
 *     "successful": true
 *   }
 * ]
 * </pre>
 * <h2>Disconnect</h2>
 * <p>To stop receiving notifications from all channels and close the conversation, send a message to the <code>/meta/disconnect</code> channel.</p>
 * <h3>Request example</h3>
 * <pre>
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/disconnect",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp"
 *   }
 * ]
 * </pre>
 * <h3>Response example</h3>
 * <pre>
 * [
 *   {
 *     "channel": "/meta/disconnect",
 *     "successful": true
 *   }
 * ]
 * </pre>
 * <h2>Long-running connections</h2>
 * <p>To keep a long-running connection alive when there are no new notifications to deliver, the server will periodically send an empty <code>/meta/connect</code> response to the client.The client should send a new <code>/meta/connect</code> request immediately after receiving such a response, to ensure that the connection remains active and future notifications are delivered.</p>
 */
public class RealtimeNotificationApi extends AdaptableApi {

	public RealtimeNotificationApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Responsive communication</p>
	 * <p>The Real-time notification API enables responsive communication from Cumulocity IoT over restricted networks towards clients such as web browser and mobile devices. All clients subscribe to so-called channels to receive messages. These channels are filled by Cumulocity IoT with the output of <a href="#tag/Operations">Operations</a>. In addition, particular system channels are used for the initial handshake with clients, subscription to channels, removal from channels and connection. The <a href="https://docs.cometd.org/current/reference/#_concepts_bayeux_protocol">Bayeux protocol</a> over HTTPS or WSS is used as communication mechanism.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The operation was completed and the result is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<RealtimeNotification> createRealtimeNotification(final RealtimeNotification body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "clientId");
		removeFromNode(jsonNode, "data");
		removeFromNode(jsonNode, "error");
		removeFromNode(jsonNode, "successful");
		return adapt().path("notification").path("realtime")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), RealtimeNotification.class);
	}
}
