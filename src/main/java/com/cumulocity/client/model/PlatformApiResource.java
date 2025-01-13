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
public class PlatformApiResource {

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	private AlarmsApiResource alarm;

	private AuditApiResource audit;

	private DeviceControlApiResource deviceControl;

	private EventsApiResource event;

	private IdentityApiResource identity;

	private InventoryApiResource inventory;

	private MeasurementApiResource measurement;

	private TenantApiResource tenant;

	private UserApiResource user;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public AlarmsApiResource getAlarm() {
		return alarm;
	}
	
	public void setAlarm(final AlarmsApiResource alarm) {
		this.alarm = alarm;
	}

	public AuditApiResource getAudit() {
		return audit;
	}
	
	public void setAudit(final AuditApiResource audit) {
		this.audit = audit;
	}

	public DeviceControlApiResource getDeviceControl() {
		return deviceControl;
	}
	
	public void setDeviceControl(final DeviceControlApiResource deviceControl) {
		this.deviceControl = deviceControl;
	}

	public EventsApiResource getEvent() {
		return event;
	}
	
	public void setEvent(final EventsApiResource event) {
		this.event = event;
	}

	public IdentityApiResource getIdentity() {
		return identity;
	}
	
	public void setIdentity(final IdentityApiResource identity) {
		this.identity = identity;
	}

	public InventoryApiResource getInventory() {
		return inventory;
	}
	
	public void setInventory(final InventoryApiResource inventory) {
		this.inventory = inventory;
	}

	public MeasurementApiResource getMeasurement() {
		return measurement;
	}
	
	public void setMeasurement(final MeasurementApiResource measurement) {
		this.measurement = measurement;
	}

	public TenantApiResource getTenant() {
		return tenant;
	}
	
	public void setTenant(final TenantApiResource tenant) {
		this.tenant = tenant;
	}

	public UserApiResource getUser() {
		return user;
	}
	
	public void setUser(final UserApiResource user) {
		this.user = user;
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
		if (r != null && r instanceof PlatformApiResource) {
			PlatformApiResource comparer = (PlatformApiResource) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getAlarm().equals(this.getAlarm()) && comparer.getAudit().equals(this.getAudit()) && comparer.getDeviceControl().equals(this.getDeviceControl()) && comparer.getEvent().equals(this.getEvent()) && comparer.getIdentity().equals(this.getIdentity()) && comparer.getInventory().equals(this.getInventory()) && comparer.getMeasurement().equals(this.getMeasurement()) && comparer.getTenant().equals(this.getTenant()) && comparer.getUser().equals(this.getUser())) {
				return true;
			}
		}
		return false;
	}
}
