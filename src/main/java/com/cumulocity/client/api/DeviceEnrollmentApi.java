// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;

/**
 * <p>Device enroll API to be used by a device to get a fresh new certificate. The device has to authenticate itself using its identifier and security token as the BasicAuth realm, user and password respectively. The tenant, identifier and security token must be shared with Cumulocity using the <a href="#tag/New-device-requests">New-device-requests</a> onboarding endpoint to set the security token for a device.Device re-enroll API to be used by a device to renew its certificate or replace its certificate with its current credentials (being a password or a JWT token).</p>
 */
public class DeviceEnrollmentApi extends AdaptableApi {

	public DeviceEnrollmentApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Create Device certificate which is signed by tenant's Certificate Authority(CA)</p>
	 * <p>Enable bulk device registration to the enrollment list through the existing <a href="#tag/New-device-requests">New-device-requests</a> endpoint.To support the new enrollment process, each device record must specify both a secret and a certificate as the authentication type.These EST devices will be added to the NewDeviceRegistration list with a status of Accepted.The ID and CREDENTIALS fields will be mapped to deviceId and security token, respectively, in the NewDeviceRegistrationData model, serving as temporary device credentials for authentication.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A device certificate was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<String> simpleEnrollDevice(final byte[] body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path(".well_known").path("est").path("simpleenroll")
			.request()
			.header("Content-Type", "application/pkcs10")
			.header("Accept", "application/pkcs7-mime;smime-type=certs-only, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("POST", Entity.json(jsonNode), String.class);
	}
	
	/**
	 * <p>Re-Issue certificates to Devices which is signed by tenant's Certificate Authority(CA)</p>
	 * <p>Enable bulk device registration to the enrollment list through the existing <code>/devicecontrol/newDeviceRequests</code> endpoint.To support the new enrollment process, each device record must specify both a secret and a certificate as the authentication type.These EST devices will be added to the NewDeviceRegistration list with a status of Accepted.The ID and CREDENTIALS fields will be mapped to deviceId and security token, respectively, in the NewDeviceRegistrationData model, serving as temporary device credentials for authentication.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A device certificate was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Byte[]> simpleReEnrollDevice(final byte[] body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path(".well_known").path("est").path("simplereenroll")
			.request()
			.header("Content-Type", "application/pkcs10")
			.header("Accept", "application/pkcs7-mime;smime-type=certs-only, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Byte[].class);
	}
}
