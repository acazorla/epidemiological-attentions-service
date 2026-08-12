package com.corpcazorla.epidemiological.application.service;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchAttentionQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalAttention;
import com.corpcazorla.epidemiological.domain.ports.out.EpidemiologicalAttentionRepository;

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
