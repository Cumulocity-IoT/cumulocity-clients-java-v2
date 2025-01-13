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
public class BulkOperationCollection {

	/**
	 * <p>An array of bulk operations.</p>
	 */
	private BulkOperation[] bulkOperations;

	/**
	 * <p>A URI reference [<a href="https://tools.ietf.org/html/rfc3986">RFC3986</a>] to a potential next page of managed objects.</p>
	 */
	private String next;

	/**
	 * <p>A URI reference [<a href="https://tools.ietf.org/html/rfc3986">RFC3986</a>] to a potential previous page of managed objects.</p>
	 */
	private String prev;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>Information about paging statistics.</p>
	 */
	private PageStatistics statistics;

	public BulkOperation[] getBulkOperations() {
		return bulkOperations;
	}
	
	public void setBulkOperations(final BulkOperation[] bulkOperations) {
		this.bulkOperations = bulkOperations;
	}

	public String getNext() {
		return next;
	}
	
	public void setNext(final String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}
	
	public void setPrev(final String prev) {
		this.prev = prev;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public PageStatistics getStatistics() {
		return statistics;
	}
	
	public void setStatistics(final PageStatistics statistics) {
		this.statistics = statistics;
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
		if (r != null && r instanceof BulkOperationCollection) {
			BulkOperationCollection comparer = (BulkOperationCollection) r;
			if (comparer.getBulkOperations().equals(this.getBulkOperations()) && String.valueOf(comparer.getNext()).equals(String.valueOf(this.getNext())) && String.valueOf(comparer.getPrev()).equals(String.valueOf(this.getPrev())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getStatistics().equals(this.getStatistics())) {
				return true;
			}
		}
		return false;
	}
}
