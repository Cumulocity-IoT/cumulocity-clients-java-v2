// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonAnyGetter;			
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using = Operation.Deserializer.class)
public class Operation {

	/**
	 * <p>Reference to a bulk operation ID if this operation was scheduled from a bulk operation.</p>
	 */
	private String bulkOperationId;

	/**
	 * <p>Date and time when the operation was created in the database.</p>
	 */
	private String creationTime;

	/**
	 * <p>Identifier of the target device where the operation should be performed.</p>
	 */
	private String deviceId;

	private ExternalIds deviceExternalIDs;

	/**
	 * <p>Reason for the failure.</p>
	 */
	private String failureReason;

	/**
	 * <p>Unique identifier of this operation.</p>
	 */
	private String id;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The status of the operation.</p>
	 */
	private Status status;

	/**
	 * <p>It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, <code>"property1": {}</code>, <code>"property2": "value"</code>. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.</p>
	 * <p>Review <a href="https://cumulocity.com/docs/concepts/domain-model/#naming-conventions-of-fragments">Getting started > Technical concepts > Cumulocity IoT's domain model > Inventory > Fragments > Naming conventions of fragments</a> in the Cumulocity IoT user documentation as there are characters that can not be used when naming custom fragments.</p>
	 */
	private Map<String, Object> customFragments;

	public String getBulkOperationId() {
		return bulkOperationId;
	}
	
	public void setBulkOperationId(final String bulkOperationId) {
		this.bulkOperationId = bulkOperationId;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public ExternalIds getDeviceExternalIDs() {
		return deviceExternalIDs;
	}
	
	public void setDeviceExternalIDs(final ExternalIds deviceExternalIDs) {
		this.deviceExternalIDs = deviceExternalIDs;
	}

	public String getFailureReason() {
		return failureReason;
	}
	
	public void setFailureReason(final String failureReason) {
		this.failureReason = failureReason;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	@JsonAnyGetter
	public Map<String, Object> getCustomFragments() {
		return customFragments;
	}
	
	public void setCustomFragments(final Map<String, Object> customFragments) {
		this.customFragments = customFragments;
	}

	
	/**
	 * <p>The status of the operation.</p>
	 */
	public enum Status {
		@JsonProperty("SUCCESSFUL")
		SUCCESSFUL("SUCCESSFUL"),
		@JsonProperty("FAILED")
		FAILED("FAILED"),
		@JsonProperty("EXECUTING")
		EXECUTING("EXECUTING"),
		@JsonProperty("PENDING")
		PENDING("PENDING");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}


	public static class Deserializer extends JsonDeserializer<Operation> {

		@Override
		public Operation deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			final Operation operation = new Operation();
			final Map<String, Object> additionalObjects = new HashMap<>();
			operation.setCustomFragments(additionalObjects);
			final JsonNode jsonNode = p.readValueAsTree();
			jsonNode.fields().forEachRemaining(entry -> {
				try {
					final Field field = operation.getClass().getDeclaredField(entry.getKey());
					field.setAccessible(true);
					final ObjectMapper objectMapper = new ObjectMapper();
					final Object value = objectMapper.readValue(entry.getValue().traverse(), field.getType());
					field.set(operation, value);
				} catch (final NoSuchFieldException nse) {
					if (Serialization.additionalPropertyClasses.containsKey(entry.getKey())) {
						final ObjectMapper objectMapper = new ObjectMapper();
						try {
							final Object value = objectMapper.readValue(entry.getValue().traverse(), Serialization.additionalPropertyClasses.get(entry.getKey()));
							additionalObjects.put(entry.getKey(), value);
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}
				} catch (final IOException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			});
			return operation;
		}
	}

	public static class Serialization {

		private static Map<String, Class<?>> additionalPropertyClasses = new HashMap<String, Class<?>>();

		public static void registerAdditionalProperty(final String typeName, final Class<?> type) {
			additionalPropertyClasses.put(typeName, type);
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
		if (r != null && r instanceof Operation) {
			Operation comparer = (Operation) r;
			if (String.valueOf(comparer.getBulkOperationId()).equals(String.valueOf(this.getBulkOperationId())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getDeviceId()).equals(String.valueOf(this.getDeviceId())) && comparer.getDeviceExternalIDs().equals(this.getDeviceExternalIDs()) && String.valueOf(comparer.getFailureReason()).equals(String.valueOf(this.getFailureReason())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getStatus().equals(this.getStatus()) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
