package com.corpcazorla.epidemiological.infrastructure.adapter.in.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.corpcazorla.epidemiological.domain.model.EpidemiologicalAttention;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.EpidemiologicalAttentionResponse;

@Mapper(componentModel = "jakarta")
public interface EpidemiologicalAttentionResponseMapper {

	EpidemiologicalAttentionResponse toResponse(EpidemiologicalAttention epidemiologicalAttention);

    List<EpidemiologicalAttentionResponse> toResponseList(List<EpidemiologicalAttention> list);
}
