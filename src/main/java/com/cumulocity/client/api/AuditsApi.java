// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.AuditRecord;
import com.cumulocity.client.model.AuditRecordCollection;

/**
 * <p>An audit log stores events that are security-relevant and should be stored for auditing. For example, an audit log should be generated when a user logs into a gateway.</p>
 * <p>An audit log extends an event through:</p>
 * <ul>
 * 	<li><p>A username of the user that carried out the activity.</p>
 * 	</li>
 * 	<li><p>An application that was used to carry out the activity.</p>
 * 	</li>
 * 	<li><p>The actual activity.</p>
 * 	</li>
 * 	<li><p>A severity.</p>
 * 	</li>
 * </ul>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class AuditsApi extends AdaptableApi {

	public AuditsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all audit records</p>
	 * <p>Retrieve all audit records registered on your tenant, or a specific subset based on queries.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and all audit records are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param application
	 * <p>Name of the application from which the audit was carried out.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param dateFrom
	 * <p>Start date or date and time of the audit record (device time).</p>
	 * @param dateTo
	 * <p>End date or date and time of the audit record (device time).</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param source
	 * <p>The platform component ID to which the audit is associated.</p>
	 * @param type
	 * <p>The type of audit record to search for.</p>
	 * @param user
	 * <p>The username to search for.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 */
	public CompletionStage<AuditRecordCollection> getAuditRecords(final String application, final int currentPage, final String dateFrom, final String dateTo, final int pageSize, final String source, final String type, final String user, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("audit").path("auditRecords")
			.queryParam("application", application)
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("pageSize", pageSize)
			.queryParam("source", source)
			.queryParam("type", type)
			.queryParam("user", user)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecordcollection+json")
			.rx()
			.method("GET", AuditRecordCollection.class);
	}
	
	/**
	 * <p>Create an audit record</p>
	 * <p>Create an audit record.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_AUDIT_ADMIN <b>OR</b> ROLE_SYSTEM <b>OR</b> AUDIT_ADMIN permission on the resource
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>An audit record was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 */
	public CompletionStage<AuditRecord> createAuditRecord(final AuditRecord body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "severity");
		removeFromNode(jsonNode, "application");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "c8y_Metadata");
		removeFromNode(jsonNode, "changes");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("audit").path("auditRecords")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.auditrecord+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json")
			.rx()
			.method("POST", Entity.json(jsonNode), AuditRecord.class);
	}
	
	/**
	 * <p>Retrieve a specific audit record</p>
	 * <p>Retrieve a specific audit record by a given ID.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_AUDIT_READ <b>OR</b> AUDIT_READ permission on the source
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the audit record is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param id
	 * <p>Unique identifier of the audit record.</p>
	 */
	public CompletionStage<AuditRecord> getAuditRecord(final String id) {
		return adapt().path("audit").path("auditRecords").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json")
			.rx()
			.method("GET", AuditRecord.class);
	}
}
