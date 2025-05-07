// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;

/**
 * <p>Device enroll API to be used by a device to issue an X509 certificate signed by tenant's <a href="#operation/postBulkNewDeviceRequestCollectionResource">certificate authority</a>. The identifier and enrollment OTP for a device must be first shared as a pre-shared-key (PSK) with Cumulocity using the <a href="#operation/postBulkNewDeviceRequestCollectionResource">bulkNewDeviceRequests</a> endpoint for certificate provisioning.Internally, ID and ENROLLMENT_OTP fields will be added to the NewDeviceRegistration list with a status of Accepted, serving as temporary device credentials for device authentication.</p>
 * <p>Device re-enroll API to be used by a device to renew its certificate or replace its certificate with its current credentials (being a password or a JWT token).</p>
 */
public class DeviceEnrollmentApi extends AdaptableApi {

	public DeviceEnrollmentApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Create Device certificate which is signed by tenant's Certificate Authority(CA)</p>
	 * <p>A device already registered for certificate provisioning by sharing <code>PSK</code>(as mentioned above) can request for a new X509 certificateusing the PSK as basic auth realm, along with a Certificate Sigining Request (CSR) using enroll API. Upon successfull validation andcertificate generation, a certificate in <code>PKCS#7</code> will be returned.</p>
	 * <blockquote>
	 * <p><strong>⚠️ Important:</strong> CSR must be a valid <code>PKCS#10</code> with deviceID as Common Name (CN).</p>
	 * </blockquote>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> CSR request with <code>CA:TRUE</code> constraint is not supported.</p>
	 * </blockquote>
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
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE
	 * </section>
	 * <p>A device using existing authentication mechanism (Basic or JWT) along with a Certificate Sigining Request, can request re-issue using reenroll API.Upon successfull validation and certificate generation, a certificate in <code>PKCS#7</code> will be returned.</p>
	 * <blockquote>
	 * <p><strong>⚠️ Important:</strong> CSR must be a valid <code>PKCS#10</code> with deviceID as Common Name (CN).</p>
	 * </blockquote>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> CSR request with <code>CA:TRUE</code> constraint is not supported.</p>
	 * </blockquote>
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
