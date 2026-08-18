package com.corpcazorla.epidemiological.application.service;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchMedicationsQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalMedications;

public interface EpidemiologicalMedicationsService {
	DataPage<EpidemiologicalMedications> listEpidemiologicalAttention(SearchMedicationsQuery request);

}
