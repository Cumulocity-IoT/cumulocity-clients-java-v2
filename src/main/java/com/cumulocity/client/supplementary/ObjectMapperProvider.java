// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.supplementary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

/**
 * A <code>ContextResolver</code> implementation providing an <code>ObjectMapper</code>.
 * <p>
 * The mapper will use non-standard coercion values to handle empty fragments either as empty Object or empy String, allowing to treat <code>"fragment": ""</code> in the same way as <code>"fragment": {}</code>.
 */
@Provider
@Produces(MediaType.WILDCARD)
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ObjectMapperProvider() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.coercionConfigDefaults().setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsEmpty);
    }

    @Override
    public ObjectMapper getContext(final Class<?> objectType) {
        return objectMapper;
    }
}
