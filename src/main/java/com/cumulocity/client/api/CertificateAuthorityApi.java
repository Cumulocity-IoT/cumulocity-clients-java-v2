// Copyright (c) 2014-present Cumulocity GmbH, Duesseldorf, Germany and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Cumulocity GmbH

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.TrustedCertificate;

/**
 * <p>API to create a new CA certificate for tenant in Cumulocity.</p>
 */
public class CertificateAuthorityApi extends AdaptableApi {

	public CertificateAuthorityApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Create Certificate Authority(CA) for a tenant with CN as tenantID</p>
	 * <p>Create a key pair and self-sign a certificate with <tenantID> as the Common Name (CN).Store the private key in an encrypted tenant option.Store the certificate in the trusted certificate repository with auto-registration checked by default. The devices can be registered automatically.If the CA certificate is removed from the trusted certificate list, corresponding public and private key removed automatically from the database collection.If a CA is already present, return a message indicating the CA is already present.</p>
	 * <blockquote>
	 * <p><strong>⚠️ Important:</strong> Note that it is possible to call this endpoint without the ROLE_TENANT_MANAGEMENT_ADMIN or ROLE_TENANT_ADMIN role. Otherwise, if the the user does not have the required role, an HTTP response 403 will be returned.<section><h5>Required roles</h5>ROLE_TENANT_MANAGEMENT_ADMINROLE_TENANT_ADMIN</section></p>
	 * </blockquote>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The tenant's CA certificate was added to the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant Certificate Authority (CA) object not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate – Tenant CA is already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity – Invalid key pair configuration.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<TrustedCertificate> createCertificateAuthority() {
		return adapt().path("certificate-authority")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * <p>Renew Certificate Authority(CA) for a tenant with existing public and private keys.</p>
	 * <p>Renew the Certificate Authority (CA) for a tenant using existing public and private keys.The renewal will fail if either the public or private key is missing.If no CA exists, or if the current CA has more than 18 months remaining before expiration, a message will be returned indicating that the CA is either not present or not eligible for renewal.</p>
	 * <blockquote>
	 * <p><strong>⚠️ Important:</strong> Note that it is possible to call this endpoint without the ROLE_TENANT_MANAGEMENT_ADMIN or ROLE_TENANT_ADMIN role. Otherwise, if the the user does not have the required role, an HTTP response 403 will be returned.<section><h5>Required roles</h5>ROLE_TENANT_MANAGEMENT_ADMINROLE_TENANT_ADMIN</section></p>
	 * </blockquote>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The tenant's CA certificate renewed successfully.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant Certificate Authority (CA) object not found.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<TrustedCertificate> renewCertificateAuthority() {
		return adapt().path("certificate-authority").path("renew")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
}
