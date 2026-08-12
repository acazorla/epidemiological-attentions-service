package com.corpcazorla.epidemiological.infrastructure.adapter.in.util;

import jakarta.ws.rs.container.ContainerRequestContext;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.corpcazorla.epidemiological.infrastructure.adapter.in.constants.TracingConstants;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.Meta;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.Pagination;

public class MetaBuilder {
    private MetaBuilder() {
        /* This utility class should not be instantiated */
    }
    public static Meta build(ContainerRequestContext ctx, String version, Pagination pagination) {

        Meta meta = new Meta();

        meta.setTraceId(String.valueOf(ctx.getProperty(TracingConstants.TRACE_ID)));
        meta.setRequestId(String.valueOf( ctx.getProperty(TracingConstants.REQUEST_ID)));
        meta.setTimestamp(OffsetDateTime.now(ZoneOffset.UTC).withNano(0).toString());
        meta.setVersion(version);
        meta.setPath(ctx.getUriInfo().getPath());
        meta.setMethod(ctx.getMethod());
        if (pagination != null) {
            meta.setPagination(pagination);
        }

        return meta;
    }
}
