// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Measurement;
import com.cumulocity.client.model.MeasurementCollection;
import com.cumulocity.client.model.MeasurementSeries;

/**
 * <p>Measurements are produced by reading sensor values. In some cases, this data is read in static intervals and sent to the platform (for example, temperature sensors or electrical meters). In other cases, the data is read on demand or at irregular intervals (for example, health devices such as weight scales). Regardless what kind of protocol the device supports, the agent is responsible for converting it into a "push" protocol by uploading data to Cumulocity IoT.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class MeasurementsApi extends AdaptableApi {

	public MeasurementsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all measurements</p>
	 * <p>Retrieve all measurements on your tenant, or a specific subset based on queries.</p>
	 * <p>In case of executing <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a> between an upper and lower boundary, for example, querying using <code>dateFrom</code>���<code>dateTo</code>, the oldest registered measurements are returned first. It is possible to change the order using the query parameter <code>revert=true</code>.</p>
	 * <p>For large measurement collections, querying older records without filters can be slow as the server needs to scan from the beginning of the input results set before beginning to return the results. For cases when older measurements should be retrieved, it is recommended to narrow the scope by using range queries based on the time stamp reported by a device. The scope of query can also be reduced significantly when a source device is provided.</p>
	 * <p>Review <a href="#tag/Measurements-specifics">Measurements Specifics</a> for details about data streaming and response formats.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_READ
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all measurements are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the measurement.</p>
	 * @param dateTo
	 * <p>End date or date and time of the measurement.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param revert
	 * <p>If you are using a range query (that is, at least one of the <code>dateFrom</code> or <code>dateTo</code> parameters is included in the request), then setting <code>revert=true</code> will sort the results by the newest measurements first.By default, the results are sorted by the oldest measurements first.</p>
	 * @param source
	 * <p>The managed object ID to which the measurement is associated.</p>
	 * @param type
	 * <p>The type of measurement to search for.</p>
	 * @param valueFragmentSeries
	 * <p>The specific series to search for.</p>
	 * @param valueFragmentType
	 * <p>A characteristic which identifies the measurement.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<MeasurementCollection> getMeasurements(final int currentPage, final String dateFrom, final String dateTo, final int pageSize, final boolean revert, final String source, final String type, final String valueFragmentSeries, final String valueFragmentType, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("measurement").path("measurements")
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("pageSize", pageSize)
			.queryParam("revert", revert)
			.queryParam("source", source)
			.queryParam("type", type)
			.queryParam("valueFragmentSeries", valueFragmentSeries)
			.queryParam("valueFragmentType", valueFragmentType)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurementcollection+json")
			.rx()
			.method("GET", MeasurementCollection.class);
	}
	
	/**
	 * <p>Create a measurement</p>
	 * <p>A measurement must be associated with a source (managed object) identified by ID, and must specify the type of measurement and the time when it was measured by the device (for example, a thermometer).</p>
	 * <p>Each measurement fragment is an object (for example, <code>c8y_Steam</code>) containing the actual measurements as properties. The property name represents the name of the measurement (for example, <code>Temperature</code>) and it contains two properties:</p>
	 * <ul>
	 * 	<li><p><code>value</code> - The value of the individual measurement. The maximum precision for floating point numbers is 64-bit IEEE 754. For integers it's a 64-bit two's complement integer. The <code>value</code> is mandatory for a fragment.</p>
	 * 	</li>
	 * 	<li><p><code>unit</code> - The unit of the measurements.</p>
	 * 	</li>
	 * </ul>
	 * <p>Review the <a href="#section/System-of-units">System of units</a> section for details about the conversions of units. Also review <a href="https://cumulocity.com/docs/concepts/domain-model/#naming-conventions-of-fragments">Getting started > Technical concepts > Cumulocity IoT's domain model > Inventory > Fragments > Naming conventions of fragments</a> in the Cumulocity IoT user documentation.</p>
	 * <p>The example below uses <code>c8y_Steam</code> in the request body to illustrate a fragment for recording temperature measurements.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Property names used for fragment and series must not contain whitespaces nor the special characters <code>. , * [ ] ( ) @ $</code>. This is required to ensure a correct processing and visualization of measurement series on UI graphs.</p>
	 * </blockquote>
	 * <h3>Create multiple measurements</h3>
	 * <p>It is also possible to create multiple measurements at once by sending a <code>measurements</code> array containing all the measurements to be created. The content type must be <code>application/vnd.com.nsn.cumulocity.measurementcollection+json</code>.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> For more details about fragments with specific meanings, refer to <a href="https://cumulocity.com/docs/device-integration/fragment-library/">Device management & connectivity > Device integration > Fragment library</a> in the Cumulocity IoT user documentation.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_ADMIN <b>OR</b> owner of the source <b>OR</b> MEASUREMENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A measurement was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Measurement> createMeasurement(final Measurement body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("measurement").path("measurements")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.measurement+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json, application/vnd.com.nsn.cumulocity.measurementcollection+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Measurement.class);
	}
	
	/**
	 * <p>Create a measurement</p>
	 * <p>A measurement must be associated with a source (managed object) identified by ID, and must specify the type of measurement and the time when it was measured by the device (for example, a thermometer).</p>
	 * <p>Each measurement fragment is an object (for example, <code>c8y_Steam</code>) containing the actual measurements as properties. The property name represents the name of the measurement (for example, <code>Temperature</code>) and it contains two properties:</p>
	 * <ul>
	 * 	<li><p><code>value</code> - The value of the individual measurement. The maximum precision for floating point numbers is 64-bit IEEE 754. For integers it's a 64-bit two's complement integer. The <code>value</code> is mandatory for a fragment.</p>
	 * 	</li>
	 * 	<li><p><code>unit</code> - The unit of the measurements.</p>
	 * 	</li>
	 * </ul>
	 * <p>Review the <a href="#section/System-of-units">System of units</a> section for details about the conversions of units. Also review <a href="https://cumulocity.com/docs/concepts/domain-model/#naming-conventions-of-fragments">Getting started > Technical concepts > Cumulocity IoT's domain model > Inventory > Fragments > Naming conventions of fragments</a> in the Cumulocity IoT user documentation.</p>
	 * <p>The example below uses <code>c8y_Steam</code> in the request body to illustrate a fragment for recording temperature measurements.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> Property names used for fragment and series must not contain whitespaces nor the special characters <code>. , * [ ] ( ) @ $</code>. This is required to ensure a correct processing and visualization of measurement series on UI graphs.</p>
	 * </blockquote>
	 * <h3>Create multiple measurements</h3>
	 * <p>It is also possible to create multiple measurements at once by sending a <code>measurements</code> array containing all the measurements to be created. The content type must be <code>application/vnd.com.nsn.cumulocity.measurementcollection+json</code>.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> For more details about fragments with specific meanings, refer to <a href="https://cumulocity.com/docs/device-integration/fragment-library/">Device management & connectivity > Device integration > Fragment library</a> in the Cumulocity IoT user documentation.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_ADMIN <b>OR</b> owner of the source <b>OR</b> MEASUREMENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A measurement was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<MeasurementCollection> createMeasurement(final MeasurementCollection body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "next");
		removeFromNode(jsonNode, "prev");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "statistics");
		return adapt().path("measurement").path("measurements")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.measurementcollection+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json, application/vnd.com.nsn.cumulocity.measurementcollection+json")
			.rx()
			.method("POST", Entity.json(jsonNode), MeasurementCollection.class);
	}
	
	/**
	 * <p>Remove measurement collections</p>
	 * <p>Remove measurement collections specified by query parameters.</p>
	 * <p>DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when there are a lot of measurements to be deleted.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> DELETE requires at least one of the following parameters: <code>source</code>, <code>dateFrom</code>, <code>dateTo</code>.</p>
	 * </blockquote>
	 * <p>In case of enhanced time series measurements, both <code>dateFrom</code> and <code>dateTo</code> parameters must be truncated to full hours (for example, 2022-08-19T14:00:00.000Z), otherwise an error will be returned.The <code>fragmentType</code> parameter allows to delete measurements only by a measurement fragment when enhanced time series measurements are used.It's not possible to delete by a custom (non-measurement) fragment.</p>
	 * <p>Example for a valid measurement value fragment:</p>
	 * <pre>
	 * "c8y_TemperatureMeasurement": {
	 *     "T": {
	 *       "value": 28,
	 *       "unit": "C"
	 *     }
	 * }
	 * </pre>
	 * <p>In the example above <code>c8y_TemperatureMeasurement</code> is called fragment and <code>T</code> is called series.</p>
	 * <p>Example for a non-measurement fragment:</p>
	 * <pre>
	 * "c8y_TemperatureMeasurement": 28
	 * </pre>
	 * <p>Enhanced Time series measurements will not allow to delete by fragment specific like above.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A collection of measurements was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity ��� invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the measurement.</p>
	 * @param dateTo
	 * <p>End date or date and time of the measurement.</p>
	 * @param fragmentType
	 * <p>A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.</p>
	 * @param source
	 * <p>The managed object ID to which the measurement is associated.</p>
	 * @param type
	 * <p>The type of measurement to search for.</p>
	 */
	public CompletionStage<Response> deleteMeasurements(final String xCumulocityProcessingMode, final String dateFrom, final String dateTo, final String fragmentType, final String source, final String type) {
		return adapt().path("measurement").path("measurements")
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("fragmentType", fragmentType)
			.queryParam("source", source)
			.queryParam("type", type)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a specific measurement</p>
	 * <p>Retrieve a specific measurement by a given ID.Note that you cannot retrieve time series measurements by ID.Instead you can search for such measurements via query parameters.No behavior changes for tenants which do not have time series enabled.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_READ <b>OR</b> owner of the source <b>OR</b> MEASUREMENT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the measurement is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Measurement not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the measurement.</p>
	 */
	public CompletionStage<Measurement> getMeasurement(final String id) {
		return adapt().path("measurement").path("measurements").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json")
			.rx()
			.method("GET", Measurement.class);
	}
	
	/**
	 * <p>Remove a specific measurement</p>
	 * <p>Remove a specific measurement by a given ID.Note that you cannot delete time series measurements by ID.Instead, you can delete by query or use the retention rules to remove expired measurements data from the Operational Store.No behavior changes for tenants which do not have time series enabled.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_ADMIN <b>OR</b> owner of the source <b>OR</b> MEASUREMENT_ADMIN permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>A measurement was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Measurement not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the measurement.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<Response> deleteMeasurement(final String id, final String xCumulocityProcessingMode) {
		return adapt().path("measurement").path("measurements").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Retrieve a list of series and their values</p>
	 * <p>Retrieve a list of series (all or only those matching the specified names) and their values within a given period of a specific managed object (source).<br>A series is any fragment in measurement that contains a <code>value</code> property.</p>
	 * <p>It is possible to fetch aggregated results using the <code>aggregationType</code> parameter. If the aggregation is not specified, the result will contain no more than 5000 values.</p>
	 * <blockquote>
	 * <p><strong>������ Important:</strong> For the aggregation to be done correctly, a device shall always use the same time zone when it sends dates.</p>
	 * </blockquote>
	 * <section><h5>Required roles</h5>
	 * ROLE_MEASUREMENT_READ <b>OR</b> owner of the source <b>OR</b> MEASUREMENT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the series are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param aggregationType
	 * <p>Fetch aggregated results as specified.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the measurement.</p>
	 * @param dateTo
	 * <p>End date or date and time of the measurement.</p>
	 * @param revert
	 * <p>If you are using a range query (that is, at least one of the <code>dateFrom</code> or <code>dateTo</code> parameters is included in the request), then setting <code>revert=true</code> will sort the results by the newest measurements first.By default, the results are sorted by the oldest measurements first.</p>
	 * @param series
	 * <p>The specific series to search for.</p>
	 * <p><strong>ⓘ Info:</strong> If you want to query multiple series at once, you must specify the parameter multiple times.</p>
	 * @param source
	 * <p>The managed object ID to which the measurement is associated.</p>
	 */
	public CompletionStage<MeasurementSeries> getMeasurementSeries(final String aggregationType, final String dateFrom, final String dateTo, final boolean revert, final String[] series, final String source) {
		return adapt().path("measurement").path("measurements").path("series")
			.queryParam("aggregationType", aggregationType)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("revert", revert)
			.queryParam("series", series, true)
			.queryParam("source", source)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", MeasurementSeries.class);
	}
}
