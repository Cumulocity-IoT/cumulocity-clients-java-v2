// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The manifest of the microservice application.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MicroserviceApplicationManifest {

	/**
	 * <p>Document type format discriminator, for future changes in format.</p>
	 */
	private String apiVersion;

	/**
	 * <p>The billing mode of the application.</p>
	 * <p>In case of RESOURCES, the number of resources used is exposed for billing calculation per usage.In case of SUBSCRIPTION, all resources usage is counted for the microservice owner and the subtenant is charged for subscription.</p>
	 */
	private BillingMode billingMode;

	/**
	 * <p>The context path in the URL makes the application accessible.</p>
	 */
	private String contextPath;

	/**
	 * <p>A list of URL extensions for this microservice application.</p>
	 */
	private Extensions[] extensions;

	/**
	 * <p>Deployment isolation.In case of PER_TENANT, there is a separate instance for each tenant.Otherwise, there is one single instance for all subscribed tenants.This will affect billing.</p>
	 */
	private Isolation isolation;

	private ApplicationManifestProbe livenessProbe;

	/**
	 * <p>Application provider information.Simple name allowed for predefined providers, for example, c8y.Detailed object for external provider.</p>
	 */
	private Provider provider;

	private ApplicationManifestProbe readinessProbe;

	/**
	 * <p>The minimum required resources for the microservice application.</p>
	 */
	private RequestResources requestResources;

	/**
	 * <p>The recommended resources for this microservice application.</p>
	 */
	private Resources resources;

	/**
	 * <p>Roles provided by the microservice.</p>
	 */
	private String[] roles;

	/**
	 * <p>List of permissions required by a microservice to work.</p>
	 */
	private String[] requiredRoles;

	/**
	 * <p>Allows to configure a microservice auto scaling policy.If the microservice uses a lot of CPU resources, a second instance will be created automatically when this is set to <code>AUTO</code>.The default is <code>NONE</code>, meaning auto scaling will not happen.</p>
	 */
	private Scale scale;

	/**
	 * <p>A list of settings objects for this microservice application.</p>
	 */
	private ApplicationSettings[] settings;

	/**
	 * <p>Allows to specify a custom category for microservice settings.By default, <code>contextPath</code> is used.</p>
	 */
	private String settingsCategory;

	/**
	 * <p>Application version.Must be a correct <a href="https://semver.org/">SemVer</a> value but the "+" sign is disallowed.</p>
	 */
	private String version;

	public String getApiVersion() {
		return apiVersion;
	}
	
	public void setApiVersion(final String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public BillingMode getBillingMode() {
		return billingMode;
	}
	
	public void setBillingMode(final BillingMode billingMode) {
		this.billingMode = billingMode;
	}

	public String getContextPath() {
		return contextPath;
	}
	
	public void setContextPath(final String contextPath) {
		this.contextPath = contextPath;
	}

	public Extensions[] getExtensions() {
		return extensions;
	}
	
	public void setExtensions(final Extensions[] extensions) {
		this.extensions = extensions;
	}

	public Isolation getIsolation() {
		return isolation;
	}
	
	public void setIsolation(final Isolation isolation) {
		this.isolation = isolation;
	}

	public ApplicationManifestProbe getLivenessProbe() {
		return livenessProbe;
	}
	
	public void setLivenessProbe(final ApplicationManifestProbe livenessProbe) {
		this.livenessProbe = livenessProbe;
	}

	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(final Provider provider) {
		this.provider = provider;
	}

	public ApplicationManifestProbe getReadinessProbe() {
		return readinessProbe;
	}
	
	public void setReadinessProbe(final ApplicationManifestProbe readinessProbe) {
		this.readinessProbe = readinessProbe;
	}

	public RequestResources getRequestResources() {
		return requestResources;
	}
	
	public void setRequestResources(final RequestResources requestResources) {
		this.requestResources = requestResources;
	}

	public Resources getResources() {
		return resources;
	}
	
	public void setResources(final Resources resources) {
		this.resources = resources;
	}

	public String[] getRoles() {
		return roles;
	}
	
	public void setRoles(final String[] roles) {
		this.roles = roles;
	}

	public String[] getRequiredRoles() {
		return requiredRoles;
	}
	
	public void setRequiredRoles(final String[] requiredRoles) {
		this.requiredRoles = requiredRoles;
	}

	public Scale getScale() {
		return scale;
	}
	
	public void setScale(final Scale scale) {
		this.scale = scale;
	}

	public ApplicationSettings[] getSettings() {
		return settings;
	}
	
	public void setSettings(final ApplicationSettings[] settings) {
		this.settings = settings;
	}

	public String getSettingsCategory() {
		return settingsCategory;
	}
	
	public void setSettingsCategory(final String settingsCategory) {
		this.settingsCategory = settingsCategory;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	
	/**
	 * <p>The billing mode of the application.</p>
	 * <p>In case of RESOURCES, the number of resources used is exposed for billing calculation per usage.In case of SUBSCRIPTION, all resources usage is counted for the microservice owner and the subtenant is charged for subscription.</p>
	 */
	public enum BillingMode {
		@JsonProperty("RESOURCES")
		RESOURCES("RESOURCES"),
		@JsonProperty("SUBSCRIPTION")
		SUBSCRIPTION("SUBSCRIPTION");
	
		private String value;
	
		BillingMode(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>Deployment isolation.In case of PER_TENANT, there is a separate instance for each tenant.Otherwise, there is one single instance for all subscribed tenants.This will affect billing.</p>
	 */
	public enum Isolation {
		@JsonProperty("MULTI_TENANT")
		MULTITENANT("MULTI_TENANT"),
		@JsonProperty("PER_TENANT")
		PERTENANT("PER_TENANT");
	
		private String value;
	
		Isolation(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>Allows to configure a microservice auto scaling policy.If the microservice uses a lot of CPU resources, a second instance will be created automatically when this is set to <code>AUTO</code>.The default is <code>NONE</code>, meaning auto scaling will not happen.</p>
	 */
	public enum Scale {
		@JsonProperty("NONE")
		NONE("NONE"),
		@JsonProperty("AUTO")
		AUTO("AUTO");
	
		private String value;
	
		Scale(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}


	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Extensions {
	
		/**
		 * <p>The relative path in Cumulocity IoT for this microservice application.</p>
		 */
		private String path;
	
		/**
		 * <p>The type of this extension.</p>
		 */
		private String type;
	
		public String getPath() {
			return path;
		}
		
		public void setPath(final String path) {
			this.path = path;
		}
	
		public String getType() {
			return type;
		}
		
		public void setType(final String type) {
			this.type = type;
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
			if (r != null && r instanceof Extensions) {
				Extensions comparer = (Extensions) r;
				if (String.valueOf(comparer.getPath()).equals(String.valueOf(this.getPath())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * <p>Application provider information.Simple name allowed for predefined providers, for example, c8y.Detailed object for external provider.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Provider {
	
		/**
		 * <p>The name of the application provider.</p>
		 */
		private String name;
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
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
			if (r != null && r instanceof Provider) {
				Provider comparer = (Provider) r;
				if (String.valueOf(comparer.getName()).equals(String.valueOf(this.getName()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>The minimum required resources for the microservice application.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class RequestResources {
	
		/**
		 * <p>The required CPU resource for this microservice application.</p>
		 */
		private String cpu;
	
		/**
		 * <p>The required memory resource for this microservice application.</p>
		 */
		private String memory;
	
		public String getCpu() {
			return cpu;
		}
		
		public void setCpu(final String cpu) {
			this.cpu = cpu;
		}
	
		public String getMemory() {
			return memory;
		}
		
		public void setMemory(final String memory) {
			this.memory = memory;
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
			if (r != null && r instanceof RequestResources) {
				RequestResources comparer = (RequestResources) r;
				if (String.valueOf(comparer.getCpu()).equals(String.valueOf(this.getCpu())) && String.valueOf(comparer.getMemory()).equals(String.valueOf(this.getMemory()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>The recommended resources for this microservice application.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Resources {
	
		/**
		 * <p>The required CPU resource for this microservice application.</p>
		 */
		private String cpu;
	
		/**
		 * <p>The required memory resource for this microservice application.</p>
		 */
		private String memory;
	
		public String getCpu() {
			return cpu;
		}
		
		public void setCpu(final String cpu) {
			this.cpu = cpu;
		}
	
		public String getMemory() {
			return memory;
		}
		
		public void setMemory(final String memory) {
			this.memory = memory;
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
			if (r != null && r instanceof Resources) {
				Resources comparer = (Resources) r;
				if (String.valueOf(comparer.getCpu()).equals(String.valueOf(this.getCpu())) && String.valueOf(comparer.getMemory()).equals(String.valueOf(this.getMemory()))) {
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
		if (r != null && r instanceof MicroserviceApplicationManifest) {
			MicroserviceApplicationManifest comparer = (MicroserviceApplicationManifest) r;
			if (String.valueOf(comparer.getApiVersion()).equals(String.valueOf(this.getApiVersion())) && comparer.getBillingMode().equals(this.getBillingMode()) && String.valueOf(comparer.getContextPath()).equals(String.valueOf(this.getContextPath())) && comparer.getExtensions().equals(this.getExtensions()) && comparer.getIsolation().equals(this.getIsolation()) && comparer.getLivenessProbe().equals(this.getLivenessProbe()) && comparer.getProvider().equals(this.getProvider()) && comparer.getReadinessProbe().equals(this.getReadinessProbe()) && comparer.getRequestResources().equals(this.getRequestResources()) && comparer.getResources().equals(this.getResources()) && comparer.getRoles().equals(this.getRoles()) && comparer.getRequiredRoles().equals(this.getRequiredRoles()) && comparer.getScale().equals(this.getScale()) && comparer.getSettings().equals(this.getSettings()) && String.valueOf(comparer.getSettingsCategory()).equals(String.valueOf(this.getSettingsCategory())) && String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion()))) {
				return true;
			}
		}
		return false;
	}
}
