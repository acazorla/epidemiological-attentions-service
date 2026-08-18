package com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper;

import org.mapstruct.*;

import com.corpcazorla.epidemiological.application.model.SearchMedicationsQuery;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.EpidemiologicalMedicationsRequest;

import java.time.LocalDate;

@Mapper(componentModel = "jakarta")
public interface EpidemiologicalMedicationsRequestMapper {
    @Mapping(target = "startDate", expression = "java(mapDate(request.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(mapDate(request.getEndDate()))")
    SearchMedicationsQuery toQuery(EpidemiologicalMedicationsRequest request);
    // --- helper ---
    default LocalDate mapDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }
}
