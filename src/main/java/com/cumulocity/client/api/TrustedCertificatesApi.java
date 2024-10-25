// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.UploadedTrustedCertificate;
import com.cumulocity.client.model.UploadedTrustedCertificateCollection;
import com.cumulocity.client.model.TrustedCertificate;
import com.cumulocity.client.model.UploadedTrustedCertSignedVerificationCode;
import com.cumulocity.client.model.UpdateCRLEntries;
import com.cumulocity.client.model.TrustedCertificateCollection;
import com.cumulocity.client.model.VerifyCertificateChain;
import com.cumulocity.client.model.AccessToken;

/**
 * <p>API methods for managing trusted certificates used to establish device connections via MQTT.</p>
 * <p>More detailed information about trusted certificates and their role can be found in <a href="https://cumulocity.com/docs/device-management-application/managing-device-data/">Device management > Device management application > Managing device data</a> in the Cumulocity IoT user documentation.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header must be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class TrustedCertificatesApi extends AdaptableApi {

	public TrustedCertificatesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all stored certificates</p>
	 * <p>Retrieve all the trusted certificates of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_READ) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the trusted certificates are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalElements</code> statistics are cached for 10 seconds.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<TrustedCertificateCollection> getTrustedCertificates(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TrustedCertificateCollection.class);
	}
	
	/**
	 * <p>Add a new certificate</p>
	 * <p>Add a new trusted certificate to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The certificate was added to the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate ��� A certificate with the same fingerprint already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� Invalid certificate data.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param addToTrustStore
	 * <p>If set to <code>true</code> the certificate is added to the truststore.</p>
	 * <p>The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.</p>
	 */
	public CompletionStage<TrustedCertificate> addTrustedCertificate(final UploadedTrustedCertificate body, final String tenantId, final String xCumulocityProcessingMode, final boolean addToTrustStore) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates")
			.queryParam("addToTrustStore", addToTrustStore)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * <p>Add multiple certificates</p>
	 * <p>Add multiple trusted certificates to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The certificates were added to the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate ��� A certificate with the same fingerprint already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� Invalid certificates data.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param addToTrustStore
	 * <p>If set to <code>true</code> the certificate is added to the truststore.</p>
	 * <p>The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.</p>
	 */
	public CompletionStage<TrustedCertificateCollection> addTrustedCertificates(final UploadedTrustedCertificateCollection body, final String tenantId, final boolean addToTrustStore) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "next");
		removeFromNode(jsonNode, "prev");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "statistics");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path("bulk")
			.queryParam("addToTrustStore", addToTrustStore)
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificateCollection.class);
	}
	
	/**
	 * <p>Retrieve a stored certificate</p>
	 * <p>Retrieve the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_READ) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the trusted certificate is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> getTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TrustedCertificate.class);
	}
	
	/**
	 * <p>Update a stored certificate</p>
	 * <p>Update the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The certificate was updated on the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> updateTrustedCertificate(final TrustedCertificate body, final String tenantId, final String fingerprint) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "proofOfPossessionValid");
		removeFromNode(jsonNode, "notAfter");
		removeFromNode(jsonNode, "serialNumber");
		removeFromNode(jsonNode, "proofOfPossessionVerificationCodeUsableUntil");
		removeFromNode(jsonNode, "subject");
		removeFromNode(jsonNode, "algorithmName");
		removeFromNode(jsonNode, "version");
		removeFromNode(jsonNode, "issuer");
		removeFromNode(jsonNode, "notBefore");
		removeFromNode(jsonNode, "proofOfPossessionUnsignedVerificationCode");
		removeFromNode(jsonNode, "fingerprint");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "certInPemFormat");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * <p>Remove a stored certificate</p>
	 * <p>Remove a stored trusted certificate (by a given fingerprint) from a specific tenant (by a given ID).When a trusted certificate is deleted, the established MQTT connection to all devices that are using the corresponding certificate are closed.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>The trusted certificate was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Certificate not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<Response> removeTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Provide the proof of possession for an already uploaded certificate</p>
	 * <p>Provide the proof of possession for a specific uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The provided signed verification code check was successful.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>The provided signed verification code is not correct.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Proof of possession for the certificate was not confirmed.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> proveCertificatePossession(final UploadedTrustedCertSignedVerificationCode body, final String tenantId, final String fingerprint) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("pop")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * <p>Confirm an already uploaded certificate</p>
	 * <p>Confirm an already uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the management tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The certificate is confirmed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>The verification was not successful. Certificate not confirmed.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> confirmCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("confirmed")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * <p>Generate a verification code for the proof of possession operation for the given certificate</p>
	 * <p>Generate a verification code for the proof of possession operation for the certificate (by a given fingerprint).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The verification code was generated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> generateVerificationCode(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("verification-code")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * <p>Verify a certificate chain</p>
	 * <p>Verify a device certificate chain against a specific tenant using file upload or by HTTP headers.The tenant ID is <code>optional</code> and this api will try to resolve the tenant from the chain if not found in the request header.For file upload, the max chain length support is 10 and for a header it is 5.</p>
	 * <p>If CRL (certificate revocation list) check is enabled on the tenant and the certificate chain is identified to be revoked during validation the further validation of the chain stops and returns unauthorized.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> File upload takes precedence over HTTP headers if both are passed.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_READ) <b>AND</b> (is the current tenant <b>OR</b> is current management tenant) <b>OR</b> (is authenticated <b>AND</b> is current user service user)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The certificate chain is valid and not revoked.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unable to parse certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>One or more certificates in the chain are revoked or the certificate chain is not valid. Revoked certificates are checked first, then the validity of the certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>The tenant ID does not exist.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * @param file
	 * <p>File to be uploaded.</p>
	 * @param xCumulocityClientCertChain
	 * <p>Used to send a certificate chain in the header. Separate the chain with <code>,</code> and also each 64 bit block with <code> </code> (a space character).</p>
	 */
	public CompletionStage<VerifyCertificateChain> validateChain(final String tenantId, final byte[] file, final String xCumulocityClientCertChain) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("tenantId", tenantId, MediaType.valueOf("text/plain"));
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("tenant").path("trusted-certificates").path("verify-cert-chain")
			.request()
			.header("X-Cumulocity-Client-Cert-Chain", xCumulocityClientCertChain)
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), VerifyCertificateChain.class);
	}
	
	/**
	 * <p>Get revoked certificates</p>
	 * <p>This endpoint downloads current CRL file containing list of revoked certificate ina binary file format with <code>content-type</code> as <code>application/pkix-crl</code>.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_READ)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The CRL file of the current tenant.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<Response> downloadCrl() {
		return adapt().path("tenant").path("trusted-certificates").path("settings").path("crl")
			.request()
			.header("Accept", "application/pkix-crl")
			.rx()
			.method("GET");
	}
	
	/**
	 * <p>Add revoked certificates</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> A certificate revocation list (CRL) is a list of digital certificatesthat have been revoked by the issuing certificate authority (CA) before expiration date.In Cumulocity IoT, a CRL check can be in online or offline mode or both.</p>
	 * </blockquote>
	 * <p>An endpoint to add revoked certificate serial numbers for offline CRL check via payload or file.</p>
	 * <p>For payload, a JSON object required with list of CRL entries, for example:</p>
	 * <pre>
	 *   {
	 *    "crls": [
	 *      {
	 *        "serialNumberInHex": "1000",
	 *        "revocationDate": "2023-01-11T16:12:36.288Z"
	 *      }
	 *     ]
	 *    }
	 * </pre>
	 * <p>Each entry is composed of:</p>
	 * <ul>
	 * 	<li><p>serialNumberInHex: Needs to be in <code>Hexadecimal Value</code>. e.g As (1000)^16 == (4096)^10, So we have to enter 1000.If duplicate serial number exists in payload, the existing entry stays</br></p>
	 * 	</li>
	 * 	<li><p><code>revocationDate</code> - accepted Date format: <code>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</code>, for example: <code>2023-01-11T16:12:36.288Z</code>.This is an optional parameter and defaults to the current server UTC date time if not specified in the payload.If specified and the date is in future then those entries will be also defaulted to current date.</p>
	 * 	</li>
	 * </ul>
	 * <p>For file upload, each file can hold at maximum 5000 revocation entries.Multiple upload is allowed.In case of duplicates, the latest (last uploaded) entry is considered.</p>
	 * <p>See below for a sample CSV file:</p>
	 * <p>| SERIAL NO.  | REVOCATION DATE ||--|--|| 1000 | 2023-01-11T16:12:36.288Z |</p>
	 * <p>Each entry is composed of :</p>
	 * <ul>
	 * 	<li><p>serialNumberInHex: Needs to be in <code>Hexadecimal Value</code>. e.g (1000)^16 == (4096)^10, So we have to enter 1000.If duplicate serial number exists in payload, the latest entry will be taken.</br></p>
	 * 	</li>
	 * 	<li><p>revocationDate: Accepted Date format: <code>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</code> e.g: 2023-01-11T16:12:36.288Z.This is an optional and will be default to current server UTC date time if not specified in payload.If specified and the date is in future then those entries will be skipped.</p>
	 * 	</li>
	 * </ul>
	 * <p>The CRL setting for offline and online check can be enabled/disabled using <kbd><a href="#operation/putOptionResource">/tenant/options</a></kbd>.Keys are <code>crl.online.check.enabled</code> and <code>crl.offline.check.enabled</code> under the category <code>configuration</code>.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </section>
	 * <p><strong>������ Important:</strong> According to CRL policy, added serial numbers cannot be reversed.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>CRLs updated successfully.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unsupported date time format.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Response> updateCRL(final UpdateCRLEntries body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("trusted-certificates").path("settings").path("crl")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode));
	}
	
	/**
	 * <p>Add revoked certificates</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> A certificate revocation list (CRL) is a list of digital certificatesthat have been revoked by the issuing certificate authority (CA) before expiration date.In Cumulocity IoT, a CRL check can be in online or offline mode or both.</p>
	 * </blockquote>
	 * <p>An endpoint to add revoked certificate serial numbers for offline CRL check via payload or file.</p>
	 * <p>For payload, a JSON object required with list of CRL entries, for example:</p>
	 * <pre>
	 *   {
	 *    "crls": [
	 *      {
	 *        "serialNumberInHex": "1000",
	 *        "revocationDate": "2023-01-11T16:12:36.288Z"
	 *      }
	 *     ]
	 *    }
	 * </pre>
	 * <p>Each entry is composed of:</p>
	 * <ul>
	 * 	<li><p>serialNumberInHex: Needs to be in <code>Hexadecimal Value</code>. e.g As (1000)^16 == (4096)^10, So we have to enter 1000.If duplicate serial number exists in payload, the existing entry stays</br></p>
	 * 	</li>
	 * 	<li><p><code>revocationDate</code> - accepted Date format: <code>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</code>, for example: <code>2023-01-11T16:12:36.288Z</code>.This is an optional parameter and defaults to the current server UTC date time if not specified in the payload.If specified and the date is in future then those entries will be also defaulted to current date.</p>
	 * 	</li>
	 * </ul>
	 * <p>For file upload, each file can hold at maximum 5000 revocation entries.Multiple upload is allowed.In case of duplicates, the latest (last uploaded) entry is considered.</p>
	 * <p>See below for a sample CSV file:</p>
	 * <p>| SERIAL NO.  | REVOCATION DATE ||--|--|| 1000 | 2023-01-11T16:12:36.288Z |</p>
	 * <p>Each entry is composed of :</p>
	 * <ul>
	 * 	<li><p>serialNumberInHex: Needs to be in <code>Hexadecimal Value</code>. e.g (1000)^16 == (4096)^10, So we have to enter 1000.If duplicate serial number exists in payload, the latest entry will be taken.</br></p>
	 * 	</li>
	 * 	<li><p>revocationDate: Accepted Date format: <code>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</code> e.g: 2023-01-11T16:12:36.288Z.This is an optional and will be default to current server UTC date time if not specified in payload.If specified and the date is in future then those entries will be skipped.</p>
	 * 	</li>
	 * </ul>
	 * <p>The CRL setting for offline and online check can be enabled/disabled using <kbd><a href="#operation/putOptionResource">/tenant/options</a></kbd>.Keys are <code>crl.online.check.enabled</code> and <code>crl.offline.check.enabled</code> under the category <code>configuration</code>.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </section>
	 * <p><strong>������ Important:</strong> According to CRL policy, added serial numbers cannot be reversed.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>CRLs updated successfully.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unsupported date time format.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param file
	 * <p>File to be uploaded.</p>
	 */
	public CompletionStage<Response> updateCRL(final byte[] file) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("tenant").path("trusted-certificates").path("settings").path("crl")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/json")
			.rx()
			.method("PUT", Entity.entity(multiPartEntity, "multipart/form-data"));
	}
	
	/**
	 * <p>Obtain device access token</p>
	 * <p>Only those devices which are registered to use cert auth can authenticate via mTLS protocol and retrieve JWT token. Device access token API works only on port 8443 via mutual TLS (mTLS) connection.Immediate issuer of client certificate must present in Platform's truststore, if not then whole certificate chain needs to send in header and root or any intermediate certificate must be present in the Platform's truststore.We must have the following:</p>
	 * <ul>
	 * 	<li><p>private_key</p>
	 * 	</li>
	 * 	<li><p>client certificate</p>
	 * 	</li>
	 * 	<li><p>whole certificate chain (Optional - This API requires the client to send a custom header <code>X-SSL-CERT-CHAIN</code> only if the immediate issuer of the client's certificate is not uploaded as a trusted certificate on the platform. If the immediate issuer is already uploaded and trusted, the header can be omitted)</p>
	 * 	</li>
	 * </ul>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>Successfully retrieved device access token from device certificate.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unable to parse certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>One or more certificates in the chain are revoked or the certificate chain is not valid. Revoked certificates are checked first, then the validity of the certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Device access token feature is disabled.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>The verification was not successful.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xSslCertChain
	 * <p>Used to send a certificate chain in the header. Separate the chain with <code> </code> (a space character) and also each 64 bit block with <code> </code> (a space character).</p>
	 */
	public CompletionStage<AccessToken> obtainAccessToken(final String xSslCertChain) {
		return adapt().path("devicecontrol").path("deviceAccessToken")
			.request()
			.header("X-Ssl-Cert-Chain", xSslCertChain)
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", AccessToken.class);
	}
}
