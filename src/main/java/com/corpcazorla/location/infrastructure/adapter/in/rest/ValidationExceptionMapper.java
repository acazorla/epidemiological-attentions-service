package com.corpcazorla.location.infrastructure.adapter.in.rest;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.validation.ConstraintViolationException;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import com.corpcazorla.location.application.error.ErrorCode;
import com.corpcazorla.location.infrastructure.adapter.in.dto.ApiResponse;
import com.corpcazorla.location.infrastructure.adapter.in.dto.ErrorDetail;
import com.corpcazorla.location.infrastructure.adapter.in.dto.Meta;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Context
    ContainerRequestContext requestContext;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
    	ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        String message = exception.getConstraintViolations()
                .stream()
                .map(v -> extractFieldName(v.getPropertyPath().toString()) + ": " + v.getMessage())
                .collect(Collectors.joining(", "));

        ErrorDetail error = new ErrorDetail(
        		errorCode.getCode(),
                message
        );

        Meta meta = new Meta();
        meta.setTraceId((String) requestContext.getProperty("traceId"));
        meta.setRequestId((String) requestContext.getProperty("requestId"));
        meta.setTimestamp(OffsetDateTime.now().withNano(0).toString());
        meta.setPath(requestContext.getUriInfo().getPath());
        meta.setMethod(requestContext.getMethod());
        ApiResponse<Object> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMeta(meta);
        response.setError(error);

        return Response.status(errorCode.getHttpStatus()).entity(response).build();
    }
    private String extractFieldName(String fullPath) {
        if (fullPath == null) return null;
        int lastDot = fullPath.lastIndexOf('.');
        return (lastDot != -1) ? fullPath.substring(lastDot + 1) : fullPath;
    }
}
