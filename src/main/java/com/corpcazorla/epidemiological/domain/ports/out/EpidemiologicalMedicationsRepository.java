package com.corpcazorla.epidemiological.domain.ports.out;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchMedicationsQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalMedications;

public interface EpidemiologicalMedicationsRepository {
	DataPage<EpidemiologicalMedications> listEpidemiologicalAttentionRepository(SearchMedicationsQuery request);
}
