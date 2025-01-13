// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.supplementary;

import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AdaptableApi {

	private final WebTarget rootTarget;

	protected AdaptableApi(final WebTarget rootTarget) {
		this.rootTarget = rootTarget;
		this.rootTarget.register(ObjectMapperProvider.class);
	}

	protected AdaptableApi.Builder adapt() {
		return new Builder(rootTarget);
	}

	protected String valueOf(final Object s) {
		return s != null ? String.valueOf(s) : null;
	}

	protected <RequestBody> JsonNode toJsonNode(final RequestBody body) {
		return new ObjectMapper().valueToTree(body);
	}

	protected void removeFromNode(final JsonNode node, final String... pathItems) {
		if (pathItems.length > 0) {
			JsonNode currentNode = node;
			String nodeName = pathItems[0];
			int index = 0;
			while (index < (pathItems.length - 1)) {
				currentNode = node.get(nodeName);
				index++;
				nodeName = pathItems[index];
			}
			if (currentNode.isObject()) {
				final ObjectNode objectNode = (ObjectNode) currentNode;
				objectNode.remove(nodeName);
			}
		}
	}

	protected class Builder {

		private WebTarget target;

		public Builder(final WebTarget target) {
			this.target = target;
		}

		public Builder path(final String s) {
			target = target.path(s);
			return this;
		}

		/**
		 * Appends a path query based on the passed <code>key</code>/<code>value</code> pair if <code>value</code> is not null.
		 * 
		 * The parameter will be serialized as <code>?key=value</code>.
		 * 
		 * @param <T>
		 * @param key
		 * @param value
		 * @return
		 */
		public <T> Builder queryParam(final String key, final T value) {
			target = target.queryParam(key, value);
			return this;
		}

		public <T> Builder queryParam(final String key, final T[] values) {
			return queryParam(key, values, true);
		}

		/**
		 * Appends a path query based on the passed <code>key</code>/<code>value</code> pair if <code>value</code> is not null.
		 *
		 * The parameter <code>explode</code> defines the serialization method:
		 *
		 * <li>
		 * <ul>If <code>true</code>, parameter will be serialized as <code>?key=1&key=2&key=3</code>.</ul>
		 * <ul>If <code>false</code>, parameter will be serialized as <code>?key=1,2,3</code>.</ul>
		 * </li>
		 *
		 * @param <T>
		 * @param key
		 * @param values
		 * @param explode
		 * @return
		 */
		public <T> Builder queryParam(final String key, final T[] values, final boolean explode) {
			if (values != null) {
				if (explode) {
					for (final T v : values) {
						target = target.queryParam(key, v);
					}
				} else {
					target = target.queryParam(key, joinToString(values));
				}
			}
			return this;
		}

		public Invocation.Builder request() {
			return target.request();
		}

		protected <T> String joinToString(final T[] many) {
			return Arrays.stream(many).filter(Objects::nonNull).map(Objects::toString).collect(Collectors.joining(","));
		}
	}
}
