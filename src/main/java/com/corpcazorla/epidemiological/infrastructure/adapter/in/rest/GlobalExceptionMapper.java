package com.corpcazorla.epidemiological.infrastructure.adapter.in.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger; // Logger de Quarkus/JBoss

import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

import com.corpcazorla.epidemiological.application.error.ErrorCode;
import com.corpcazorla.epidemiological.application.exception.BusinessException;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.ApiResponse;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.ErrorDetail;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.Meta;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.util.MetaBuilder;
import com.corpcazorla.epidemiological.infrastructure.adapter.out.persistence.PersistenceAdapterException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @ConfigProperty(name = "proyecto.api.version")
    String apiVersion;
	private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);
    @Context
    ContainerRequestContext requestContext;
    @Override
    public Response toResponse(Throwable exception) {
    	String traceId = UUID.randomUUID().toString();
        // Valores por defecto para errores no controlados (500)
        Response.Status status;
        String userMessage;
        String errorCode;
        // 1. Business Exception
        if (exception instanceof BusinessException be) {
            status = Response.Status.fromStatusCode(be.getHttpStatus());
            userMessage = be.getMessage();
            errorCode = be.getCode();
            LOG.warnf("BusinessException [TraceID: %s]: %s", traceId,errorCode, userMessage);
        // 2. Persistence Exception
        }else if (exception instanceof PersistenceAdapterException pe) {
            status = Response.Status.fromStatusCode(pe.getCode());
            userMessage = pe.getMessage();
            errorCode = ErrorCode.DATABASE_ERROR.getCode();
            LOG.warnf("PersistenceException [TraceID: %s]: %s", traceId, userMessage);
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            userMessage = "Ocurrió un error inesperado";
            errorCode = ErrorCode.INTERNAL_ERROR.getCode();       	
            // Si es un error técnico real (NullPointer, SQL Error, etc.)
            LOG.errorf(exception, "UnhandledException [TraceID=%s]", traceId);
            LOG.errorf("Error Crítico [TraceID: %s] - Detalle: %s", traceId, exception.getMessage(), exception);
        }

     // Construcción de respuesta estándar
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setSuccess(false);
        Meta meta = MetaBuilder.build(requestContext, apiVersion, null);
        ErrorDetail errorDetail = new ErrorDetail(errorCode, userMessage);
        errorResponse.setMeta(meta);
        errorResponse.setError(errorDetail);

        return Response.status(status).entity(errorResponse).build();
    }
}
