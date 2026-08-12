package com.corpcazorla.epidemiological.infrastructure.adapter.in.rest;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.ApiResponse;

import java.time.OffsetDateTime;
import java.util.UUID;

@Provider // Esta anotación le dice a Quarkus que registre este componente automáticamente
public class ResponseFilter implements ContainerResponseFilter {

    @ConfigProperty(name = "proyecto.api.version")
    String apiVersion;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        Object entity = responseContext.getEntity();

        // Verificamos si la respuesta es de tipo ApiResponse
        if (entity instanceof ApiResponse<?> response) {
            
            // Si el objeto meta no existe, lo creamos (aunque tu constructor ya lo hace)
            if (response.getMeta() == null) {
                response.setMeta(new com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.Meta());
            }

            // Inyectamos los valores globales de forma automática
            response.getMeta().setVersion(apiVersion);
            response.getMeta().setTimestamp(OffsetDateTime.now().toString());
            
            // Si el traceId está vacío (como en el flujo de éxito), generamos uno nuevo
            if (response.getMeta().getTraceId() == null) {
                response.getMeta().setTraceId(UUID.randomUUID().toString());
            }
        }
    }
}