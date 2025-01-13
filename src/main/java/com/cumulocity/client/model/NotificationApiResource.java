// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class NotificationApiResource {

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Collection of all notification subscriptions.</p>
	 */
	private NotificationSubscriptions notificationSubscriptions;

	/**
	 * <p>Read-only collection of all notification subscriptions for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.</p>
	 */
	private String notificationSubscriptionsBySource;

	/**
	 * <p>Read-only collection of all notification subscriptions of a particular context and a specific source object.</p>
	 */
	private String notificationSubscriptionsBySourceAndContext;

	/**
	 * <p>Read-only collection of all notification subscriptions of a particular context.</p>
	 */
	private String notificationSubscriptionsByContext;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public NotificationSubscriptions getNotificationSubscriptions() {
		return notificationSubscriptions;
	}
	
	public void setNotificationSubscriptions(final NotificationSubscriptions notificationSubscriptions) {
		this.notificationSubscriptions = notificationSubscriptions;
	}

	public String getNotificationSubscriptionsBySource() {
		return notificationSubscriptionsBySource;
	}
	
	public void setNotificationSubscriptionsBySource(final String notificationSubscriptionsBySource) {
		this.notificationSubscriptionsBySource = notificationSubscriptionsBySource;
	}

	public String getNotificationSubscriptionsBySourceAndContext() {
		return notificationSubscriptionsBySourceAndContext;
	}
	
	public void setNotificationSubscriptionsBySourceAndContext(final String notificationSubscriptionsBySourceAndContext) {
		this.notificationSubscriptionsBySourceAndContext = notificationSubscriptionsBySourceAndContext;
	}

	public String getNotificationSubscriptionsByContext() {
		return notificationSubscriptionsByContext;
	}
	
	public void setNotificationSubscriptionsByContext(final String notificationSubscriptionsByContext) {
		this.notificationSubscriptionsByContext = notificationSubscriptionsByContext;
	}

	/**
	 * <p>Collection of all notification subscriptions.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class NotificationSubscriptions {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		private NotificationSubscription[] subscriptions;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public NotificationSubscription[] getSubscriptions() {
			return subscriptions;
		}
		
		public void setSubscriptions(final NotificationSubscription[] subscriptions) {
			this.subscriptions = subscriptions;
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
			if (r != null && r instanceof NotificationSubscriptions) {
				NotificationSubscriptions comparer = (NotificationSubscriptions) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSubscriptions().equals(this.getSubscriptions())) {
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
		if (r != null && r instanceof NotificationApiResource) {
			NotificationApiResource comparer = (NotificationApiResource) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getNotificationSubscriptions().equals(this.getNotificationSubscriptions()) && String.valueOf(comparer.getNotificationSubscriptionsBySource()).equals(String.valueOf(this.getNotificationSubscriptionsBySource())) && String.valueOf(comparer.getNotificationSubscriptionsBySourceAndContext()).equals(String.valueOf(this.getNotificationSubscriptionsBySourceAndContext())) && String.valueOf(comparer.getNotificationSubscriptionsByContext()).equals(String.valueOf(this.getNotificationSubscriptionsByContext()))) {
				return true;
			}
		}
		return false;
	}
}
