// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ApplicationBinaries {

	/**
	 * <p>An array of attachments.</p>
	 */
	private Attachments[] attachments;

	public Attachments[] getAttachments() {
		return attachments;
	}
	
	public void setAttachments(final Attachments[] attachments) {
		this.attachments = attachments;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Attachments {
	
		/**
		 * <p>The application context path.</p>
		 */
		private String contextPath;
	
		/**
		 * <p>The date and time when the attachment was created.</p>
		 */
		private String created;
	
		/**
		 * <p>A description for the attachment.</p>
		 */
		private String description;
	
		/**
		 * <p>A download URL for the attachment.</p>
		 */
		private String downloadUrl;
	
		/**
		 * <p>The ID of the attachment.</p>
		 */
		private String id;
	
		/**
		 * <p>The length of the attachment, in bytes.</p>
		 */
		private int length;
	
		/**
		 * <p>The name of the attachment.</p>
		 */
		private String name;
	
		public String getContextPath() {
			return contextPath;
		}
		
		public void setContextPath(final String contextPath) {
			this.contextPath = contextPath;
		}
	
		public String getCreated() {
			return created;
		}
		
		public void setCreated(final String created) {
			this.created = created;
		}
	
		public String getDescription() {
			return description;
		}
		
		public void setDescription(final String description) {
			this.description = description;
		}
	
		public String getDownloadUrl() {
			return downloadUrl;
		}
		
		public void setDownloadUrl(final String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
		}
	
		public int getLength() {
			return length;
		}
		
		public void setLength(final int length) {
			this.length = length;
		}
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
		}
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof Attachments) {
				Attachments comparer = (Attachments) r;
				if (String.valueOf(comparer.getContextPath()).equals(String.valueOf(this.getContextPath())) && String.valueOf(comparer.getCreated()).equals(String.valueOf(this.getCreated())) && String.valueOf(comparer.getDescription()).equals(String.valueOf(this.getDescription())) && String.valueOf(comparer.getDownloadUrl()).equals(String.valueOf(this.getDownloadUrl())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && Integer.valueOf(comparer.getLength()).equals(Integer.valueOf(this.getLength())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName()))) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof ApplicationBinaries) {
			ApplicationBinaries comparer = (ApplicationBinaries) r;
			if (comparer.getAttachments().equals(this.getAttachments())) {
				return true;
			}
		}
		return false;
	}
}
