// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
public class NotificationTokenClaims {

	/**
	 * <p>The token expiration duration.</p>
	 */
	private int expiresInMinutes;

	/**
	 * <p>The subscriber name which the client wishes to be identified with.</p>
	 */
	private String subscriber;

	/**
	 * <p>The subscription name. This value must match the same that was used when the subscription was created.</p>
	 */
	private String subscription;

	/**
	 * <p>The subscription type. Currently the only supported type is <code>notification</code> .Other types may be added in future.</p>
	 */
	private Type type;

	/**
	 * <p>If <code>true</code>, the token will be securely signed by the Cumulocity IoT platform.</p>
	 */
	private boolean signed;

	/**
	 * <p>If <code>true</code>, indicates that the token is used to create a shared consumer on the subscription.</p>
	 */
	private boolean shared;

	/**
	 * <p>If <code>true</code>, indicates that the created token refers to the non-persistent variant of the named subscription.</p>
	 */
	private boolean nonPersistent;

	public NotificationTokenClaims() {
	}

	public NotificationTokenClaims(final String subscriber, final String subscription) {
		this.subscriber = subscriber;
		this.subscription = subscription;
	}

	public int getExpiresInMinutes() {
		return expiresInMinutes;
	}
	
	public void setExpiresInMinutes(final int expiresInMinutes) {
		this.expiresInMinutes = expiresInMinutes;
	}

	public String getSubscriber() {
		return subscriber;
	}
	
	public void setSubscriber(final String subscriber) {
		this.subscriber = subscriber;
	}

	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(final String subscription) {
		this.subscription = subscription;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	public boolean getSigned() {
		return signed;
	}
	
	public void setSigned(final boolean signed) {
		this.signed = signed;
	}

	public boolean getShared() {
		return shared;
	}
	
	public void setShared(final boolean shared) {
		this.shared = shared;
	}

	public boolean getNonPersistent() {
		return nonPersistent;
	}
	
	public void setNonPersistent(final boolean nonPersistent) {
		this.nonPersistent = nonPersistent;
	}

	
	/**
	 * <p>The subscription type. Currently the only supported type is <code>notification</code> .Other types may be added in future.</p>
	 */
	public enum Type {
		@JsonProperty("notification")
		NOTIFICATION("notification");
	
		private String value;
	
		Type(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
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
		if (r != null && r instanceof NotificationTokenClaims) {
			NotificationTokenClaims comparer = (NotificationTokenClaims) r;
			if (Integer.valueOf(comparer.getExpiresInMinutes()).equals(Integer.valueOf(this.getExpiresInMinutes())) && String.valueOf(comparer.getSubscriber()).equals(String.valueOf(this.getSubscriber())) && String.valueOf(comparer.getSubscription()).equals(String.valueOf(this.getSubscription())) && comparer.getType().equals(this.getType()) && Boolean.valueOf(comparer.getSigned()).equals(Boolean.valueOf(this.getSigned())) && Boolean.valueOf(comparer.getShared()).equals(Boolean.valueOf(this.getShared())) && Boolean.valueOf(comparer.getNonPersistent()).equals(Boolean.valueOf(this.getNonPersistent()))) {
				return true;
			}
		}
		return false;
	}
}
