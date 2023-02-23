// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RealtimeNotification {

	/**
	 * Configuration parameters for the current connect message.
	 */
	private Advice advice;

	/**
	 * The channel name as a URI.
	 */
	private Channel channel;

	/**
	 * Unique client ID generated by the server during handshake. Required for all other operations.
	 */
	private String clientId;

	/**
	 * Selected connection type.
	 */
	private String connectionType;

	/**
	 * List of notifications from the channel.
	 */
	private Data data;

	/**
	 * Operation failure reason (only present if the operation was not successful).
	 */
	private String error;

	/**
	 * Authentication object passed to handshake (only over WebSockets).
	 */
	private Ext ext;

	/**
	 * ID of the message passed in a request. Required to match the response message.
	 */
	private String id;

	/**
	 * Minimum server-side Bayeux protocol version required by the client (in a request) or minimum client-side Bayeux protocol version required by the server (in a response).
	 */
	private String minimumVersion;

	/**
	 * Name of the channel to subscribe to. Subscription channels are available for [Alarms](#tag/Alarm-notification-API), [Device control](#tag/Device-control-notification-API), [Events](#tag/Event-notification-API), [Inventory](#tag/Inventory-notification-API) and [Measurements](#tag/Measurement-notification-API).
	 */
	private String subscription;

	/**
	 * Indicates if the operation was successful.
	 */
	private boolean successful;

	/**
	 * Connection types supported by both client and server, that is, intersection between client and server options.
	 */
	private String[] supportedConnectionTypes;

	/**
	 * [Bayeux protocol](https://docs.cometd.org/current/reference/#_concepts_bayeux_protocol) version used by the client (in a request) or server (in a response).
	 * 
	 */
	private String version;

	public RealtimeNotification() {
	}

	public RealtimeNotification(final Channel channel) {
		this.channel = channel;
	}

	public Advice getAdvice() {
		return advice;
	}
	
	public void setAdvice(final Advice advice) {
		this.advice = advice;
	}

	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(final Channel channel) {
		this.channel = channel;
	}

	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public String getConnectionType() {
		return connectionType;
	}
	
	public void setConnectionType(final String connectionType) {
		this.connectionType = connectionType;
	}

	public Data getData() {
		return data;
	}
	
	public void setData(final Data data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}
	
	public void setError(final String error) {
		this.error = error;
	}

	public Ext getExt() {
		return ext;
	}
	
	public void setExt(final Ext ext) {
		this.ext = ext;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getMinimumVersion() {
		return minimumVersion;
	}
	
	public void setMinimumVersion(final String minimumVersion) {
		this.minimumVersion = minimumVersion;
	}

	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(final String subscription) {
		this.subscription = subscription;
	}

	public boolean getSuccessful() {
		return successful;
	}
	
	public void setSuccessful(final boolean successful) {
		this.successful = successful;
	}

	public String[] getSupportedConnectionTypes() {
		return supportedConnectionTypes;
	}
	
	public void setSupportedConnectionTypes(final String[] supportedConnectionTypes) {
		this.supportedConnectionTypes = supportedConnectionTypes;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	
	/**
	 * The channel name as a URI.
	 */
	public enum Channel {
		@JsonProperty("/meta/handshake")
		METAHANDSHAKE("/meta/handshake"),
		@JsonProperty("/meta/subscribe")
		METASUBSCRIBE("/meta/subscribe"),
		@JsonProperty("/meta/unsubscribe")
		METAUNSUBSCRIBE("/meta/unsubscribe"),
		@JsonProperty("/meta/connect")
		METACONNECT("/meta/connect"),
		@JsonProperty("/meta/disconnect")
		METADISCONNECT("/meta/disconnect");
	
		private String value;
	
		Channel(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * Configuration parameters for the current connect message.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Advice {
	
		/**
		 * Period (milliseconds) after which the server will close the session, if it doesn't received the next connect message from the client. Overrides server default settings for current request-response conversation.
		 */
		private int interval;
	
		/**
		 * Interval (milliseconds) between the sending of the connect message and the response from the server. Overrides server default settings for the current request-response conversation.
		 */
		private int timeout;
	
		public int getInterval() {
			return interval;
		}
		
		public void setInterval(final int interval) {
			this.interval = interval;
		}
	
		public int getTimeout() {
			return timeout;
		}
		
		public void setTimeout(final int timeout) {
			this.timeout = timeout;
		}
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof Advice) {
				Advice comparer = (Advice) r;
				if (Integer.valueOf(comparer.getInterval()).equals(Integer.valueOf(this.getInterval())) && Integer.valueOf(comparer.getTimeout()).equals(Integer.valueOf(this.getTimeout()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * List of notifications from the channel.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Data {
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
	}

	/**
	 * Authentication object passed to handshake (only over WebSockets).
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Ext {
	
		@JsonProperty(value = "com.cumulocity.authn")
		private Comcumulocityauthn comcumulocityauthn;
	
		/**
		 * The system of units to use.
		 */
		private SystemOfUnits systemOfUnits;
	
		public Comcumulocityauthn getComcumulocityauthn() {
			return comcumulocityauthn;
		}
		
		public void setComcumulocityauthn(final Comcumulocityauthn comcumulocityauthn) {
			this.comcumulocityauthn = comcumulocityauthn;
		}
	
		public SystemOfUnits getSystemOfUnits() {
			return systemOfUnits;
		}
		
		public void setSystemOfUnits(final SystemOfUnits systemOfUnits) {
			this.systemOfUnits = systemOfUnits;
		}
	
		
		/**
		 * The system of units to use.
		 */
		public enum SystemOfUnits {
			@JsonProperty("imperial")
			IMPERIAL("imperial"),
			@JsonProperty("metric")
			METRIC("metric");
		
			private String value;
		
			SystemOfUnits(final String value) {
				this.value = value;
			}
		
			public String getValue() {
				return value;
			}
		}
	
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Comcumulocityauthn {
		
			/**
			 * Base64 encoded credentials.
			 */
			private String token;
		
			/**
			 * Optional two factor authentication token.
			 */
			private String tfa;
		
			/**
			 * Required for OAuth authentication.
			 */
			private String xsrfToken;
		
			public String getToken() {
				return token;
			}
			
			public void setToken(final String token) {
				this.token = token;
			}
		
			public String getTfa() {
				return tfa;
			}
			
			public void setTfa(final String tfa) {
				this.tfa = tfa;
			}
		
			public String getXsrfToken() {
				return xsrfToken;
			}
			
			public void setXsrfToken(final String xsrfToken) {
				this.xsrfToken = xsrfToken;
			}
		
			@Override
			public String toString() {
				try {
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof Comcumulocityauthn) {
					Comcumulocityauthn comparer = (Comcumulocityauthn) r;
					if (String.valueOf(comparer.getToken()).equals(String.valueOf(this.getToken())) && String.valueOf(comparer.getTfa()).equals(String.valueOf(this.getTfa())) && String.valueOf(comparer.getXsrfToken()).equals(String.valueOf(this.getXsrfToken()))) {
						return true;
					}
				}
				return false;
			}
		}
	
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof Ext) {
				Ext comparer = (Ext) r;
				if (comparer.getComcumulocityauthn().equals(this.getComcumulocityauthn()) && comparer.getSystemOfUnits().equals(this.getSystemOfUnits())) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof RealtimeNotification) {
			RealtimeNotification comparer = (RealtimeNotification) r;
			if (comparer.getAdvice().equals(this.getAdvice()) && comparer.getChannel().equals(this.getChannel()) && String.valueOf(comparer.getClientId()).equals(String.valueOf(this.getClientId())) && String.valueOf(comparer.getConnectionType()).equals(String.valueOf(this.getConnectionType())) && comparer.getData().equals(this.getData()) && String.valueOf(comparer.getError()).equals(String.valueOf(this.getError())) && comparer.getExt().equals(this.getExt()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getMinimumVersion()).equals(String.valueOf(this.getMinimumVersion())) && String.valueOf(comparer.getSubscription()).equals(String.valueOf(this.getSubscription())) && Boolean.valueOf(comparer.getSuccessful()).equals(Boolean.valueOf(this.getSuccessful())) && comparer.getSupportedConnectionTypes().equals(this.getSupportedConnectionTypes()) && String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion()))) {
				return true;
			}
		}
		return false;
	}
}
