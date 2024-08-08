// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.SystemOptionCollection;
import com.cumulocity.client.model.SystemOption;

/**
 * <p>API methods to retrieve the read-only properties predefined in the platform's configuration.</p>
 * <p>For security reasons, a few system options are considered secured, which means the user must have the required role <strong>ROLE_OPTION_MANAGEMENT_ADMIN</strong> to read their values.</p>
 * <p>List of options:</p>
 * <p>|         Category          | Key                           | Considered as secured ||:-------------------------:|:------------------------------|:----------------------||         password          | green.min-length              | yes                   || two-factor-authentication | pin.validity                  | yes                   || two-factor-authentication | token.length                  | yes                   || two-factor-authentication | token.validity                | yes                   ||      authentication       | badRequestCounter             | yes                   ||           files           | microservice.zipped.max.size  | yes                   ||           files           | microservice.unzipped.max.size| yes                   ||           files           | webapp.zipped.max.size        | yes                   ||           files           | webapp.unzipped.max.size      | yes                   || two-factor-authentication | enforced                      | no                    ||       reportMailer        | available                     | no                    ||          system           | version                       | no                    ||          plugin           | eventprocessing.enabled       | no                    ||         password          | limit.validity                | no                    ||         password          | enforce.strength              | no                    || two-factor-authentication | strategy                      | no                    || two-factor-authentication | pin.length                    | no                    || two-factor-authentication | enabled                       | no                    || two-factor-authentication | enforced.group                | no                    || two-factor-authentication | tenant-scope-settings.enabled | no                    || two-factor-authentication | logout-on-browser-termination | no                    ||       connectivity        | microservice.url              | no                    ||       support-user        | enabled                       | no                    ||          support          | url                           | no                    ||         trackers          | supported.models              | no                    ||         encoding          | test                          | no                    ||        data-broker        | bootstrap.period              | no                    ||           files           | max.size                      | no                    ||      device-control       | bulkoperation.creationramp    | no                    ||         gainsight         | api.key                       | no                    ||            cep            | deprecation.alarm             | no                    ||       remoteaccess        | pass-through.enabled          | no                    ||    device-registration    | security-token.policy         | no                    |</p>
 */
public class SystemOptionsApi extends AdaptableApi {

	public SystemOptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all system options</p>
	 * <p>Retrieve all the system options available on the tenant.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Note that it is possible to call this endpoint without the ROLE_OPTION_MANAGEMENT_ADMIN role, but options that are considered secured (see the list of options above) will be obfuscated with a constant value <code>"<<Encrypted>>"</code>.</p>
	 * </blockquote>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the system options are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 */
	public CompletionStage<SystemOptionCollection> getSystemOptions() {
		return adapt().path("tenant").path("system").path("options")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
			.rx()
			.method("GET", SystemOptionCollection.class);
	}
	
	/**
	 * <p>Retrieve a specific system option</p>
	 * <p>Retrieve a specific system option (by a given category and key) on your tenant.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Note that it is possible to call this endpoint without the ROLE_OPTION_MANAGEMENT_ADMIN role, but only the options that are considered not secured (see the list of options above) will be returned. Otherwise, if the option is considered secured and the user does not have the required role, an HTTP response 403 will be returned.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the system option is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param category
	 * <p>The category of the system options.</p>
	 * @param key
	 * <p>The key of a system option.</p>
	 */
	public CompletionStage<SystemOption> getSystemOption(final String category, final String key) {
		return adapt().path("tenant").path("system").path("options").path(valueOf(category)).path(valueOf(key))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("GET", SystemOption.class);
	}
}
