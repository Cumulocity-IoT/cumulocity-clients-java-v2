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
@JsonDeserialize(using = ManagedObject.Deserializer.class)
public class ManagedObject {

	/**
	 * <p>The date and time when the object was created.</p>
	 */
	private String creationTime;

	/**
	 * <p>Unique identifier of the object.</p>
	 */
	private String id;

	/**
	 * <p>The date and time when the object was updated for the last time.</p>
	 */
	private String lastUpdated;

	/**
	 * <p>Human-readable name that is used for representing the object in user interfaces.</p>
	 */
	private String name;

	/**
	 * <p>Username of the device's owner.</p>
	 */
	private String owner;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The fragment type can be interpreted as <em>device class</em>, this means, devices with the same type can receive the same types of configuration, software, firmware and operations. The type value is indexed and is therefore used for queries.</p>
	 */
	private String type;

	/**
	 * <p>A collection of references to child additions.</p>
	 */
	private ObjectChildAdditions childAdditions;

	/**
	 * <p>A collection of references to child assets.</p>
	 */
	private ObjectChildAssets childAssets;

	/**
	 * <p>A collection of references to child devices.</p>
	 */
	private ObjectChildDevices childDevices;

	/**
	 * <p>A collection of references to addition parent objects.</p>
	 */
	private ObjectAdditionParents additionParents;

	/**
	 * <p>A collection of references to asset parent objects.</p>
	 */
	private ObjectAssetParents assetParents;

	/**
	 * <p>A collection of references to device parent objects.</p>
	 */
	private ObjectDeviceParents deviceParents;

	/**
	 * <p>A fragment which identifies this managed object as a device.</p>
	 */
	@JsonProperty(value = "c8y_IsDevice")
	private C8yIsDevice c8yIsDevice;

	/**
	 * <p>The read only fragment which contains the latest measurements reported by the device.The returned optionally only if the query parameter <code>withLatestValues=true</code> is used.</p>
	 * <blockquote>
	 * <p><strong>⚠️ Feature Preview:</strong> The feature is part of the Latest Measurement feature which is still under public feature preview.</p>
	 * </blockquote>
	 */
	@JsonProperty(value = "c8y_LatestMeasurements")
	private C8yLatestMeasurements c8yLatestMeasurements;

	/**
	 * <p>A fragment which identifies this managed object as a device group.</p>
	 */
	@JsonProperty(value = "c8y_IsDeviceGroup")
	private C8yIsDeviceGroup c8yIsDeviceGroup;

	/**
	 * <p>This fragment must be added in order to publish sample commands for a subset of devices sharing the same device type. If the fragment is present, the list of sample commands for a device type will be extended with the sample commands for the <code>c8y_DeviceTypes</code>. New sample commands created from the user interface will be created in the context of the <code>c8y_DeviceTypes</code>.</p>
	 */
	@JsonProperty(value = "c8y_DeviceTypes")
	private String[] c8yDeviceTypes;

	/**
	 * <p>Lists the operations that are available for a particular device, so that applications can trigger the operations.</p>
	 */
	@JsonProperty(value = "c8y_SupportedOperations")
	private String[] c8ySupportedOperations;

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

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(final String owner) {
		this.owner = owner;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	public ObjectChildAdditions getChildAdditions() {
		return childAdditions;
	}
	
	public void setChildAdditions(final ObjectChildAdditions childAdditions) {
		this.childAdditions = childAdditions;
	}

	public ObjectChildAssets getChildAssets() {
		return childAssets;
	}
	
	public void setChildAssets(final ObjectChildAssets childAssets) {
		this.childAssets = childAssets;
	}

	public ObjectChildDevices getChildDevices() {
		return childDevices;
	}
	
	public void setChildDevices(final ObjectChildDevices childDevices) {
		this.childDevices = childDevices;
	}

	public ObjectAdditionParents getAdditionParents() {
		return additionParents;
	}
	
	public void setAdditionParents(final ObjectAdditionParents additionParents) {
		this.additionParents = additionParents;
	}

	public ObjectAssetParents getAssetParents() {
		return assetParents;
	}
	
	public void setAssetParents(final ObjectAssetParents assetParents) {
		this.assetParents = assetParents;
	}

	public ObjectDeviceParents getDeviceParents() {
		return deviceParents;
	}
	
	public void setDeviceParents(final ObjectDeviceParents deviceParents) {
		this.deviceParents = deviceParents;
	}

	public C8yIsDevice getC8yIsDevice() {
		return c8yIsDevice;
	}
	
	public void setC8yIsDevice(final C8yIsDevice c8yIsDevice) {
		this.c8yIsDevice = c8yIsDevice;
	}

	public C8yLatestMeasurements getC8yLatestMeasurements() {
		return c8yLatestMeasurements;
	}
	
	public void setC8yLatestMeasurements(final C8yLatestMeasurements c8yLatestMeasurements) {
		this.c8yLatestMeasurements = c8yLatestMeasurements;
	}

	public C8yIsDeviceGroup getC8yIsDeviceGroup() {
		return c8yIsDeviceGroup;
	}
	
	public void setC8yIsDeviceGroup(final C8yIsDeviceGroup c8yIsDeviceGroup) {
		this.c8yIsDeviceGroup = c8yIsDeviceGroup;
	}

	public String[] getC8yDeviceTypes() {
		return c8yDeviceTypes;
	}
	
	public void setC8yDeviceTypes(final String[] c8yDeviceTypes) {
		this.c8yDeviceTypes = c8yDeviceTypes;
	}

	public String[] getC8ySupportedOperations() {
		return c8ySupportedOperations;
	}
	
	public void setC8ySupportedOperations(final String[] c8ySupportedOperations) {
		this.c8ySupportedOperations = c8ySupportedOperations;
	}

	@JsonAnyGetter
	public Map<String, Object> getCustomFragments() {
		return customFragments;
	}
	
	public void setCustomFragments(final Map<String, Object> customFragments) {
		this.customFragments = customFragments;
	}

	/**
	 * <p>A fragment which identifies this managed object as a device.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yIsDevice {
	
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
	 * <p>A fragment which identifies this managed object as a device group.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yIsDeviceGroup {
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
	}

	public static class Deserializer extends JsonDeserializer<ManagedObject> {

		@Override
		public ManagedObject deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			final ManagedObject managedObject = new ManagedObject();
			final Map<String, Object> additionalObjects = new HashMap<>();
			managedObject.setCustomFragments(additionalObjects);
			final JsonNode jsonNode = p.readValueAsTree();
			jsonNode.fields().forEachRemaining(entry -> {
				try {
					final Field field = managedObject.getClass().getDeclaredField(entry.getKey());
					field.setAccessible(true);
					final ObjectMapper objectMapper = new ObjectMapper();
					final Object value = objectMapper.readValue(entry.getValue().traverse(), field.getType());
					field.set(managedObject, value);
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
			return managedObject;
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
		if (r != null && r instanceof ManagedObject) {
			ManagedObject comparer = (ManagedObject) r;
			if (String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastUpdated()).equals(String.valueOf(this.getLastUpdated())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getOwner()).equals(String.valueOf(this.getOwner())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType())) && comparer.getChildAdditions().equals(this.getChildAdditions()) && comparer.getChildAssets().equals(this.getChildAssets()) && comparer.getChildDevices().equals(this.getChildDevices()) && comparer.getAdditionParents().equals(this.getAdditionParents()) && comparer.getAssetParents().equals(this.getAssetParents()) && comparer.getDeviceParents().equals(this.getDeviceParents()) && comparer.getC8yIsDevice().equals(this.getC8yIsDevice()) && comparer.getC8yLatestMeasurements().equals(this.getC8yLatestMeasurements()) && comparer.getC8yIsDeviceGroup().equals(this.getC8yIsDeviceGroup()) && comparer.getC8yDeviceTypes().equals(this.getC8yDeviceTypes()) && comparer.getC8ySupportedOperations().equals(this.getC8ySupportedOperations()) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
