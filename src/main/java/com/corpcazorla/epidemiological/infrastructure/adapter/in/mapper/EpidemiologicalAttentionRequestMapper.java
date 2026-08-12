package com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper;

import org.mapstruct.*;

import com.corpcazorla.epidemiological.application.model.SearchAttentionQuery;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.EpidemiologicalAttentionRequest;

import java.time.LocalDate;

@Mapper(componentModel = "jakarta")
public interface EpidemiologicalAttentionRequestMapper {
    @Mapping(target = "startDate", expression = "java(mapDate(request.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(mapDate(request.getEndDate()))")
    SearchAttentionQuery toQuery(EpidemiologicalAttentionRequest request);
    // --- helper ---
    default LocalDate mapDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }
}
