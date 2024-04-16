// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Parameters determining the authentication process.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AuthConfig {

	/**
	 * <p>SSO specific. Describes the fields in the access token from the external server containing user information.</p>
	 */
	private AccessTokenToUserDataMapping accessTokenToUserDataMapping;

	/**
	 * <p>SSO specific. Token audience.</p>
	 */
	private String audience;

	private RequestRepresentation authorizationRequest;

	/**
	 * <p>For basic authentication case only.</p>
	 */
	private BasicAuthenticationRestrictions authenticationRestrictions;

	/**
	 * <p>SSO specific. Information for the UI about the name displayed on the external server login button.</p>
	 */
	private String buttonName;

	/**
	 * <p>SSO specific. The identifier of the Cumulocity IoT tenant on the external authorization server.</p>
	 */
	private String clientId;

	/**
	 * <p>The authentication configuration grant type identifier.</p>
	 */
	private GrantType grantType;

	/**
	 * <p>Unique identifier of this login option.</p>
	 */
	private String id;

	/**
	 * <p>SSO specific. External token issuer.</p>
	 */
	private String issuer;

	private RequestRepresentation logoutRequest;

	/**
	 * <p>Indicates whether the configuration is only accessible to the management tenant.</p>
	 */
	private boolean onlyManagementTenantAccess;

	/**
	 * <p>SSO specific. Describes the process of internal user creation during login with the external authorization server.</p>
	 */
	private OnNewUser onNewUser;

	/**
	 * <p>The name of the authentication provider.</p>
	 */
	private String providerName;

	/**
	 * <p>SSO specific. URL used for redirecting to the Cumulocity IoT platform.</p>
	 */
	private String redirectToPlatform;

	private RequestRepresentation refreshRequest;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The session configuration properties are only available for OAuth internal. See <a href="https://cumulocity.com/guides/10.11.0/users-guide/administration/#oauth-internal">Administration > Changing settings > OAuth internal</a> in the <em>10.11.0 user guide</em> for more details.</p>
	 */
	private OAuthSessionConfiguration sessionConfiguration;

	/**
	 * <p>SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.</p>
	 */
	private SignatureVerificationConfig signatureVerificationConfig;

	/**
	 * <p>SSO specific. Template name used by the UI.</p>
	 */
	private String template;

	private RequestRepresentation tokenRequest;

	/**
	 * <p>The authentication configuration type. Note that the value is case insensitive.</p>
	 */
	private Type type;

	/**
	 * <p>SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.</p>
	 */
	private UserIdConfig userIdConfig;

	/**
	 * <p>Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.</p>
	 */
	private UserManagementSource userManagementSource;

	/**
	 * <p>Information for the UI if the respective authentication form should be visible for the user.</p>
	 */
	private boolean visibleOnLoginPage;

	/**
	 * <p>A configuration for authentication with an access token from the authorization server.</p>
	 */
	private ExternalTokenConfig externalTokenConfig;

	public AuthConfig() {
	}

	public AuthConfig(final String providerName, final Type type) {
		this.providerName = providerName;
		this.type = type;
	}

	public AccessTokenToUserDataMapping getAccessTokenToUserDataMapping() {
		return accessTokenToUserDataMapping;
	}
	
	public void setAccessTokenToUserDataMapping(final AccessTokenToUserDataMapping accessTokenToUserDataMapping) {
		this.accessTokenToUserDataMapping = accessTokenToUserDataMapping;
	}

	public String getAudience() {
		return audience;
	}
	
	public void setAudience(final String audience) {
		this.audience = audience;
	}

	public RequestRepresentation getAuthorizationRequest() {
		return authorizationRequest;
	}
	
	public void setAuthorizationRequest(final RequestRepresentation authorizationRequest) {
		this.authorizationRequest = authorizationRequest;
	}

	public BasicAuthenticationRestrictions getAuthenticationRestrictions() {
		return authenticationRestrictions;
	}
	
	public void setAuthenticationRestrictions(final BasicAuthenticationRestrictions authenticationRestrictions) {
		this.authenticationRestrictions = authenticationRestrictions;
	}

	public String getButtonName() {
		return buttonName;
	}
	
	public void setButtonName(final String buttonName) {
		this.buttonName = buttonName;
	}

	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public GrantType getGrantType() {
		return grantType;
	}
	
	public void setGrantType(final GrantType grantType) {
		this.grantType = grantType;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(final String issuer) {
		this.issuer = issuer;
	}

	public RequestRepresentation getLogoutRequest() {
		return logoutRequest;
	}
	
	public void setLogoutRequest(final RequestRepresentation logoutRequest) {
		this.logoutRequest = logoutRequest;
	}

	public boolean getOnlyManagementTenantAccess() {
		return onlyManagementTenantAccess;
	}
	
	public void setOnlyManagementTenantAccess(final boolean onlyManagementTenantAccess) {
		this.onlyManagementTenantAccess = onlyManagementTenantAccess;
	}

	public OnNewUser getOnNewUser() {
		return onNewUser;
	}
	
	public void setOnNewUser(final OnNewUser onNewUser) {
		this.onNewUser = onNewUser;
	}

	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(final String providerName) {
		this.providerName = providerName;
	}

	public String getRedirectToPlatform() {
		return redirectToPlatform;
	}
	
	public void setRedirectToPlatform(final String redirectToPlatform) {
		this.redirectToPlatform = redirectToPlatform;
	}

	public RequestRepresentation getRefreshRequest() {
		return refreshRequest;
	}
	
	public void setRefreshRequest(final RequestRepresentation refreshRequest) {
		this.refreshRequest = refreshRequest;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public OAuthSessionConfiguration getSessionConfiguration() {
		return sessionConfiguration;
	}
	
	public void setSessionConfiguration(final OAuthSessionConfiguration sessionConfiguration) {
		this.sessionConfiguration = sessionConfiguration;
	}

	public SignatureVerificationConfig getSignatureVerificationConfig() {
		return signatureVerificationConfig;
	}
	
	public void setSignatureVerificationConfig(final SignatureVerificationConfig signatureVerificationConfig) {
		this.signatureVerificationConfig = signatureVerificationConfig;
	}

	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(final String template) {
		this.template = template;
	}

	public RequestRepresentation getTokenRequest() {
		return tokenRequest;
	}
	
	public void setTokenRequest(final RequestRepresentation tokenRequest) {
		this.tokenRequest = tokenRequest;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	public UserIdConfig getUserIdConfig() {
		return userIdConfig;
	}
	
	public void setUserIdConfig(final UserIdConfig userIdConfig) {
		this.userIdConfig = userIdConfig;
	}

	public UserManagementSource getUserManagementSource() {
		return userManagementSource;
	}
	
	public void setUserManagementSource(final UserManagementSource userManagementSource) {
		this.userManagementSource = userManagementSource;
	}

	public boolean getVisibleOnLoginPage() {
		return visibleOnLoginPage;
	}
	
	public void setVisibleOnLoginPage(final boolean visibleOnLoginPage) {
		this.visibleOnLoginPage = visibleOnLoginPage;
	}

	public ExternalTokenConfig getExternalTokenConfig() {
		return externalTokenConfig;
	}
	
	public void setExternalTokenConfig(final ExternalTokenConfig externalTokenConfig) {
		this.externalTokenConfig = externalTokenConfig;
	}

	
	/**
	 * <p>The authentication configuration grant type identifier.</p>
	 */
	public enum GrantType {
		@JsonProperty("AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE"),
		@JsonProperty("PASSWORD")
		PASSWORD("PASSWORD");
	
		private String value;
	
		GrantType(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>The authentication configuration type. Note that the value is case insensitive.</p>
	 */
	public enum Type {
		@JsonProperty("BASIC")
		BASIC("BASIC"),
		@JsonProperty("OAUTH2")
		OAUTH2("OAUTH2"),
		@JsonProperty("OAUTH2_INTERNAL")
		OAUTH2INTERNAL("OAUTH2_INTERNAL");
	
		private String value;
	
		Type(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * <p>Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.</p>
	 */
	public enum UserManagementSource {
		@JsonProperty("INTERNAL")
		INTERNAL("INTERNAL"),
		@JsonProperty("REMOTE")
		REMOTE("REMOTE");
	
		private String value;
	
		UserManagementSource(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * <p>SSO specific. Describes the fields in the access token from the external server containing user information.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class AccessTokenToUserDataMapping {
	
		/**
		 * <p>The name of the field containing the user's email.</p>
		 */
		private String emailClaimName;
	
		/**
		 * <p>The name of the field containing the user's first name.</p>
		 */
		private String firstNameClaimName;
	
		/**
		 * <p>The name of the field containing the user's last name.</p>
		 */
		private String lastNameClaimName;
	
		/**
		 * <p>The name of the field containing the user's phone number.</p>
		 */
		private String phoneNumberClaimName;
	
		public String getEmailClaimName() {
			return emailClaimName;
		}
		
		public void setEmailClaimName(final String emailClaimName) {
			this.emailClaimName = emailClaimName;
		}
	
		public String getFirstNameClaimName() {
			return firstNameClaimName;
		}
		
		public void setFirstNameClaimName(final String firstNameClaimName) {
			this.firstNameClaimName = firstNameClaimName;
		}
	
		public String getLastNameClaimName() {
			return lastNameClaimName;
		}
		
		public void setLastNameClaimName(final String lastNameClaimName) {
			this.lastNameClaimName = lastNameClaimName;
		}
	
		public String getPhoneNumberClaimName() {
			return phoneNumberClaimName;
		}
		
		public void setPhoneNumberClaimName(final String phoneNumberClaimName) {
			this.phoneNumberClaimName = phoneNumberClaimName;
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
			if (r != null && r instanceof AccessTokenToUserDataMapping) {
				AccessTokenToUserDataMapping comparer = (AccessTokenToUserDataMapping) r;
				if (String.valueOf(comparer.getEmailClaimName()).equals(String.valueOf(this.getEmailClaimName())) && String.valueOf(comparer.getFirstNameClaimName()).equals(String.valueOf(this.getFirstNameClaimName())) && String.valueOf(comparer.getLastNameClaimName()).equals(String.valueOf(this.getLastNameClaimName())) && String.valueOf(comparer.getPhoneNumberClaimName()).equals(String.valueOf(this.getPhoneNumberClaimName()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * <p>SSO specific. Describes the process of internal user creation during login with the external authorization server.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class OnNewUser {
	
		/**
		 * <p>Modern version of configuration of default groups and applications. This ensures backward compatibility.</p>
		 */
		private DynamicMapping dynamicMapping;
	
		public DynamicMapping getDynamicMapping() {
			return dynamicMapping;
		}
		
		public void setDynamicMapping(final DynamicMapping dynamicMapping) {
			this.dynamicMapping = dynamicMapping;
		}
	
		/**
		 * <p>Modern version of configuration of default groups and applications. This ensures backward compatibility.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class DynamicMapping {
		
			/**
			 * <p>Configuration of the mapping.</p>
			 */
			private Configuration configuration;
		
			/**
			 * <p>Represents rules used to assign groups and applications.</p>
			 */
			private Mappings[] mappings;
		
			/**
			 * <p>Represents rules used to assign inventory roles.</p>
			 */
			private InventoryMappings[] inventoryMappings;
		
			public Configuration getConfiguration() {
				return configuration;
			}
			
			public void setConfiguration(final Configuration configuration) {
				this.configuration = configuration;
			}
		
			public Mappings[] getMappings() {
				return mappings;
			}
			
			public void setMappings(final Mappings[] mappings) {
				this.mappings = mappings;
			}
		
			public InventoryMappings[] getInventoryMappings() {
				return inventoryMappings;
			}
			
			public void setInventoryMappings(final InventoryMappings[] inventoryMappings) {
				this.inventoryMappings = inventoryMappings;
			}
		
			/**
			 * <p>Configuration of the mapping.</p>
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Configuration {
			
				/**
				 * <p>Indicates whether the mapping should be evaluated always or only during the first external login when the internal user is created.</p>
				 */
				private boolean mapRolesOnlyForNewUser;
			
				/**
				 * <p>If set to <code>true</code>, dynamic access mapping is only managed for global roles, applications and inventory roles which are listed in the configuration. Others remain unchanged.</p>
				 */
				private boolean manageRolesOnlyFromAccessMapping;
			
				public boolean getMapRolesOnlyForNewUser() {
					return mapRolesOnlyForNewUser;
				}
				
				public void setMapRolesOnlyForNewUser(final boolean mapRolesOnlyForNewUser) {
					this.mapRolesOnlyForNewUser = mapRolesOnlyForNewUser;
				}
			
				public boolean getManageRolesOnlyFromAccessMapping() {
					return manageRolesOnlyFromAccessMapping;
				}
				
				public void setManageRolesOnlyFromAccessMapping(final boolean manageRolesOnlyFromAccessMapping) {
					this.manageRolesOnlyFromAccessMapping = manageRolesOnlyFromAccessMapping;
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
					if (r != null && r instanceof Configuration) {
						Configuration comparer = (Configuration) r;
						if (Boolean.valueOf(comparer.getMapRolesOnlyForNewUser()).equals(Boolean.valueOf(this.getMapRolesOnlyForNewUser())) && Boolean.valueOf(comparer.getManageRolesOnlyFromAccessMapping()).equals(Boolean.valueOf(this.getManageRolesOnlyFromAccessMapping()))) {
							return true;
						}
					}
					return false;
				}
			}
		
			/**
			 * <p>Represents information of mapping access to groups and applications.</p>
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Mappings {
			
				/**
				 * <p>Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups, permit access to the specified applications or to assign specific inventory roles to device groups.</p>
				 */
				private JSONPredicateRepresentation when;
			
				/**
				 * <p>List of the applications' identifiers.</p>
				 */
				private int[] thenApplications;
			
				/**
				 * <p>List of the groups' identifiers.</p>
				 */
				private int[] thenGroups;
			
				public JSONPredicateRepresentation getWhen() {
					return when;
				}
				
				public void setWhen(final JSONPredicateRepresentation when) {
					this.when = when;
				}
			
				public int[] getThenApplications() {
					return thenApplications;
				}
				
				public void setThenApplications(final int[] thenApplications) {
					this.thenApplications = thenApplications;
				}
			
				public int[] getThenGroups() {
					return thenGroups;
				}
				
				public void setThenGroups(final int[] thenGroups) {
					this.thenGroups = thenGroups;
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
					if (r != null && r instanceof Mappings) {
						Mappings comparer = (Mappings) r;
						if (comparer.getWhen().equals(this.getWhen()) && comparer.getThenApplications().equals(this.getThenApplications()) && comparer.getThenGroups().equals(this.getThenGroups())) {
							return true;
						}
					}
					return false;
				}
			}
		
			/**
			 * <p>Represents information of mapping access to inventory roles.</p>
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class InventoryMappings {
			
				/**
				 * <p>Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups, permit access to the specified applications or to assign specific inventory roles to device groups.</p>
				 */
				private JSONPredicateRepresentation when;
			
				/**
				 * <p>List of the OAuth inventory assignments.</p>
				 */
				private ThenInventoryRoles[] thenInventoryRoles;
			
				public JSONPredicateRepresentation getWhen() {
					return when;
				}
				
				public void setWhen(final JSONPredicateRepresentation when) {
					this.when = when;
				}
			
				public ThenInventoryRoles[] getThenInventoryRoles() {
					return thenInventoryRoles;
				}
				
				public void setThenInventoryRoles(final ThenInventoryRoles[] thenInventoryRoles) {
					this.thenInventoryRoles = thenInventoryRoles;
				}
			
				/**
				 * <p>Represents inventory roles for a specific device group.</p>
				 */
				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(Include.NON_NULL)
				public static class ThenInventoryRoles {
				
					/**
					 * <p>A unique identifier for the managed object for which the roles are assigned.</p>
					 */
					private String managedObject;
				
					/**
					 * <p>List of the inventory roles' identifiers.</p>
					 */
					private int[] roleIds;
				
					public String getManagedObject() {
						return managedObject;
					}
					
					public void setManagedObject(final String managedObject) {
						this.managedObject = managedObject;
					}
				
					public int[] getRoleIds() {
						return roleIds;
					}
					
					public void setRoleIds(final int[] roleIds) {
						this.roleIds = roleIds;
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
						if (r != null && r instanceof ThenInventoryRoles) {
							ThenInventoryRoles comparer = (ThenInventoryRoles) r;
							if (String.valueOf(comparer.getManagedObject()).equals(String.valueOf(this.getManagedObject())) && comparer.getRoleIds().equals(this.getRoleIds())) {
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
					if (r != null && r instanceof InventoryMappings) {
						InventoryMappings comparer = (InventoryMappings) r;
						if (comparer.getWhen().equals(this.getWhen()) && comparer.getThenInventoryRoles().equals(this.getThenInventoryRoles())) {
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
				if (r != null && r instanceof DynamicMapping) {
					DynamicMapping comparer = (DynamicMapping) r;
					if (comparer.getConfiguration().equals(this.getConfiguration()) && comparer.getMappings().equals(this.getMappings()) && comparer.getInventoryMappings().equals(this.getInventoryMappings())) {
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
			if (r != null && r instanceof OnNewUser) {
				OnNewUser comparer = (OnNewUser) r;
				if (comparer.getDynamicMapping().equals(this.getDynamicMapping())) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class SignatureVerificationConfig {
	
		/**
		 * <p>AAD signature verification configuration.</p>
		 */
		private Aad aad;
	
		/**
		 * <p>ADFS manifest signature verification configuration.</p>
		 */
		private AdfsManifest adfsManifest;
	
		/**
		 * <p>The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.</p>
		 */
		private Jwks jwks;
	
		/**
		 * <p>Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.</p>
		 */
		private Manual manual;
	
		public Aad getAad() {
			return aad;
		}
		
		public void setAad(final Aad aad) {
			this.aad = aad;
		}
	
		public AdfsManifest getAdfsManifest() {
			return adfsManifest;
		}
		
		public void setAdfsManifest(final AdfsManifest adfsManifest) {
			this.adfsManifest = adfsManifest;
		}
	
		public Jwks getJwks() {
			return jwks;
		}
		
		public void setJwks(final Jwks jwks) {
			this.jwks = jwks;
		}
	
		public Manual getManual() {
			return manual;
		}
		
		public void setManual(final Manual manual) {
			this.manual = manual;
		}
	
		/**
		 * <p>AAD signature verification configuration.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Aad {
		
			/**
			 * <p>URL used to retrieve the public key used for signature verification.</p>
			 */
			private String publicKeyDiscoveryUrl;
		
			public String getPublicKeyDiscoveryUrl() {
				return publicKeyDiscoveryUrl;
			}
			
			public void setPublicKeyDiscoveryUrl(final String publicKeyDiscoveryUrl) {
				this.publicKeyDiscoveryUrl = publicKeyDiscoveryUrl;
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
				if (r != null && r instanceof Aad) {
					Aad comparer = (Aad) r;
					if (String.valueOf(comparer.getPublicKeyDiscoveryUrl()).equals(String.valueOf(this.getPublicKeyDiscoveryUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * <p>ADFS manifest signature verification configuration.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class AdfsManifest {
		
			/**
			 * <p>The URI to the manifest resource.</p>
			 */
			private String manifestUrl;
		
			public String getManifestUrl() {
				return manifestUrl;
			}
			
			public void setManifestUrl(final String manifestUrl) {
				this.manifestUrl = manifestUrl;
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
				if (r != null && r instanceof AdfsManifest) {
					AdfsManifest comparer = (AdfsManifest) r;
					if (String.valueOf(comparer.getManifestUrl()).equals(String.valueOf(this.getManifestUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * <p>The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Jwks {
		
			/**
			 * <p>The URI to the public key resource.</p>
			 */
			private String jwksUrl;
		
			public String getJwksUrl() {
				return jwksUrl;
			}
			
			public void setJwksUrl(final String jwksUrl) {
				this.jwksUrl = jwksUrl;
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
				if (r != null && r instanceof Jwks) {
					Jwks comparer = (Jwks) r;
					if (String.valueOf(comparer.getJwksUrl()).equals(String.valueOf(this.getJwksUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * <p>Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Manual {
		
			/**
			 * <p>The name of the field in the JWT access token containing the certificate identifier.</p>
			 */
			private String certIdField;
		
			/**
			 * <p>Indicates whether the certificate identifier should be read from the JWT access token.</p>
			 */
			private boolean certIdFromField;
		
			/**
			 * <p>Details of the certificates.</p>
			 */
			private Certificates certificates;
		
			public String getCertIdField() {
				return certIdField;
			}
			
			public void setCertIdField(final String certIdField) {
				this.certIdField = certIdField;
			}
		
			public boolean getCertIdFromField() {
				return certIdFromField;
			}
			
			public void setCertIdFromField(final boolean certIdFromField) {
				this.certIdFromField = certIdFromField;
			}
		
			public Certificates getCertificates() {
				return certificates;
			}
			
			public void setCertificates(final Certificates certificates) {
				this.certificates = certificates;
			}
		
			/**
			 * <p>Details of the certificates.</p>
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Certificates {
			
				/**
				 * <p>The signing algorithm of the JWT access token.</p>
				 */
				private Alg alg;
			
				/**
				 * <p>The public key certificate.</p>
				 */
				private String publicKey;
			
				/**
				 * <p>The validity start date of the certificate.</p>
				 */
				private String validFrom;
			
				/**
				 * <p>The expiry date of the certificate.</p>
				 */
				private String validTill;
			
				public Alg getAlg() {
					return alg;
				}
				
				public void setAlg(final Alg alg) {
					this.alg = alg;
				}
			
				public String getPublicKey() {
					return publicKey;
				}
				
				public void setPublicKey(final String publicKey) {
					this.publicKey = publicKey;
				}
			
				public String getValidFrom() {
					return validFrom;
				}
				
				public void setValidFrom(final String validFrom) {
					this.validFrom = validFrom;
				}
			
				public String getValidTill() {
					return validTill;
				}
				
				public void setValidTill(final String validTill) {
					this.validTill = validTill;
				}
			
				
				/**
				 * <p>The signing algorithm of the JWT access token.</p>
				 */
				public enum Alg {
					@JsonProperty("RSA")
					RSA("RSA"),
					@JsonProperty("PCKS")
					PCKS("PCKS");
				
					private String value;
				
					Alg(final String value) {
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
					if (r != null && r instanceof Certificates) {
						Certificates comparer = (Certificates) r;
						if (comparer.getAlg().equals(this.getAlg()) && String.valueOf(comparer.getPublicKey()).equals(String.valueOf(this.getPublicKey())) && String.valueOf(comparer.getValidFrom()).equals(String.valueOf(this.getValidFrom())) && String.valueOf(comparer.getValidTill()).equals(String.valueOf(this.getValidTill()))) {
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
				if (r != null && r instanceof Manual) {
					Manual comparer = (Manual) r;
					if (String.valueOf(comparer.getCertIdField()).equals(String.valueOf(this.getCertIdField())) && Boolean.valueOf(comparer.getCertIdFromField()).equals(Boolean.valueOf(this.getCertIdFromField())) && comparer.getCertificates().equals(this.getCertificates())) {
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
			if (r != null && r instanceof SignatureVerificationConfig) {
				SignatureVerificationConfig comparer = (SignatureVerificationConfig) r;
				if (comparer.getAad().equals(this.getAad()) && comparer.getAdfsManifest().equals(this.getAdfsManifest()) && comparer.getJwks().equals(this.getJwks()) && comparer.getManual().equals(this.getManual())) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * <p>SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class UserIdConfig {
	
		/**
		 * <p>Used only when <code>useConstantValue</code> is set to <code>true</code>.</p>
		 */
		private String constantValue;
	
		/**
		 * <p>The name of the field containing the JWT.</p>
		 */
		private String jwtField;
	
		/**
		 * <p>Not recommended. If set to <code>true</code>, all SSO users will share one account in the Cumulocity IoT platform.</p>
		 */
		private boolean useConstantValue;
	
		public String getConstantValue() {
			return constantValue;
		}
		
		public void setConstantValue(final String constantValue) {
			this.constantValue = constantValue;
		}
	
		public String getJwtField() {
			return jwtField;
		}
		
		public void setJwtField(final String jwtField) {
			this.jwtField = jwtField;
		}
	
		public boolean getUseConstantValue() {
			return useConstantValue;
		}
		
		public void setUseConstantValue(final boolean useConstantValue) {
			this.useConstantValue = useConstantValue;
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
			if (r != null && r instanceof UserIdConfig) {
				UserIdConfig comparer = (UserIdConfig) r;
				if (String.valueOf(comparer.getConstantValue()).equals(String.valueOf(this.getConstantValue())) && String.valueOf(comparer.getJwtField()).equals(String.valueOf(this.getJwtField())) && Boolean.valueOf(comparer.getUseConstantValue()).equals(Boolean.valueOf(this.getUseConstantValue()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * <p>A configuration for authentication with an access token from the authorization server.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ExternalTokenConfig {
	
		/**
		 * <p>Indicates whether authentication is enabled or disabled.</p>
		 */
		private boolean enabled;
	
		/**
		 * <p>Points to the claim of the access token from the authorization server that must be used as the username in the Cumulocity IoT platform.</p>
		 */
		private UserOrAppIdConfig userOrAppIdConfig;
	
		/**
		 * <p>If set to <code>true</code>, the access token is validated against the authorization server by way of introspection or user info request.</p>
		 */
		private boolean validationRequired;
	
		/**
		 * <p>The method of validation of the access token.</p>
		 */
		private ValidationMethod validationMethod;
	
		private RequestRepresentation tokenValidationRequest;
	
		/**
		 * <p>The frequency (in Minutes) in which Cumulocity sends a validation request to authorization server. The recommended frequency is 1 minute.</p>
		 */
		private int accessTokenValidityCheckIntervalInMinutes;
	
		public boolean getEnabled() {
			return enabled;
		}
		
		public void setEnabled(final boolean enabled) {
			this.enabled = enabled;
		}
	
		public UserOrAppIdConfig getUserOrAppIdConfig() {
			return userOrAppIdConfig;
		}
		
		public void setUserOrAppIdConfig(final UserOrAppIdConfig userOrAppIdConfig) {
			this.userOrAppIdConfig = userOrAppIdConfig;
		}
	
		public boolean getValidationRequired() {
			return validationRequired;
		}
		
		public void setValidationRequired(final boolean validationRequired) {
			this.validationRequired = validationRequired;
		}
	
		public ValidationMethod getValidationMethod() {
			return validationMethod;
		}
		
		public void setValidationMethod(final ValidationMethod validationMethod) {
			this.validationMethod = validationMethod;
		}
	
		public RequestRepresentation getTokenValidationRequest() {
			return tokenValidationRequest;
		}
		
		public void setTokenValidationRequest(final RequestRepresentation tokenValidationRequest) {
			this.tokenValidationRequest = tokenValidationRequest;
		}
	
		public int getAccessTokenValidityCheckIntervalInMinutes() {
			return accessTokenValidityCheckIntervalInMinutes;
		}
		
		public void setAccessTokenValidityCheckIntervalInMinutes(final int accessTokenValidityCheckIntervalInMinutes) {
			this.accessTokenValidityCheckIntervalInMinutes = accessTokenValidityCheckIntervalInMinutes;
		}
	
		
		/**
		 * <p>The method of validation of the access token.</p>
		 */
		public enum ValidationMethod {
			@JsonProperty("INTROSPECTION")
			INTROSPECTION("INTROSPECTION"),
			@JsonProperty("USERINFO")
			USERINFO("USERINFO");
		
			private String value;
		
			ValidationMethod(final String value) {
				this.value = value;
			}
		
			public String getValue() {
				return value;
			}
		}
	
		/**
		 * <p>Points to the claim of the access token from the authorization server that must be used as the username in the Cumulocity IoT platform.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class UserOrAppIdConfig {
		
			/**
			 * <p>Used only if <code>useConstantValue</code> is set to <code>true</code>.</p>
			 */
			private String constantValue;
		
			/**
			 * <p>The name of the field containing the JWT.</p>
			 */
			private String jwtField;
		
			/**
			 * <p>Not recommended. If set to <code>true</code>, all users share a single account in the Cumulocity IoT platform.</p>
			 */
			private boolean useConstantValue;
		
			public String getConstantValue() {
				return constantValue;
			}
			
			public void setConstantValue(final String constantValue) {
				this.constantValue = constantValue;
			}
		
			public String getJwtField() {
				return jwtField;
			}
			
			public void setJwtField(final String jwtField) {
				this.jwtField = jwtField;
			}
		
			public boolean getUseConstantValue() {
				return useConstantValue;
			}
			
			public void setUseConstantValue(final boolean useConstantValue) {
				this.useConstantValue = useConstantValue;
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
				if (r != null && r instanceof UserOrAppIdConfig) {
					UserOrAppIdConfig comparer = (UserOrAppIdConfig) r;
					if (String.valueOf(comparer.getConstantValue()).equals(String.valueOf(this.getConstantValue())) && String.valueOf(comparer.getJwtField()).equals(String.valueOf(this.getJwtField())) && Boolean.valueOf(comparer.getUseConstantValue()).equals(Boolean.valueOf(this.getUseConstantValue()))) {
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
			if (r != null && r instanceof ExternalTokenConfig) {
				ExternalTokenConfig comparer = (ExternalTokenConfig) r;
				if (Boolean.valueOf(comparer.getEnabled()).equals(Boolean.valueOf(this.getEnabled())) && comparer.getUserOrAppIdConfig().equals(this.getUserOrAppIdConfig()) && Boolean.valueOf(comparer.getValidationRequired()).equals(Boolean.valueOf(this.getValidationRequired())) && comparer.getValidationMethod().equals(this.getValidationMethod()) && comparer.getTokenValidationRequest().equals(this.getTokenValidationRequest()) && Integer.valueOf(comparer.getAccessTokenValidityCheckIntervalInMinutes()).equals(Integer.valueOf(this.getAccessTokenValidityCheckIntervalInMinutes()))) {
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
		if (r != null && r instanceof AuthConfig) {
			AuthConfig comparer = (AuthConfig) r;
			if (comparer.getAccessTokenToUserDataMapping().equals(this.getAccessTokenToUserDataMapping()) && String.valueOf(comparer.getAudience()).equals(String.valueOf(this.getAudience())) && comparer.getAuthorizationRequest().equals(this.getAuthorizationRequest()) && comparer.getAuthenticationRestrictions().equals(this.getAuthenticationRestrictions()) && String.valueOf(comparer.getButtonName()).equals(String.valueOf(this.getButtonName())) && String.valueOf(comparer.getClientId()).equals(String.valueOf(this.getClientId())) && comparer.getGrantType().equals(this.getGrantType()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getIssuer()).equals(String.valueOf(this.getIssuer())) && comparer.getLogoutRequest().equals(this.getLogoutRequest()) && Boolean.valueOf(comparer.getOnlyManagementTenantAccess()).equals(Boolean.valueOf(this.getOnlyManagementTenantAccess())) && comparer.getOnNewUser().equals(this.getOnNewUser()) && String.valueOf(comparer.getProviderName()).equals(String.valueOf(this.getProviderName())) && String.valueOf(comparer.getRedirectToPlatform()).equals(String.valueOf(this.getRedirectToPlatform())) && comparer.getRefreshRequest().equals(this.getRefreshRequest()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSessionConfiguration().equals(this.getSessionConfiguration()) && comparer.getSignatureVerificationConfig().equals(this.getSignatureVerificationConfig()) && String.valueOf(comparer.getTemplate()).equals(String.valueOf(this.getTemplate())) && comparer.getTokenRequest().equals(this.getTokenRequest()) && comparer.getType().equals(this.getType()) && comparer.getUserIdConfig().equals(this.getUserIdConfig()) && comparer.getUserManagementSource().equals(this.getUserManagementSource()) && Boolean.valueOf(comparer.getVisibleOnLoginPage()).equals(Boolean.valueOf(this.getVisibleOnLoginPage())) && comparer.getExternalTokenConfig().equals(this.getExternalTokenConfig())) {
				return true;
			}
		}
		return false;
	}
}
