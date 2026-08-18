package com.corpcazorla.epidemiological.infrastructure.adapter.in.rest;

import com.corpcazorla.epidemiological.application.service.EpidemiologicalMedicationsService;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.*;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper.EpidemiologicalMedicationsRequestMapper;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper.EpidemiologicalMedicationsResponseMapper;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.util.MetaBuilder;

import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/v1/epidemiological-medications")
@Tag(name = "epidemiological medications", description = "List of epidemiological medications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EpidemiologicalMedicationsController {
	@Context
	ContainerRequestContext requestContext;
	
    private final EpidemiologicalMedicationsService service;
    private final EpidemiologicalMedicationsResponseMapper responseMapper;
    private final EpidemiologicalMedicationsRequestMapper requestMapper;
    private final String apiVersion;

    public EpidemiologicalMedicationsController(EpidemiologicalMedicationsService service, EpidemiologicalMedicationsRequestMapper requestMapper,EpidemiologicalMedicationsResponseMapper responseMapper,@ConfigProperty(name = "proyecto.api.version") String apiVersion) {
        this.service = service;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        
        this.apiVersion=apiVersion;
    }

    @POST
    @Path("/search")
    @APIResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @APIResponse(responseCode = "400", description = "Invalid request")
    @APIResponse(responseCode = "500", description = "Error interno del servidor")
    public Response listEpidemiologicalAttention(@Valid EpidemiologicalMedicationsRequest request) {
    	
    	var result = service.listEpidemiologicalAttention(requestMapper.toQuery(request));
        List<EpidemiologicalMedicationsResponse> data = responseMapper.toResponseList(result.getData());
        Pagination pagination = Pagination.of(result.getTotalElements());
        Meta meta = MetaBuilder.build(requestContext, apiVersion, pagination);
        return Response.ok(new ApiResponse<>(data, meta)).build();
    }
}