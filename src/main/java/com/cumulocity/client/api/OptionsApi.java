// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Option;
import com.cumulocity.client.model.CategoryOptions;
import com.cumulocity.client.model.CategoryKeyOption;
import com.cumulocity.client.model.OptionCollection;

/**
 * <p>API methods to retrieve the options configured in the tenant.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class OptionsApi extends AdaptableApi {

	public OptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all options</p>
	 * <p>Retrieve all the options available on the tenant.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Note that Enterprise Tenants are also allowed to read inherited tenant options from the management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the options are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * <p><strong>ⓘ Info:</strong> To improve performance, the <code>totalPages</code> statistics are cached for 10 seconds.</p>
	 */
	public CompletionStage<OptionCollection> getOptions(final int currentPage, final int pageSize, final boolean withTotalPages) {
		return adapt().path("tenant").path("options")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
			.rx()
			.method("GET", OptionCollection.class);
	}
	
	/**
	 * <p>Create an option</p>
	 * <p>Create an option on your tenant.</p>
	 * <p>Options are category-key-value tuples which store tenant configurations. Some categories of options allow the creation of new ones, while others are limited to predefined set of keys.</p>
	 * <p>Any option of any tenant can be defined as "non-editable" by the "management" tenant; once done, any PUT or DELETE requests made on that option by the tenant owner will result in a 403 error (Unauthorized).</p>
	 * <h3>Default option categories</h3>
	 * <p><strong>access.control</strong></p>
	 * <p>| Key |	Default value |	Predefined | Description ||--|--|--|--|| allow.origin | * | Yes | Comma separated list of domains allowed for execution of CORS. Wildcards are allowed (for example, <code>*.cumulocity.com</code>) |</p>
	 * <p><strong>alarm.type.mapping</strong></p>
	 * <p>| Key  |	Predefined | Description ||--|--|--|| <ALARM_TYPE> | No | Overrides the severity and alarm text for the alarm with type <ALARM_TYPE>. The severity and text are specified as <code><ALARM_SEVERITY>\|<ALARM_TEXT></code>. If either part is empty, the value will not be overridden. If the severity is NONE, the alarm will be suppressed. Example: <code>"CRITICAL\|temperature too high"</code>|</p>
	 * <h3>Encrypted credentials</h3>
	 * <p>Adding a "credentials." prefix to the <code>key</code> will make the <code>value</code> of the option encrypted. When the option is  sent to a microservice, the "credentials." prefix is removed and the <code>value</code> is decrypted. For example:</p>
	 * <pre>
	 * {
	 *   "category": "secrets",
	 *   "key": "credentials.mykey",
	 *   "value": "myvalue"
	 * }
	 * </pre>
	 * <p>In that particular example, the request will contain an additional header <code>"Mykey": "myvalue"</code>.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An option was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<Option> createOption(final Option body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		return adapt().path("tenant").path("options")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.option+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Option.class);
	}
	
	/**
	 * <p>Retrieve all options by category</p>
	 * <p>Retrieve all the options (by a specified category) on your tenant.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Note that Enterprise Tenants are also allowed to read inherited tenant options from the management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the options are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param category
	 * <p>The category of the options.</p>
	 */
	public CompletionStage<CategoryOptions> getOptionsByCategory(final String category) {
		return adapt().path("tenant").path("options").path(valueOf(category))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("GET", CategoryOptions.class);
	}
	
	/**
	 * <p>Update options by category</p>
	 * <p>Update one or more options (by a specified category) on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A collection of options was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param category
	 * <p>The category of the options.</p>
	 */
	public CompletionStage<CategoryOptions> updateOptionsByCategory(final CategoryOptions body, final String category) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("options").path(valueOf(category))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), CategoryOptions.class);
	}
	
	/**
	 * <p>Retrieve a specific option</p>
	 * <p>Retrieve a specific option (by a given category and key) on your tenant.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Note that Enterprise Tenants are also allowed to read inherited tenant options from the management tenant.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the option is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Option not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param category
	 * <p>The category of the options.</p>
	 * @param key
	 * <p>The key of an option.</p>
	 */
	public CompletionStage<Option> getOption(final String category, final String key) {
		return adapt().path("tenant").path("options").path(valueOf(category)).path(valueOf(key))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("GET", Option.class);
	}
	
	/**
	 * <p>Update a specific option</p>
	 * <p>Update the value of a specific option (by a given category and key) on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_ADMIN <b>AND</b> the option is editable
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>An option was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Option not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param category
	 * <p>The category of the options.</p>
	 * @param key
	 * <p>The key of an option.</p>
	 */
	public CompletionStage<Option> updateOption(final CategoryKeyOption body, final String category, final String key) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("options").path(valueOf(category)).path(valueOf(key))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Option.class);
	}
	
	/**
	 * <p>Remove a specific option</p>
	 * <p>Remove a specific option (by a given category and key) on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_OPTION_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>An option was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Option not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param category
	 * <p>The category of the options.</p>
	 * @param key
	 * <p>The key of an option.</p>
	 */
	public CompletionStage<Response> deleteOption(final String category, final String key) {
		return adapt().path("tenant").path("options").path(valueOf(category)).path(valueOf(key))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
