// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.DeviceCredentials;
import com.cumulocity.client.model.BulkNewDeviceRequest;

/**
 * <p>API methods to create device credentials in Cumulocity IoT.</p>
 * <p>Device credentials can be enquired by devices that do not have credentials for accessing a tenant yet.Since the device does not have credentials yet, a set of fixed credentials is used for this API.The credentials can be obtained by <a href="https://cumulocity.com/docs/additional-resources/contacting-support/">contacting support</a>.</p>
 * <blockquote>
 * <p><strong>⚠️ Important:</strong> Do not use your tenant credentials with this API.</p>
 * </blockquote>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class DeviceCredentialsApi extends AdaptableApi {

	public DeviceCredentialsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Create device credentials</p>
	 * <p>Create device credentials.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_BOOTSTRAP
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>Device credentials were created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<DeviceCredentials> createDeviceCredentials(final DeviceCredentials body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "password");
		removeFromNode(jsonNode, "tenantId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "username");
		return adapt().path("devicecontrol").path("deviceCredentials")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.devicecredentials+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.devicecredentials+json")
			.rx()
			.method("POST", Entity.json(jsonNode), DeviceCredentials.class);
	}
	
	/**
	 * <p>Create a bulk device credentials request</p>
	 * <p>Create a bulk device credentials request.</p>
	 * <p>Device credentials and basic device representation can be provided within a CSV file which must be UTF-8 or ANSI encoded. The CSV file must have two sections.</p>
	 * <p>The first section is the first line of the CSV file. This line contains column names (headers):</p>
	 * <p>|Name|Mandatory|Description||--- |--- |--- ||ID|Yes|The external ID of a device.||CREDENTIALS|Yes*|Password for the device's user. Mandatory, unless AUTH_TYPE is "CERTIFICATES", then CREDENTIALS can be skipped.||AUTH_TYPE|No|Required authentication type for the device's user. If the device uses credentials, this can be skipped or filled with "BASIC". Devices that use certificates must set "CERTIFICATES".||TENANT|No|The ID of the tenant for which the registration is executed (only allowed for the management tenant).||TYPE|No|The type of the device representation.||NAME|No|The name of the device representation.||ICCID|No|The ICCID of the device (SIM card number). If the ICCID appears in file, the import adds a fragment <code>c8y_Mobile.iccid</code>. The ICCID value is not mandatory for each row, see the example for an HTTP request below.||IDTYPE|No|The type of the external ID. If IDTYPE doesn't appear in the file, the default value is used. The default value is <code>c8y_Serial</code>. The IDTYPE value is not mandatory for each row, see the example for an HTTP request below.||PATH|No|The path in the groups hierarchy where the device is added. PATH contains the name of each group separated by <code>/</code>, that is: <code>main_group/sub_group/.../last_sub_group</code>. If a group does not exist, the import creates the group.||SHELL|No|If this column contains a value of 1, the import adds the SHELL feature to the device (specifically the <code>c8y_SupportedOperations</code> fragment). The SHELL value is not mandatory for each row, see the example for an HTTP request below.|</p>
	 * <p>Section two is the rest of the CSV file. Section two contains the device information. The order and quantity of the values must be the same as of the headers.</p>
	 * <p>A separator is automatically obtained from the CSV file. Valid separator values are: <code>\t</code> (tabulation mark), <code>;</code> (semicolon) and <code>,</code> (comma).</p>
	 * <blockquote>
	 * <p><strong>⚠️ Important:</strong> The CSV file needs the "com_cumulocity_model_Agent.active" header with a value of "true" to be added to the request.</p>
	 * </blockquote>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> A bulk registration creates an elementary representation of the device. Then, the device needs to update it to a full representation with its own status. The device is ready to use only after it is updated to the full representation. Also see <a href="https://cumulocity.com/docs/device-management-application/registering-devices/#security-token-policy">Device management > Device management application > Registering devices > Single device registration > Security token policy</a> and <a href="https://cumulocity.com/docs/device-integration/rest/#integration-life-cycle">Device management > Device integration > Device integration using REST > Integration life cycle</a>.</p>
	 * </blockquote>
	 * <p>A CSV file can appear in many forms (with regard to the optional tenant column and the occurrence of device information):</p>
	 * <ul>
	 * 	<li><p>If a user is logged in as the management tenant, then the columns ID, CREDENTIALS and TENANT are mandatory, and the device credentials will be created for the tenant mentioned in the TENANT column.</p>
	 * 	</li>
	 * 	<li><p>If a user is logged in as a different tenant, for example, as <code>sample_tenant</code>, then the columns ID and CREDENTIALS are mandatory (if the file contains the TENANT column, it is ignored and the device credentials will be created for the tenant that is logged in).</p>
	 * 	</li>
	 * 	<li><p>If a user wants to add information about the device, the columns TYPE and NAME must appear in the CSV file.</p>
	 * 	</li>
	 * 	<li><p>If a user wants to add information about a SIM card number, the columns TYPE, NAME and ICCID must appear in the CSV file.</p>
	 * 	</li>
	 * 	<li><p>If a user wants to change the type of external ID, the columns TYPE, NAME and IDTYPE must appear in the CSV file.</p>
	 * 	</li>
	 * 	<li><p>If a user wants to add a device to a group, the columns TYPE, NAME and PATH must appear in the CSV file.</p>
	 * 	</li>
	 * 	<li><p>If a user wants to add the SHELL feature, the columns TYPE, NAME and SHELL must appear in the CSV file and the column SHELL must contain a value of 1.</p>
	 * 	</li>
	 * </ul>
	 * <p>It is possible to define a custom <a href="#tag/External-IDs">external ID</a> mapping and some custom device properties which are added to newly created devices during registration:</p>
	 * <ul>
	 * 	<li><p>To add a custom external ID mapping, enter the external ID type as the header of the last column with the prefix "external-", for example, to add an external ID mapping of type <code>c8y_Imei</code>, enter <code>external-c8y_Imei</code> in the last column header. The value of this external ID type should be set in the corresponding column of the data rows.</p>
	 * 	</li>
	 * 	<li><p>To add a custom property to a registered device, enter the custom property name as a header, for example, "myCustomProperty", and the value would be in the rows below.</p>
	 * 	</li>
	 * </ul>
	 * <p>The custom device properties mapping has the following limitations:</p>
	 * <ul>
	 * 	<li><p>Braces '{}' used in data rows will be interpreted as strings of "{}". The system will interpret the value as an object when some custom property is added, for example, put <code>com_cumulocity_model_Agent.active</code> into the headers row and <code>true</code> into the data row to create an object <code>"com_cumulocity_model_Agent": {"active": "true"}"</code>.</p>
	 * 	</li>
	 * 	<li><p>It is not possible to add array values via bulk registration.</p>
	 * 	</li>
	 * </ul>
	 * <p>Example file:</p>
	 * <pre>
	 * ID;CREDENTIALS;TYPE;NAME;ICCID;IDTYPE;PATH;SHELL
	 * id_101;AbcD1234!1234AbcD;type_of_device;Device 101;111111111;;csv device/subgroup0;1
	 * id_102;AbcD1234!1234AbcD;type_of_device;Device 102;222222222;;csv device/subgroup0;0
	 * id_111;AbcD1234!1234AbcD;type_of_device;Device 111;333333333;c8y_Imei;csv device1/subgroup1;0
	 * id_112;AbcD1234!1234AbcD;type_of_device;Device 112;444444444;;csv device1/subgroup1;1
	 * id_121;AbcD1234!1234AbcD;type_of_device;Device 121;555555555;;csv device1/subgroup2;1
	 * id_122;AbcD1234!1234AbcD;type_of_device;Device 122;;;csv device1/subgroup2;
	 * id_131;AbcD1234!1234AbcD;type_of_device;Device 131;;;csv device1/subgroup3;
	 * </pre>
	 * <p>There is also a simple registration method that creates all registration requests at once, then each one needs to go through regular acceptance.This simple registration only makes use of the ID and PATH fields from the list above.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>A bulk of new device requests was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param file
	 * <p>The CSV file to be uploaded.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 */
	public CompletionStage<BulkNewDeviceRequest> createBulkDeviceCredentials(final byte[] file, final String xCumulocityProcessingMode) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("file", file, MediaType.valueOf("text/csv"));
		return adapt().path("devicecontrol").path("bulkNewDeviceRequests")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulknewdevicerequest+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), BulkNewDeviceRequest.class);
	}
}
