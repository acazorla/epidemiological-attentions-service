package com.corpcazorla.location.infrastructure.adapter.in.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.UUID;

import com.corpcazorla.location.infrastructure.adapter.in.constants.TracingConstants;

@Provider
public class RequestContextFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String traceId = requestContext.getHeaderString("X-Trace-Id");
        String requestId = requestContext.getHeaderString("X-Request-Id");
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        requestContext.setProperty(TracingConstants.TRACE_ID, traceId);
        requestContext.setProperty(TracingConstants.REQUEST_ID, requestId);
    }
}
