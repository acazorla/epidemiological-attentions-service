package com.corpcazorla.epidemiological.application.service;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchMedicationsQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalMedications;
import com.corpcazorla.epidemiological.domain.ports.out.EpidemiologicalMedicationsRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EpidemiologicalMedicationsServiceImpl implements EpidemiologicalMedicationsService{
	private final EpidemiologicalMedicationsRepository epidemiologicalAttentionRepositoryPort;

    public EpidemiologicalMedicationsServiceImpl(EpidemiologicalMedicationsRepository repository) {
        this.epidemiologicalAttentionRepositoryPort = repository;
    }
    public DataPage<EpidemiologicalMedications> listEpidemiologicalAttention(SearchMedicationsQuery request) {
        return epidemiologicalAttentionRepositoryPort.listEpidemiologicalAttentionRepository(request);
    }

}
