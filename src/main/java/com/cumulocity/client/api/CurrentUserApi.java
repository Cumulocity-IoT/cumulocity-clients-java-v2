// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.CurrentUser;
import com.cumulocity.client.model.PasswordChange;
import com.cumulocity.client.model.CurrentUserTotpSecretActivity;
import com.cumulocity.client.model.CurrentUserTotpCode;
import com.cumulocity.client.model.CurrentUserTotpSecret;

/**
 * <p>The current user is the user that is currently authenticated with Cumulocity for the API calls.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class CurrentUserApi extends AdaptableApi {

	public CurrentUserApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve the current user</p>
	 * <p>Retrieve the user reference of the current user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <p>Users with ROLE_SYSTEM are not allowed to query with Accept header <code>application/vnd.com.nsn.cumulocity.user+json</code></p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the current user is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<CurrentUser> getCurrentUser() {
		return adapt().path("user").path("currentUser")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("GET", CurrentUser.class);
	}
	
	/**
	 * <p>Update the current user</p>
	 * <p>Update the current user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The current user was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<CurrentUser> updateCurrentUser(final CurrentUser body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "effectiveRoles");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		return adapt().path("user").path("currentUser")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.currentuser+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), CurrentUser.class);
	}
	
	/**
	 * <p>Update the current user's password</p>
	 * <p>Update the current user's  password.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> If the tenant uses OAI-Secure authentication, the current user will not be logged out. Instead, a new cookie will be set with a new token, and the previous token will expire within a minute.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The current user password was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> updateCurrentUserPassword(final PasswordChange body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("password")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Generate secret to set up TFA</p>
	 * <p>Generate a secret code to create a QR code to set up the two-factor authentication functionality using a TFA app/service.</p>
	 * <p>For more information about the feature, see <a href="https://www.cumulocity.com/docs/authentication/tfa/">Platform administration > Authentication > Two-factor authentication</a> in the Cumulocity user documentation.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the secret is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<CurrentUserTotpSecret> generateTfaSecret() {
		return adapt().path("user").path("currentUser").path("totpSecret")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", CurrentUserTotpSecret.class);
	}
	
	/**
	 * <p>Returns the activation state of the two-factor authentication feature.</p>
	 * <p>Returns the activation state of the two-factor authentication feature for the current user.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>Returns the activation state.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>User not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<CurrentUserTotpSecretActivity> getTfaState() {
		return adapt().path("user").path("currentUser").path("totpSecret").path("activity")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", CurrentUserTotpSecretActivity.class);
	}
	
	/**
	 * <p>Activates or deactivates the two-factor authentication feature</p>
	 * <p>Activates or deactivates the two-factor authentication feature for the current user.</p>
	 * <p>For more information about the feature, see <a href="https://www.cumulocity.com/docs/authentication/tfa/">Platform administration > Authentication > Two-factor authentication</a> in the Cumulocity user documentation.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>The two-factor authentication was activated or deactivated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Cannot deactivate TOTP setup.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>TFA TOTP secret does not exist. First generate secret.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> setTfaState(final CurrentUserTotpSecretActivity body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("totpSecret").path("activity")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Verify TFA code</p>
	 * <p>Verifies the authentication code that the current user received from a TFA app/service and uploaded to the platform to gain access or enable the two-factor authentication feature.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>The sent code was correct and the access can be granted.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Invalid verification code.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Cannot validate TFA TOTP code - user's TFA TOTP secret does not exist.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> verifyTfaCode(final CurrentUserTotpCode body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("totpSecret").path("verify")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("POST", Entity.json(jsonNode));
	}
}
