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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using = Event.Deserializer.class)
public class Event {

	/**
	 * <p>The date and time when the event was created.</p>
	 */
	private String creationTime;

	/**
	 * <p>The date and time when the event was last updated.</p>
	 */
	private String lastUpdated;

	/**
	 * <p>Unique identifier of the event.</p>
	 */
	private String id;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The managed object to which the event is associated.</p>
	 */
	private Source source;

	/**
	 * <p>Description of the event.</p>
	 */
	private String text;

	/**
	 * <p>The date and time when the event is updated.</p>
	 */
	private String time;

	/**
	 * <p>Identifies the type of this event.</p>
	 */
	private String type;

	/**
	 * <p>It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, <code>"property1": {}</code>, <code>"property2": "value"</code>. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.</p>
	 * <p>Review <a href="https://cumulocity.com/docs/concepts/domain-model/#naming-conventions-of-fragments">Getting started > Technical concepts > Cumulocity IoT's domain model > Inventory > Fragments > Naming conventions of fragments</a> in the Cumulocity IoT user documentation as there are characters that can not be used when naming custom fragments.</p>
	 */
	private Map<String, Object> customFragments;

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(final String lastUpdated) {
		this.lastUpdated = lastUpdated;
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

	public Source getSource() {
		return source;
	}
	
	public void setSource(final Source source) {
		this.source = source;
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
	 * <p>The managed object to which the event is associated.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Source {
	
		/**
		 * <p>Unique identifier of the object.</p>
		 */
		private String id;
	
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
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}

	public static class Deserializer extends JsonDeserializer<Event> {

		@Override
		public Event deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			final Event event = new Event();
			final Map<String, Object> additionalObjects = new HashMap<>();
			event.setCustomFragments(additionalObjects);
			final JsonNode jsonNode = p.readValueAsTree();
			jsonNode.fields().forEachRemaining(entry -> {
				try {
					final Field field = event.getClass().getDeclaredField(entry.getKey());
					field.setAccessible(true);
					final ObjectMapper objectMapper = new ObjectMapper();
					final Object value = objectMapper.readValue(entry.getValue().traverse(), field.getType());
					field.set(event, value);
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
			return event;
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
		if (r != null && r instanceof Event) {
			Event comparer = (Event) r;
			if (String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getLastUpdated()).equals(String.valueOf(this.getLastUpdated())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSource().equals(this.getSource()) && String.valueOf(comparer.getText()).equals(String.valueOf(this.getText())) && String.valueOf(comparer.getTime()).equals(String.valueOf(this.getTime())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
