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
public class User {

	/**
	 * <p>A list of applications for this user.</p>
	 */
	private Application[] applications;

	/**
	 * <p>An object with a list of custom properties.</p>
	 */
	private CustomProperties customProperties;

	/**
	 * <p>The user's display name in Cumulocity.</p>
	 */
	private String displayName;

	/**
	 * <p>The user's email address.</p>
	 */
	private String email;

	/**
	 * <p>Indicates whether the user is enabled or not. Disabled users cannot log in or perform API requests.</p>
	 */
	private boolean enabled;

	/**
	 * <p>The user's first name.</p>
	 */
	private String firstName;

	/**
	 * <p>An object with a list of user groups.</p>
	 */
	private Groups groups;

	/**
	 * <p>A unique identifier for this user.</p>
	 */
	private String id;

	/**
	 * <p>The user's last name.</p>
	 */
	private String lastName;

	/**
	 * <p>The date and time when the user's password was last changed, in <a href="https://www.w3.org/TR/NOTE-datetime">ISO 8601 datetime format</a>.</p>
	 */
	private String lastPasswordChange;

	/**
	 * <p>Indicates whether the user is subscribed to the newsletter or not.</p>
	 */
	private boolean newsletter;

	/**
	 * <p>Identifier of the parent user. If present, indicates that a user belongs to a user hierarchy by pointing to its direct ancestor. Can only be set by users with role USER_MANAGEMENT_ADMIN during user creation. Otherwise it's assigned automatically.</p>
	 */
	private String owner;

	/**
	 * <p>The user's password. Only Latin1 characters are allowed.</p>
	 * <p>If you do not specify a password when creating a new user with a POST request, it must contain the property <code>sendPasswordResetEmail</code> with a value of <code>true</code>.</p>
	 */
	private String password;

	/**
	 * <p>Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.</p>
	 */
	private PasswordStrength passwordStrength;

	/**
	 * <p>The user's phone number.</p>
	 */
	private String phone;

	/**
	 * <p>An object with a list of user roles.</p>
	 */
	private Roles roles;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>When set to <code>true</code>, this field will cause Cumulocity to send a password reset email to the email address specified.</p>
	 * <p>If there is no password specified when creating a new user with a POST request, this must be specified and it must be set to <code>true</code>.</p>
	 */
	private boolean sendPasswordResetEmail;

	/**
	 * <p>Indicates if the user should reset the password on the next login.</p>
	 */
	private boolean shouldResetPassword;

	/**
	 * <p>Indicates if the user has to use two-factor authentication to log in.</p>
	 */
	private boolean twoFactorAuthenticationEnabled;

	/**
	 * <p>The user's username. It can have a maximum of 1000 characters.</p>
	 */
	private String userName;

	/**
	 * <p>An object with a list of the user's device permissions.</p>
	 */
	@Deprecated
	private DevicePermissions devicePermissions;

	public Application[] getApplications() {
		return applications;
	}
	
	public void setApplications(final Application[] applications) {
		this.applications = applications;
	}

	public CustomProperties getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final CustomProperties customProperties) {
		this.customProperties = customProperties;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public Groups getGroups() {
		return groups;
	}
	
	public void setGroups(final Groups groups) {
		this.groups = groups;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getLastPasswordChange() {
		return lastPasswordChange;
	}
	
	public void setLastPasswordChange(final String lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public boolean getNewsletter() {
		return newsletter;
	}
	
	public void setNewsletter(final boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(final String owner) {
		this.owner = owner;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public PasswordStrength getPasswordStrength() {
		return passwordStrength;
	}
	
	public void setPasswordStrength(final PasswordStrength passwordStrength) {
		this.passwordStrength = passwordStrength;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Roles getRoles() {
		return roles;
	}
	
	public void setRoles(final Roles roles) {
		this.roles = roles;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public boolean getSendPasswordResetEmail() {
		return sendPasswordResetEmail;
	}
	
	public void setSendPasswordResetEmail(final boolean sendPasswordResetEmail) {
		this.sendPasswordResetEmail = sendPasswordResetEmail;
	}

	public boolean getShouldResetPassword() {
		return shouldResetPassword;
	}
	
	public void setShouldResetPassword(final boolean shouldResetPassword) {
		this.shouldResetPassword = shouldResetPassword;
	}

	public boolean getTwoFactorAuthenticationEnabled() {
		return twoFactorAuthenticationEnabled;
	}
	
	public void setTwoFactorAuthenticationEnabled(final boolean twoFactorAuthenticationEnabled) {
		this.twoFactorAuthenticationEnabled = twoFactorAuthenticationEnabled;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public DevicePermissions getDevicePermissions() {
		return devicePermissions;
	}
	
	public void setDevicePermissions(final DevicePermissions devicePermissions) {
		this.devicePermissions = devicePermissions;
	}

	
	/**
	 * <p>Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.</p>
	 */
	public enum PasswordStrength {
		@JsonProperty("GREEN")
		GREEN("GREEN"),
		@JsonProperty("YELLOW")
		YELLOW("YELLOW"),
		@JsonProperty("RED")
		RED("RED");
	
		private String value;
	
		PasswordStrength(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * <p>An object with a list of user groups.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Groups {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		/**
		 * <p>A list of user group references.</p>
		 */
		private GroupReference[] references;
	
		/**
		 * <p>Information about paging statistics.</p>
		 */
		private PageStatistics statistics;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public GroupReference[] getReferences() {
			return references;
		}
		
		public void setReferences(final GroupReference[] references) {
			this.references = references;
		}
	
		public PageStatistics getStatistics() {
			return statistics;
		}
		
		public void setStatistics(final PageStatistics statistics) {
			this.statistics = statistics;
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
			if (r != null && r instanceof Groups) {
				Groups comparer = (Groups) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getReferences().equals(this.getReferences()) && comparer.getStatistics().equals(this.getStatistics())) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * <p>An object with a list of user roles.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Roles {
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		/**
		 * <p>A list of user role references.</p>
		 */
		private RoleReference[] references;
	
		/**
		 * <p>Information about paging statistics.</p>
		 */
		private PageStatistics statistics;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public RoleReference[] getReferences() {
			return references;
		}
		
		public void setReferences(final RoleReference[] references) {
			this.references = references;
		}
	
		public PageStatistics getStatistics() {
			return statistics;
		}
		
		public void setStatistics(final PageStatistics statistics) {
			this.statistics = statistics;
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
			if (r != null && r instanceof Roles) {
				Roles comparer = (Roles) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getReferences().equals(this.getReferences()) && comparer.getStatistics().equals(this.getStatistics())) {
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
		if (r != null && r instanceof User) {
			User comparer = (User) r;
			if (comparer.getApplications().equals(this.getApplications()) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDisplayName()).equals(String.valueOf(this.getDisplayName())) && String.valueOf(comparer.getEmail()).equals(String.valueOf(this.getEmail())) && Boolean.valueOf(comparer.getEnabled()).equals(Boolean.valueOf(this.getEnabled())) && String.valueOf(comparer.getFirstName()).equals(String.valueOf(this.getFirstName())) && comparer.getGroups().equals(this.getGroups()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastName()).equals(String.valueOf(this.getLastName())) && String.valueOf(comparer.getLastPasswordChange()).equals(String.valueOf(this.getLastPasswordChange())) && Boolean.valueOf(comparer.getNewsletter()).equals(Boolean.valueOf(this.getNewsletter())) && String.valueOf(comparer.getOwner()).equals(String.valueOf(this.getOwner())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && comparer.getPasswordStrength().equals(this.getPasswordStrength()) && String.valueOf(comparer.getPhone()).equals(String.valueOf(this.getPhone())) && comparer.getRoles().equals(this.getRoles()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && Boolean.valueOf(comparer.getSendPasswordResetEmail()).equals(Boolean.valueOf(this.getSendPasswordResetEmail())) && Boolean.valueOf(comparer.getShouldResetPassword()).equals(Boolean.valueOf(this.getShouldResetPassword())) && Boolean.valueOf(comparer.getTwoFactorAuthenticationEnabled()).equals(Boolean.valueOf(this.getTwoFactorAuthenticationEnabled())) && String.valueOf(comparer.getUserName()).equals(String.valueOf(this.getUserName())) && comparer.getDevicePermissions().equals(this.getDevicePermissions())) {
				return true;
			}
		}
		return false;
	}
}
