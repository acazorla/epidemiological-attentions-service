package com.corpcazorla.location.infrastructure.adapter.in.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.corpcazorla.location.domain.model.EpidemiologicalAttention;
import com.corpcazorla.location.infrastructure.adapter.in.dto.EpidemiologicalAttentionResponse;

@Mapper(componentModel = "jakarta")
public interface EpidemiologicalAttentionResponseMapper {

	EpidemiologicalAttentionResponse toResponse(EpidemiologicalAttention epidemiologicalAttention);

    List<EpidemiologicalAttentionResponse> toResponseList(List<EpidemiologicalAttention> list);
}
