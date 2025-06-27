// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PasswordChange {

	/**
	 * <p>The current password of the user performing the request.</p>
	 */
	private String currentUserPassword;

	/**
	 * <p>The new password to be set for the user performing the request.</p>
	 * <p>By default, the password must meet the following conditions:</p>
	 * <ul>
	 * 	<li><p>Consist of at least eight characters (this parameter can be configured by the management tenant.)</p>
	 * 	</li>
	 * 	<li><p>It must not have been used previously by user.</p>
	 * 	</li>
	 * 	<li><p>Include each of the following character types:</p>
	 * 	<ul>
	 * 		<li><p>uppercase letters: <code>[A-Z]</code>, for example <code>ABCDEF</code>.</p>
	 * 		</li>
	 * 		<li><p>lowercase letters: <code>[a-z]</code>, for example <code>abcdef</code>.</p>
	 * 		</li>
	 * 		<li><p>numbers: <code>[0-9]</code>, for example: <code>123456</code>.</p>
	 * 		</li>
	 * 		<li><p>any other symbol from following list <code> `~!@#$%^&*()_|+-=?;:'",.<>{}[]\/</code> as a special character, for example <code>!@#$%^</code>.</p>
	 * 		</li>
	 * 	</ul>
	 * 	</li>
	 * </ul>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> The password rules can be configured by the administrator, that means, your administrator can configure your account to enforce a password policy. You may be required to pick a strong password for example or to change your password regularly.</p>
	 * </blockquote>
	 */
	private String newPassword;

	public PasswordChange() {
	}

	public PasswordChange(final String currentUserPassword, final String newPassword) {
		this.currentUserPassword = currentUserPassword;
		this.newPassword = newPassword;
	}

	public String getCurrentUserPassword() {
		return currentUserPassword;
	}
	
	public void setCurrentUserPassword(final String currentUserPassword) {
		this.currentUserPassword = currentUserPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
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
		if (r != null && r instanceof PasswordChange) {
			PasswordChange comparer = (PasswordChange) r;
			if (String.valueOf(comparer.getCurrentUserPassword()).equals(String.valueOf(this.getCurrentUserPassword())) && String.valueOf(comparer.getNewPassword()).equals(String.valueOf(this.getNewPassword()))) {
				return true;
			}
		}
		return false;
	}
}
