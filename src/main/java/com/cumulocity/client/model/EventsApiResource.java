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
public class EventsApiResource {

	/**
	 * <p>Collection of all events</p>
	 */
	private Events events;

	/**
	 * <p>Read-only collection of all events for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.</p>
	 */
	private String eventsForSource;

	/**
	 * <p>Read-only collection of all events of a particular type and a specific source object.</p>
	 */
	private String eventsForSourceAndType;

	/**
	 * <p>Read-only collection of all events of a particular type.</p>
	 */
	private String eventsForType;

	/**
	 * <p>Read-only collection of all events containing a particular fragment type.</p>
	 */
	private String eventsForFragmentType;

	/**
	 * <p>Read-only collection of all events for a particular time range.</p>
	 */
	private String eventsForTime;

	/**
	 * <p>Read-only collection of all events for a specific source object in a particular time range.</p>
	 */
	private String eventsForSourceAndTime;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	public Events getEvents() {
		return events;
	}
	
	public void setEvents(final Events events) {
		this.events = events;
	}

	public String getEventsForSource() {
		return eventsForSource;
	}
	
	public void setEventsForSource(final String eventsForSource) {
		this.eventsForSource = eventsForSource;
	}

	public String getEventsForSourceAndType() {
		return eventsForSourceAndType;
	}
	
	public void setEventsForSourceAndType(final String eventsForSourceAndType) {
		this.eventsForSourceAndType = eventsForSourceAndType;
	}

	public String getEventsForType() {
		return eventsForType;
	}
	
	public void setEventsForType(final String eventsForType) {
		this.eventsForType = eventsForType;
	}

	public String getEventsForFragmentType() {
		return eventsForFragmentType;
	}
	
	public void setEventsForFragmentType(final String eventsForFragmentType) {
		this.eventsForFragmentType = eventsForFragmentType;
	}

	public String getEventsForTime() {
		return eventsForTime;
	}
	
	public void setEventsForTime(final String eventsForTime) {
		this.eventsForTime = eventsForTime;
	}

	public String getEventsForSourceAndTime() {
		return eventsForSourceAndTime;
	}
	
	public void setEventsForSourceAndTime(final String eventsForSourceAndTime) {
		this.eventsForSourceAndTime = eventsForSourceAndTime;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * <p>Collection of all events</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Events {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		private Event[] events;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public Event[] getEvents() {
			return events;
		}
		
		public void setEvents(final Event[] events) {
			this.events = events;
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
			if (r != null && r instanceof Events) {
				Events comparer = (Events) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getEvents().equals(this.getEvents())) {
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
		if (r != null && r instanceof EventsApiResource) {
			EventsApiResource comparer = (EventsApiResource) r;
			if (comparer.getEvents().equals(this.getEvents()) && String.valueOf(comparer.getEventsForSource()).equals(String.valueOf(this.getEventsForSource())) && String.valueOf(comparer.getEventsForSourceAndType()).equals(String.valueOf(this.getEventsForSourceAndType())) && String.valueOf(comparer.getEventsForType()).equals(String.valueOf(this.getEventsForType())) && String.valueOf(comparer.getEventsForFragmentType()).equals(String.valueOf(this.getEventsForFragmentType())) && String.valueOf(comparer.getEventsForTime()).equals(String.valueOf(this.getEventsForTime())) && String.valueOf(comparer.getEventsForSourceAndTime()).equals(String.valueOf(this.getEventsForSourceAndTime())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
