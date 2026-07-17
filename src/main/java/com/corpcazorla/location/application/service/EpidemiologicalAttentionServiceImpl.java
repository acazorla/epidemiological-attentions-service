package com.corpcazorla.location.application.service;

import com.corpcazorla.location.application.model.DataPage;
import com.corpcazorla.location.application.model.SearchAttentionQuery;
import com.corpcazorla.location.domain.model.EpidemiologicalAttention;
import com.corpcazorla.location.domain.ports.out.EpidemiologicalAttentionRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EpidemiologicalAttentionServiceImpl implements EpidemiologicalAttentionService{
	private final EpidemiologicalAttentionRepository epidemiologicalAttentionRepositoryPort;

    public EpidemiologicalAttentionServiceImpl(EpidemiologicalAttentionRepository repository) {
        this.epidemiologicalAttentionRepositoryPort = repository;
    }
    public DataPage<EpidemiologicalAttention> listEpidemiologicalAttention(SearchAttentionQuery request) {
        return epidemiologicalAttentionRepositoryPort.listEpidemiologicalAttentionRepository(request);
    }

}
