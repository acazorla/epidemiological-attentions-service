package com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.corpcazorla.epidemiological.domain.model.EpidemiologicalMedications;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.EpidemiologicalMedicationsResponse;

@Mapper(componentModel = "jakarta")
public interface EpidemiologicalMedicationsResponseMapper {

	EpidemiologicalMedicationsResponse toResponse(EpidemiologicalMedications epidemiologicalAttention);

    List<EpidemiologicalMedicationsResponse> toResponseList(List<EpidemiologicalMedications> list);
}
