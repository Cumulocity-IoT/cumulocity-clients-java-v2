// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Provides detailed information about the closest mobile cell towers. When the functionality is activated, the location of the device is determined based on this fragment, in order to track the device whereabouts when GPS tracking is not available.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yCellInfo {

	/**
	 * <p>The radio type of this cell tower.</p>
	 */
	private String radioType;

	/**
	 * <p>Detailed information about the neighboring cell towers.</p>
	 */
	private C8yCellTower[] cellTowers;

	public C8yCellInfo() {
	}

	public C8yCellInfo(final C8yCellTower[] cellTowers) {
		this.cellTowers = cellTowers;
	}

	public String getRadioType() {
		return radioType;
	}
	
	public void setRadioType(final String radioType) {
		this.radioType = radioType;
	}

	public C8yCellTower[] getCellTowers() {
		return cellTowers;
	}
	
	public void setCellTowers(final C8yCellTower[] cellTowers) {
		this.cellTowers = cellTowers;
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
		if (r != null && r instanceof C8yCellInfo) {
			C8yCellInfo comparer = (C8yCellInfo) r;
			if (String.valueOf(comparer.getRadioType()).equals(String.valueOf(this.getRadioType())) && comparer.getCellTowers().equals(this.getCellTowers())) {
				return true;
			}
		}
		return false;
	}
}
