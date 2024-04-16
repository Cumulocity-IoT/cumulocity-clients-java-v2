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
@JsonDeserialize(using = Alarm.Deserializer.class)
public class Alarm {

	/**
	 * <p>Number of times that this alarm has been triggered.</p>
	 */
	private int count;

	/**
	 * <p>The date and time when the alarm was created.</p>
	 */
	private String creationTime;

	/**
	 * <p>The time at which the alarm occurred for the first time. Only present when <code>count</code> is greater than 1.</p>
	 */
	private String firstOccurrenceTime;

	/**
	 * <p>Unique identifier of the alarm.</p>
	 */
	private String id;

	/**
	 * <p>The date and time when the alarm was last updated.</p>
	 */
	private String lastUpdated;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The severity of the alarm.</p>
	 */
	private Severity severity;

	/**
	 * <p>The managed object to which the alarm is associated.</p>
	 */
	private Source source;

	/**
	 * <p>The status of the alarm. If not specified, a new alarm will be created as ACTIVE.</p>
	 */
	private Status status;

	/**
	 * <p>Description of the alarm.</p>
	 */
	private String text;

	/**
	 * <p>The date and time when the alarm is triggered.</p>
	 */
	private String time;

	/**
	 * <p>Identifies the type of this alarm.</p>
	 */
	private String type;

	/**
	 * <p>It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, <code>"property1": {}</code>, <code>"property2": "value"</code>. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.</p>
	 * <p>Review <a href="https://cumulocity.com/docs/concepts/domain-model/#naming-conventions-of-fragments">Getting started > Technical concepts > Cumulocity IoT's domain model > Inventory > Fragments > Naming conventions of fragments</a> in the Cumulocity IoT user documentation as there are characters that can not be used when naming custom fragments.</p>
	 */
	private Map<String, Object> customFragments;

	public int getCount() {
		return count;
	}
	
	public void setCount(final int count) {
		this.count = count;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public String getFirstOccurrenceTime() {
		return firstOccurrenceTime;
	}
	
	public void setFirstOccurrenceTime(final String firstOccurrenceTime) {
		this.firstOccurrenceTime = firstOccurrenceTime;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(final String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Severity getSeverity() {
		return severity;
	}
	
	public void setSeverity(final Severity severity) {
		this.severity = severity;
	}

	public Source getSource() {
		return source;
	}
	
	public void setSource(final Source source) {
		this.source = source;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}
	
	public void setText(final String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(final String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	@JsonAnyGetter
	public Map<String, Object> getCustomFragments() {
		return customFragments;
	}
	
	public void setCustomFragments(final Map<String, Object> customFragments) {
		this.customFragments = customFragments;
	}

	
	/**
	 * <p>The severity of the alarm.</p>
	 */
	public enum Severity {
		@JsonProperty("CRITICAL")
		CRITICAL("CRITICAL"),
		@JsonProperty("MAJOR")
		MAJOR("MAJOR"),
		@JsonProperty("MINOR")
		MINOR("MINOR"),
		@JsonProperty("WARNING")
		WARNING("WARNING");
	
		private String value;
	
		Severity(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>The status of the alarm. If not specified, a new alarm will be created as ACTIVE.</p>
	 */
	public enum Status {
		@JsonProperty("ACTIVE")
		ACTIVE("ACTIVE"),
		@JsonProperty("ACKNOWLEDGED")
		ACKNOWLEDGED("ACKNOWLEDGED"),
		@JsonProperty("CLEARED")
		CLEARED("CLEARED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}


	/**
	 * <p>The managed object to which the alarm is associated.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Source {
	
		/**
		 * <p>Unique identifier of the object.</p>
		 */
		private String id;
	
		/**
		 * <p>Human-readable name that is used for representing the object in user interfaces.</p>
		 */
		private String name;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
		}
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof Source) {
				Source comparer = (Source) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}


	public static class Deserializer extends JsonDeserializer<Alarm> {

		@Override
		public Alarm deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			final Alarm alarm = new Alarm();
			final Map<String, Object> additionalObjects = new HashMap<>();
			alarm.setCustomFragments(additionalObjects);
			final JsonNode jsonNode = p.readValueAsTree();
			jsonNode.fields().forEachRemaining(entry -> {
				try {
					final Field field = alarm.getClass().getDeclaredField(entry.getKey());
					field.setAccessible(true);
					final ObjectMapper objectMapper = new ObjectMapper();
					final Object value = objectMapper.readValue(entry.getValue().traverse(), field.getType());
					field.set(alarm, value);
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
			return alarm;
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
		if (r != null && r instanceof Alarm) {
			Alarm comparer = (Alarm) r;
			if (Integer.valueOf(comparer.getCount()).equals(Integer.valueOf(this.getCount())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getFirstOccurrenceTime()).equals(String.valueOf(this.getFirstOccurrenceTime())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastUpdated()).equals(String.valueOf(this.getLastUpdated())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSeverity().equals(this.getSeverity()) && comparer.getSource().equals(this.getSource()) && comparer.getStatus().equals(this.getStatus()) && String.valueOf(comparer.getText()).equals(String.valueOf(this.getText())) && String.valueOf(comparer.getTime()).equals(String.valueOf(this.getTime())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
